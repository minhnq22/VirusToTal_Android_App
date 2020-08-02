package com.example.nguyenquangminh_virustotal_app.Service;


import com.example.nguyenquangminh_virustotal_app.Model.ScanResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ScanService {
    @GET("vtapi/v2/url/report")
    Call<ResponseBody> getResultURLScanning(@Query("resource") String url,
                                            @Query("apikey") String apikey);

    @GET("vtapi/v2/file/report")
    Call<ResponseBody> getResultFileReport(@Query("resource") String hashcode,
                                           @Query("apikey") String apikey);

    @Multipart
    @POST("vtapi/v2/file/scan")
    Call<ScanResponse> getResultFileScanning(@Part MultipartBody.Part file,
                                             @Query("apikey") String apikey);
}
