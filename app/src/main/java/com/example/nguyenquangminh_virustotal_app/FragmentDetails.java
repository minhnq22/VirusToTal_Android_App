package com.example.nguyenquangminh_virustotal_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nguyenquangminh_virustotal_app.Model.ScanResponse;
import com.example.nguyenquangminh_virustotal_app.Utils.Utils;

public class FragmentDetails extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        return view;
    }

    ScanResponse scanResponse;

    TextView textDetailScanId;
    TextView textResource;
    TextView textScanDate;
    TextView textPermalink;
    TextView textURL;
    TextView textMD5;
    TextView textSHA1;
    TextView textSHA256;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        scanResponse = Utils.getGsonParser().fromJson(bundle.getString("data"), ScanResponse.class);

        textDetailScanId = view.findViewById(R.id.textDetailScanId);
        textResource = view.findViewById(R.id.textResource);
        textScanDate = view.findViewById(R.id.textScanDate);
        textPermalink = view.findViewById(R.id.textPermalink);
        textURL = view.findViewById(R.id.textURL);
        textMD5 = view.findViewById(R.id.textMD5);
        textSHA1 = view.findViewById(R.id.textSHA1);
        textSHA256 = view.findViewById(R.id.textSHA256);

        textDetailScanId.setText(scanResponse.getScan_id() == null ? scanResponse.getScanid() : scanResponse.getScan_id());
        textResource.setText(scanResponse.getResource());
        textScanDate.setText(scanResponse.getScan_date());
        textPermalink.setText(scanResponse.getPermalink());
        textURL.setText(scanResponse.getUrl());
        textMD5.setText(scanResponse.getMd5());
        textSHA1.setText(scanResponse.getSha1());
        textSHA256.setText(scanResponse.getSha256());
    }
}
