package com.example.nguyenquangminh_virustotal_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentURL extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_url, container, false);
        return view;
    }

    Button searchButton;
    EditText URLeditText;
    Context context;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchButton = view.findViewById(R.id.button_search_url);
        URLeditText = view.findViewById(R.id.edittext_url);
        context = this.getContext();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = URLeditText.getText().toString();

                Intent intent = new Intent();
                intent.setClass(context, ResultActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
}
