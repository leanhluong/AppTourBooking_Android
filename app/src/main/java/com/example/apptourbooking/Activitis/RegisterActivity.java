package com.example.apptourbooking.Activitis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class RegisterActivity extends AppCompatActivity {
    private ImageView imgbackRegister, btnShowPass, btnShowRepass;
    private boolean passwordVisible = false;
    private EditText username, fullname, password, repassword;
    private String token;
    private Button btnRegister;

    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TakeToken();
        Init();

        Back();

        Register();
        Showpass();
        ShowRepass();

    }

    private void Init(){
        imgbackRegister = findViewById(R.id.img_register_back);
        username = findViewById(R.id.editText_username);
        fullname = findViewById(R.id.editText_Fullname);
        password = findViewById(R.id.editText_password);
        repassword = findViewById(R.id.editText_confirm_password);
        btnRegister = findViewById(R.id.btn_Register);
        btnShowPass = findViewById(R.id.register_showpass);
        btnShowRepass = findViewById(R.id.register_show_repass);
    }

    private void Back(){
        imgbackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
    private String TakeToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();
                    }
                });
        return token;
    }

    private void Register(){
        DB = new DatabaseHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String name = fullname.getText().toString();
                String pass = password.getText().toString();
                String repass= repassword.getText().toString();
                if(user.equals("") ||name.equals("")|| pass.equals("") || repass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false ){
                            String tok = TakeToken().toString();
                            Boolean insert = DB.InsertData(user,name,tok,pass);
                            if(insert == true){
                                Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "User already exitsts! Please sign in ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void Showpass(){
        btnShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordVisible) {
                    // Nếu mật khẩu hiện ra, ẩn nó
                    password.setInputType(129); // 129 là giá trị cho InputType TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
                    btnShowPass.setImageResource(R.drawable.login_ic_sv);
                } else {
                    // Nếu mật khẩu đang ẩn, hiển thị nó
                    password.setInputType(1); // 1 là giá trị cho InputType TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_NORMAL
                    btnShowPass.setImageResource(R.drawable.login_ic_svoff);
                }
                passwordVisible = !passwordVisible;
            }
        });
    }

    private void ShowRepass(){
        btnShowRepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordVisible) {
                    // Nếu mật khẩu hiện ra, ẩn nó
                    repassword.setInputType(129); // 129 là giá trị cho InputType TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
                    btnShowRepass.setImageResource(R.drawable.login_ic_sv);
                } else {
                    // Nếu mật khẩu đang ẩn, hiển thị nó
                    repassword.setInputType(1); // 1 là giá trị cho InputType TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_NORMAL
                    btnShowRepass.setImageResource(R.drawable.login_ic_svoff);
                }
                passwordVisible = !passwordVisible; //luôn đổi trạng thái
            }
        });
    }

}