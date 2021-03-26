package com.example.project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.login1;
import com.example.project.register1;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button update;
    private Button useful_links;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);
        return root;

        update = findViewById(R.id.button);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateActivity();
            }
        });
        useful_links = findViewById(R.id.button4);

        useful_links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useful_linksActivity();
            }
        });
    }


}
    private void updateActivity() {
        Intent intent = new Intent(this, update.class);
        startActivity(intent);
    }

    private void useful_linksActivity() {
        Intent intent = new Intent(this, useful_links.class);
        startActivity(intent);
    }
}

