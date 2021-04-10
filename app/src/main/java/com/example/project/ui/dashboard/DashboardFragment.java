package com.example.project.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.DBHelper;
import com.example.project.R;
import com.example.project.homepage;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    SharedPreferences spref;
    DBHelper dbptr;
    String email;

    TextView tv1, tv2, tv3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        spref = this.getActivity().getSharedPreferences("Project", Context.MODE_PRIVATE);
        dbptr = new DBHelper(this.getActivity());
        email = spref.getString("email", "");

        tv1 = root.findViewById(R.id.EditOldPass);
        tv2 = root.findViewById(R.id.EditNewPass1);
        tv3 = root.findViewById(R.id.EditNewPass2);

        Button btn = root.findViewById(R.id.changePwdBtn);
        btn.setOnClickListener(this::changePassword);

        return root;
    }

    public void changePassword(View view) {
        String actualPass = dbptr.getPassword(email);

        String oldPass = tv1.getText().toString();
        String newPass1 = tv2.getText().toString();
        String newPass2 = tv3.getText().toString();

        if (oldPass.isEmpty() || newPass1.isEmpty() || newPass2.isEmpty()) {
            Toast.makeText(this.getActivity().getApplicationContext(), "Fill all the fields.", Toast.LENGTH_SHORT).show();
        }
        else if (!actualPass.equals(oldPass)) {
            Toast.makeText(this.getActivity().getApplicationContext(), "Incorrect password.", Toast.LENGTH_SHORT).show();
        }
        else if (!newPass1.equals(newPass2)) {
            Toast.makeText(this.getActivity().getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
        }
        else if (newPass1.length() < 8 || newPass1.length() > 20) {
            Toast.makeText(this.getActivity().getApplicationContext(), "Password length should be between 8 and 20 chars.", Toast.LENGTH_SHORT).show();
        }
        else {
            dbptr.changePwd(email, newPass1);
            Toast.makeText(this.getActivity().getApplicationContext(), "Password changed...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.getActivity(), homepage.class);
            startActivity(intent);
        }
    }
}