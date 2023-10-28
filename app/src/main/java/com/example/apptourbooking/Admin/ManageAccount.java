package com.example.apptourbooking.Admin;

import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_FULLNAME;
import static com.example.apptourbooking.Database.DatabaseHelper.TB_USER_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptourbooking.Activitis.LoginActivity;
import com.example.apptourbooking.Activitis.RegisterActivity;
import com.example.apptourbooking.Adapter.ManagerAccountAdapter;
import com.example.apptourbooking.DAO.UserDAO;
import com.example.apptourbooking.Database.DatabaseHelper;
import com.example.apptourbooking.Domain.UserInfo;
import com.example.apptourbooking.R;

import java.util.ArrayList;
import java.util.List;

public class ManageAccount extends AppCompatActivity {

    private ImageView btn_back;
    private EditText edt_fullname, edt_username, edt_password, edt_role;
    private Button btn_add, btn_delete, btn_update, btn_load;

    ListView lv;
    List<String> ds = new ArrayList<>();
    ArrayAdapter<String> adapter;

    List<UserInfo> us ;
    ManagerAccountAdapter managerAccountAdapter;
    UserDAO userDAO;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        Init();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userDAO = new UserDAO(this);
        ds = userDAO.getAllUser();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ds);
//        lv.setAdapter(adapter);

        us = userDAO.getAllUserToAccount();
        managerAccountAdapter = new ManagerAccountAdapter(ManageAccount.this, us);
        lv.setAdapter(managerAccountAdapter);

        Insert();
        Delete();
        Update();
        Load();

    }

    private void Init(){
        btn_back = findViewById(R.id.mAccount_back);
        edt_fullname = findViewById(R.id.mAccount_edt_fullname);
        edt_username = findViewById(R.id.mAccount_edt_username);
        edt_password = findViewById(R.id.mAccount_edt_password);
        edt_role = findViewById(R.id.mAccount_edt_role);
        lv= findViewById(R.id.mAccount_lv);
        btn_add = findViewById(R.id.mAccount_btn_add);
        btn_delete = findViewById(R.id.mAccount_btn_delete);
        btn_update = findViewById(R.id.mAccount_btn_update);
        btn_load = findViewById(R.id.mAccount_btn_load);
    }
    private void Insert(){

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserInfo u = new UserInfo();
                u.setUserName(edt_username.getText().toString());
                u.setFullName(edt_fullname.getText().toString());
                u.setPassword(edt_password.getText().toString());
                u.setRole(Integer.parseInt(edt_role.getText().toString()));

                long kq =  userDAO.insertUser(u);
                if(kq == -1){
                    Toast.makeText(ManageAccount.this, "Add failed", Toast.LENGTH_SHORT).show();
                }if (kq==1){
                    Toast.makeText(ManageAccount.this, "Add successfully", Toast.LENGTH_SHORT).show();
                }

//                DB = new DatabaseHelper(this);
//                String user = edt_username.getText().toString();
//                String name = edt_fullname.getText().toString();
//                String pass = edt_password.getText().toString();
//                if(user.equals("")||name.equals("") || pass.equals("") ){
//                    Toast.makeText(ManageAccount.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
//                } else {
//                        Boolean checkuser = DB.checkusername(user);
//                        if(checkuser == false){
//                            Boolean insert = DB.InsertData(user,name,pass);
//                            if(insert == true){
//                                Toast.makeText(ManageAccount.this, "Register successfully", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(ManageAccount.this, "Register failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else {
//                            Toast.makeText(ManageAccount.this, "User already exitsts! Please sign in ", Toast.LENGTH_SHORT).show();
//                        }
//                }
            }
        });
    }

    private void Delete(){
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kq = userDAO.deleteUser(edt_username.getText().toString());
                if(kq == -1){
                    Toast.makeText(ManageAccount.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }if (kq==1){
                    Toast.makeText(ManageAccount.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Update(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo u = new UserInfo();
                u.setUserName(edt_username.getText().toString());
                u.setFullName(edt_fullname.getText().toString());
                u.setPassword(edt_password.getText().toString());
                u.setRole(Integer.parseInt(edt_role.getText().toString()));

                long kq =  userDAO.updateUser(u);
                if(kq == -1){
                    Toast.makeText(ManageAccount.this, "Update failed", Toast.LENGTH_SHORT).show();
                }if (kq==1){
                    Toast.makeText(ManageAccount.this, "Update successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Load(){
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us.clear();
                us = userDAO.getAllUserToAccount();
                managerAccountAdapter = new ManagerAccountAdapter(ManageAccount.this, us);
                lv.setAdapter(managerAccountAdapter);
            }
        });
    }
}