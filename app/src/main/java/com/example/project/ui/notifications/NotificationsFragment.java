package com.example.project.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.DBHelper;
import com.example.project.R;
import com.example.project.homepage;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    SharedPreferences spref;
    EditText bp, sugar, calorie;
    Button submitBtn, getDataBtn;
    DBHelper dbptr;
    String email;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        super.onCreate(savedInstanceState);

        spref = this.getActivity().getSharedPreferences("Project", Context.MODE_PRIVATE);
        dbptr = new DBHelper(this.getActivity());
        email = spref.getString("email", "");

        bp = root.findViewById(R.id.bp_level);
        sugar = root.findViewById(R.id.sugar_value);
        calorie = root.findViewById(R.id.calorie_intake);

        submitBtn = root.findViewById(R.id.submit_Btn);
        submitBtn.setOnClickListener(this::updateData);

        getDataBtn = root.findViewById(R.id.getDataBtn);
        getDataBtn.setOnClickListener(this::getData);

        return root;
    }

    public void updateData(View view) {
        if (this.email.isEmpty() || bp.getText().toString().isEmpty() || sugar.getText().toString().isEmpty() || calorie.getText().toString().isEmpty()) {
            Toast.makeText(this.getActivity().getApplicationContext(), "Incomplete Data.", Toast.LENGTH_LONG).show();
            return;
        }

        int Bloodpressure = Integer.parseInt(bp.getText().toString());
        int Sugar = Integer.parseInt(sugar.getText().toString());
        int Calorie_intake = Integer.parseInt(calorie.getText().toString());

        if (dbptr.addBSC(this.email, Bloodpressure, Sugar, Calorie_intake)) {
            Toast.makeText(this.getActivity().getApplicationContext(), "Data Updated.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.getActivity(), homepage.class);
            startActivity(intent);
        } else {
            Toast.makeText(this.getActivity().getApplicationContext(), "Some error occurred. Please check logs.", Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(View view) {
        Log.d("debug", "Hello" + this.email);
        String rows = dbptr.getdata(this.email);
        Log.d("debug", rows);
    }
}