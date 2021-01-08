package com.leanhquan.notemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leanhquan.notemanagementsystem.Common.Common;
import com.leanhquan.notemanagementsystem.Model.User;
import com.rey.material.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private EditText            edtUsername, edtPassword;
    private TextView            btnSignUp;
    private Button              btnLogin;
    private CheckBox            cbRemember;
    private FirebaseDatabase    database;
    private DatabaseReference   userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = sharedPreferences.getString("remember", "");

        if (checkbox.equals("true")){
            Intent homeInten = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeInten);
        } else if (checkbox.equals("false")) {
            return;
        }

        database = FirebaseDatabase.getInstance();
        userDB = database.getReference("user");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();
                if (!user.isEmpty() && !pass.isEmpty()) {
                    login(user, pass);
                } else {
                    Toast.makeText(MainActivity.this, "Username or password does not empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterInten = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(RegisterInten);
            }
        });

        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("remember", "true");
                } else if (!buttonView.isChecked()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("remember", "false");
                }
            }
        });


    }

    private void login(final String user, final String pass) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please waiting...");
        dialog.show();

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(user).exists()){
                    dialog.dismiss();
                    User userLogin = snapshot.child(user).getValue(User.class);

                    if (userLogin.getPassword().equals(pass)){
                        Intent homeInten = new Intent(MainActivity.this, HomeActivity.class);
                        Common.currentUser = userLogin;
                        startActivity(homeInten);
                        finish();
                    }else {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "User does not exits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void init() {
        edtUsername = findViewById(R.id.edtUsernameLogin);
        edtPassword = findViewById(R.id.edtPasswordLogin);
        cbRemember = findViewById(R.id.cbRememberLogin);
        btnSignUp = findViewById(R.id.txtSignUp);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
