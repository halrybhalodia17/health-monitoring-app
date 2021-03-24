package com.example.project.ui.links;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.project.R;
import com.example.project.ui.dashboard.DashboardViewModel;
import com.example.project.ui.home.HomeViewModel;

public class linksFragment extends Fragment {

    private linksviewmodel  linksviewmodel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        linksviewmodel =
                new ViewModelProvider(this).get(linksviewmodel.class);
        View root = inflater.inflate(R.layout.fragment_links, container, false);
        final TextView textView = root.findViewById(R.id.textview5);
        linksviewmodel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
