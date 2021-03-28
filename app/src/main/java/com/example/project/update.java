package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    SharedPreferences spref;
    EditText height, weight, age;
    Button submitBtn;
    DBHelper dbptr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        spref = getSharedPreferences("Project", Context.MODE_PRIVATE);

        height = findViewById(R.id.editHeight);
        weight = findViewById(R.id.editWeight);
        age = findViewById(R.id.editAge);

        dbptr = new DBHelper(this);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this::updateData);
    }

    public void updateData(View view) {
        String email = spref.getString("email", "");

        if (email.isEmpty() || height.getText().toString().isEmpty() || weight.getText().toString().isEmpty() || age.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Incomplete Data.", Toast.LENGTH_LONG).show();
            return;
        }

        int heightVal = Integer.parseInt(height.getText().toString());
        int weightVal = Integer.parseInt(weight.getText().toString());
        int ageVal = Integer.parseInt(age.getText().toString());

        if (dbptr.addHWA(email, heightVal, weightVal, ageVal)) {
            Toast.makeText(getApplicationContext(), "Data Updated.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, homepage.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Some error occurred. Please check logs.", Toast.LENGTH_SHORT).show();
        }
    }
}