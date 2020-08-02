package com.example.nguyenquangminh_virustotal_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenquangminh_virustotal_app.Model.ScanResponse;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    ScanResponse scanResponse;
    List<ScanResponse.ReportDetail> reportDetail;

    public RecycleViewAdapter(ScanResponse scanResponse) {
        this.scanResponse = scanResponse;
        this.reportDetail = this.scanResponse.getReportDetailList();
//        Log.d("CHECK",  "RecycleViewAdapter: " + reportDetail.size());
//        Log.d("CHECK",  "RecycleViewAdapter: " + reportDetail);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.results_detection, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.engine.setText(reportDetail.get(position).getEngine());
        int color = Color.parseColor(reportDetail.get(position).isDetected() ? "#ff1f4a" : "#22b573");
        holder.detected.setImageResource(reportDetail.get(position).isDetected() ? R.drawable.detected : R.drawable.undetected);
        holder.detected.setColorFilter(color);
        holder.detail.setText(reportDetail.get(position).getResult() != null ? reportDetail.get(position).getResult() : "Undetected");
    }

    @Override
    public int getItemCount() {
        return reportDetail.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView engine;
        ImageView detected;
        TextView detail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            engine = itemView.findViewById(R.id.engine);
            detected = itemView.findViewById(R.id.detected);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}