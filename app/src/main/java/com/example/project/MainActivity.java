package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreferences = getSharedPreferences("Project", Context.MODE_PRIVATE);
        String user = sharedpreferences.getString("email", "notpresent");

        if (!user.equals("notpresent")) {
            Toast.makeText(getApplicationContext(), user, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, homepage.class);
            startActivity(intent);
        }

        login = findViewById(R.id.button);
        login.setOnClickListener(view -> loginActivity());

        register = findViewById(R.id.button2);
        register.setOnClickListener(view -> registerActivity());
    }

    private void loginActivity() {
        Intent intent = new Intent(this, login1.class);
        startActivity(intent);
    }

    private void registerActivity() {
        Intent intent = new Intent(this,register1.class);
        startActivity(intent);
    }
}