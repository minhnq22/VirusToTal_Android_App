package com.example.nguyenquangminh_virustotal_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenquangminh_virustotal_app.Model.ScanResponse;
import com.example.nguyenquangminh_virustotal_app.Utils.Utils;

public class FragmentDetection extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detection, container, false);
        return view;
    }

    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    ScanResponse scanResponse;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        scanResponse = Utils.getGsonParser().fromJson(bundle.getString("data"), ScanResponse.class);

        adapter = new RecycleViewAdapter(scanResponse);
        recyclerView = view.findViewById(R.id.recyclerViewDetectionResult);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }
}
