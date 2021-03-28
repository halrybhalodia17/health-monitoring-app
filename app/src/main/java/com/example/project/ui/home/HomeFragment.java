package com.example.project.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.DBHelper;
import com.example.project.R;

public class HomeFragment extends Fragment {

    SharedPreferences spref;
    private HomeViewModel homeViewModel;
    private Button update;
    private Button useful_links;
    DBHelper dbptr;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        spref = this.getActivity().getSharedPreferences("Project", Context.MODE_PRIVATE);
        dbptr = new DBHelper(this.getActivity());

        String email = spref.getString("email", "");

        int height = dbptr.getHeight(email);
        int weight = dbptr.getWeight(email);
        int age = dbptr.getAge(email);

        TextView heighttv = root.findViewById(R.id.heightVal);
        TextView weighttv = root.findViewById(R.id.weightVal);
        TextView agetv = root.findViewById(R.id.ageVal);

        heighttv.setText(height + " cm");
        weighttv.setText(weight + " kg");
        agetv.setText(age + "");

        update = root.findViewById(R.id.button3);
        useful_links = root.findViewById(R.id.button4);

        update.setOnClickListener(view -> updateActivity());
        useful_links.setOnClickListener(view -> useful_linksActivity());

        return root;
    }

    private void updateActivity() {
        Intent intent = new Intent(getActivity(), com.example.project.update.class);
        startActivity(intent);
    }

    private void useful_linksActivity() {
        Intent intent = new Intent(getActivity(), com.example.project.useful_links.class);
        startActivity(intent);
    }
}

