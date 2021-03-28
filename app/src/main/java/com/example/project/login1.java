package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login1 extends Activity {
    EditText Email, Password;
    Button submitBtn;
    DBHelper dbptr;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login1);
        sharedpreferences = getSharedPreferences("Project", Context.MODE_PRIVATE);

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
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("email", email);
                editor.commit();
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
