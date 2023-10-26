package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apptourbooking.Admin.AdminActivity;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.R;

public class LoginActivity extends AppCompatActivity {

    ImageView imgbackLogin, imgHienMk;
    private CheckBox rememberCheckbox;
    private boolean passwordVisible = false;
    ConstraintLayout cslRegister;
    private EditText username, password;
    private Button btn_login;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Anhxa();

        Back();
        MoveToRegister();

        Login();

        ShowPassword();

        SharedRemember();

    }

    private void Anhxa(){
        cslRegister = (ConstraintLayout) findViewById(R.id.csl_register);
        imgbackLogin = (ImageView) findViewById(R.id.img_login_back);
        username = findViewById(R.id.login_editText_username);
        password = findViewById(R.id.login_editText_password);
        btn_login = findViewById(R.id.btn_login);
        imgHienMk = findViewById(R.id.login_img_hienmk);
        rememberCheckbox = findViewById(R.id.login_cb_remember);
    }

    private void Back(){
        imgbackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void MoveToRegister(){
        cslRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void ShowPassword(){
        imgHienMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordVisible) {
                    // Nếu mật khẩu hiện ra, ẩn nó
                    password.setInputType(129); // 129 là giá trị cho InputType TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
                    imgHienMk.setImageResource(R.drawable.login_ic_sv);
                } else {
                    // Nếu mật khẩu đang ẩn, hiển thị nó
                    password.setInputType(1); // 1 là giá trị cho InputType TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_NORMAL
                    imgHienMk.setImageResource(R.drawable.login_ic_svoff);
                }
                passwordVisible = !passwordVisible;
            }
        });
    }
    private void Login(){
        DB =  new DatabaseHelper(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals("") ){
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        if(rememberCheckbox.isChecked()){
                            SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("RememberUsername", user);
                            editor.putString("RememberPassword", pass);
                            editor.putBoolean("rememberMe", true);
                            editor.apply();
                        } else {
                            SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("RememberUsername");
                            editor.remove("RememberPassword");
                            editor.putBoolean("rememberMe", false);
                            editor.apply();
                        }
                        if(user.equals("anhluong0110")){
                            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        }else {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void SharedRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);

        if (rememberMe) {
            // Nếu người dùng đã chọn "Lưu tài khoản và mật khẩu", bạn có thể đọc tài khoản và mật khẩu từ SharedPreferences
            String savedUsername = sharedPreferences.getString("RememberUsername", "");
            String savedPassword = sharedPreferences.getString("RememberPassword", "");

            // Đặt tài khoản và mật khẩu vào trường nhập liệu
            username.setText(savedUsername);
            password.setText(savedPassword);
            rememberCheckbox.setChecked(true);
        }
    }
}