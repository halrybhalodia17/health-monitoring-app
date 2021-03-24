package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login1 extends Activity {
    EditText Email, Password;
    Button submitBtn;
    DBHelper dbptr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);

        Email = findViewById(R.id.editTextEmail);
        Password = findViewById(R.id.editTextPassword);
        submitBtn = findViewById(R.id.loginBtn);

        dbptr = new DBHelper(this);
        submitBtn.setOnClickListener(this::loginUser);
    }

    public void loginUser(View view) {
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all the fields.", Toast.LENGTH_SHORT).show();
        }
        else {
            String pwd = dbptr.getPassword(email);

            if (pwd.equals("")) {
                Toast.makeText(getApplicationContext(), "User not registered.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, register1.class);
                startActivity(intent);
            }
            else if (pwd.equals(password)) {
                Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, homepage.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Please check your email/password.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
