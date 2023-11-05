package com.example.apptourbooking.Activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apptourbooking.Admin.ManageAccount;
import com.example.apptourbooking.Database.UserDAO;
import com.example.apptourbooking.Domain.UserInfo;
import com.example.apptourbooking.R;

public class UpdateProfileActivity extends AppCompatActivity {
    private EditText id, fullname, username, password, token, role;
    private Button btn_save, btn_exits;
    private UserInfo userInfo;
    private UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        init();


        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("update_profile_account");
        id.setText(""+ userInfo.getUserId());
        username.setText(userInfo.getUserName() );
        fullname.setText(userInfo.getFullName() );
        password.setText(userInfo.getPassword() );
        token.setText(userInfo.getToken() );
        role.setText(""+ userInfo.getRole() );

        userDAO = new UserDAO(this);


        Update();
        Exits();
    }

    private void init(){
        id = findViewById(R.id.edit_profile_id);
        fullname = findViewById(R.id.edit_profile_fullname);
        username = findViewById(R.id.edit_profile_username);
        password = findViewById(R.id.edit_profile_password);
        token = findViewById(R.id.edit_profile_token);
        role = findViewById(R.id.edit_profile_role);
        btn_save = findViewById(R.id.edit_profile_btn_save);
        btn_exits = findViewById(R.id.edit_profile_btn_exits);
    }
    private void Update(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo u = new UserInfo();
                u.setUserName(username.getText().toString());
                u.setFullName(fullname.getText().toString());
                u.setPassword(password.getText().toString());
                u.setToken(token.getText().toString());
                u.setRole(Integer.parseInt(role.getText().toString()));

                long kq =  userDAO.updateUser(u);
                if(kq == -1){
                    Toast.makeText(UpdateProfileActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }if (kq==1){

                    Intent intent = new Intent(UpdateProfileActivity.this, ProfileActivity.class);
                    intent.putExtra("profile_account",u );
                    startActivity(intent);

                    Toast.makeText(UpdateProfileActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Exits(){
        btn_exits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}