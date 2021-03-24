package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register1 extends Activity {

    EditText Name, Email, Pass, Pass1;
    Button submitBtn;
    DBHelper dbptr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1);

        Name = findViewById(R.id.editTextName);
        Email = findViewById(R.id.editTextEmail);
        Pass = findViewById(R.id.editTextPassword);
        Pass1 = findViewById(R.id.editTextPassword1);
        submitBtn = findViewById(R.id.registerBtn);

        dbptr = new DBHelper(this);
        submitBtn.setOnClickListener(this::registerUser);
    }

    public void registerUser(View view) {
        String name = Name.getText().toString();
        String email = Email.getText().toString();
        String pass = Pass.getText().toString();
        String pass1 = Pass1.getText().toString();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || pass1.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill all the fields.", Toast.LENGTH_SHORT).show();
        }
        else if (!pass.equals(pass1)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dbptr.insertUser(name, email, pass)) {
                Toast.makeText(getApplicationContext(), "User Registered Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, login1.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Error in registering user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
