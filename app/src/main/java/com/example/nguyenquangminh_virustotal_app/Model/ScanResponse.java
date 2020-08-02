package com.example.nguyenquangminh_virustotal_app.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScanResponse implements Serializable {
    @SerializedName("scanid")
    String scanid;
    @SerializedName("scan_id")
    String scan_id;
    @SerializedName("sha1")
    String sha1;
    @SerializedName("url")
    String url;
    @SerializedName("resource")
    String resource;
    @SerializedName("response_code")
    String response_code;
    @SerializedName("sha256")
    String sha256;
    @SerializedName("permalink")
    String permalink;
    @SerializedName("md5")
    String md5;
    @SerializedName("verbose_msg")
    String verbose_msg;
    @SerializedName("positives")
    int positives;
    @SerializedName("total")
    int total;
    @SerializedName("scan_date")
    String scan_date;
    @SerializedName("scans")
    @Expose
    Map<String, ReportDetail> reportDetailMap = new HashMap<>();
    List<ReportDetail> reportDetailList = new ArrayList<>();

    @Override
    public String toString() {
        return "FileReportResponse{" +
                "reportDetail=" + reportDetailMap +
                '}';
    }

    public class ReportDetail{
        @SerializedName("detected")
        boolean detected;
        @SerializedName("result")
        String result;
        @SerializedName("version")
        String version;
        @SerializedName("update")
        String update;

        String engine;

        @Override
        public String toString() {
            return "ReportDetail{" +
                    "detected=" + detected +
                    ", result='" + result + '\'' +
                    ", version='" + version + '\'' +
                    ", update='" + update + '\'' +
                    ", engine='" + engine + '\'' +
                    '}';
        }

        public ReportDetail(boolean detected, String result, String version, String update, String engine) {
            this.detected = detected;
            this.result = result;
            this.version = version;
            this.update = update;
            this.engine = engine;
        }


        public String getEngine() {
            return engine;
        }

        public boolean isDetected() {
            return detected;
        }

        public String getResult() {
            return result;
        }

        public String getVersion() {
            return version;
        }

        public String getUpdate() {
            return update;
        }
    }

    public String getScanid() {
        return scanid;
    }

    public String getUrl() {
        return url;
    }

    public String getScan_id() {
        return scan_id;
    }

    public String getSha1() {
        return sha1;
    }

    public String getResource() {
        return resource;
    }

    public String getResponse_code() {
        return response_code;
    }

    public String getSha256() {
        return sha256;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getMd5() {
        return md5;
    }

    public String getVerbose_msg() {
        return verbose_msg;
    }

    public int getPositives() {
        return positives;
    }

    public int getTotal() {
        return total;
    }

    public String getScan_date() {
        return scan_date;
    }

    public Map<String, ReportDetail> getReportDetailMap() {
        return reportDetailMap;
    }

    public List<ReportDetail> getReportDetailList() {
        for (Map.Entry<String, ReportDetail> entry : reportDetailMap.entrySet()) {
            String key = entry.getKey();
            ReportDetail reportDetail = entry.getValue();
//            System.out.println(key + ":" + reportDetail);
            // do something with key and/or tab
            reportDetailList.add(new ReportDetail(
                    reportDetail.isDetected(),
                    reportDetail.getResult(),
                    reportDetail.getVersion(),
                    reportDetail.getUpdate(),
                    key));
        }
        return reportDetailList;
    }
}