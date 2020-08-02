package com.example.nguyenquangminh_virustotal_app;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nguyenquangminh_virustotal_app.Model.ScanResponse;
import com.example.nguyenquangminh_virustotal_app.Utils.Utils;

import java.io.File;


public class FragmentSummary extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        return view;
    }

    ProgressBar progressBar;
    ScanResponse scanResponse;
    TextView textViewPosivite;
    TextView textViewTotal;
    TextView textScanId;
    TextView textVerboseMsg;
    TextView textTimeScan;

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        progressBar = view.findViewById(R.id.progressBar);
        textViewPosivite = view.findViewById(R.id.text_summary_positive);
        textViewTotal = view.findViewById(R.id.text_summary_total);
        textScanId = view.findViewById(R.id.text_scanId);
        textVerboseMsg = view.findViewById(R.id.text_verbose_msg);
        textTimeScan = view.findViewById(R.id.text_timeScan);

        scanResponse = Utils.getGsonParser().fromJson(bundle.getString("data"), ScanResponse.class);
        int positivepersen = (int) (100 * (float) scanResponse.getPositives() / (float)scanResponse.getTotal());
        if(positivepersen == 0) {
            textViewPosivite.setTextColor(Color.parseColor("#22b573"));
        } else {
            textViewPosivite.setTextColor(Color.parseColor("#E91E63"));
        }
        textViewPosivite.setText(""+scanResponse.getPositives());
        textViewTotal.setText("/"+scanResponse.getTotal());
        progressBar.setProgress(positivepersen);

        textVerboseMsg.setText(scanResponse.getVerbose_msg());
        textScanId.setText(scanResponse.getScan_id());
        textTimeScan.setText(scanResponse.getScan_date());

        System.out.println(scanResponse.getResource());
    }
}
