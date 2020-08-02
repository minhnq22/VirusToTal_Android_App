package com.example.nguyenquangminh_virustotal_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nguyenquangminh_virustotal_app.APIClient.APIClient;
import com.example.nguyenquangminh_virustotal_app.Model.ScanResponse;
import com.example.nguyenquangminh_virustotal_app.Service.ScanService;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_about:
                Toast.makeText(getApplicationContext(), "Nguyen Quang Minh - B16DCAT107", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_item_exit:
                System.exit(0);
                break;
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_result);

        progressBarLoading = findViewById(R.id.progressBarLoading);
        Intent intent = getIntent();
        String apikey = "cb7bf42b6f4f76249770a0649e285aec1940bf42842155ed48ff87bc6a3320bb";
        ScanService scanService = APIClient.getClient().create(ScanService.class);

        if (intent.getStringExtra("url") != null) {
            scanURL(intent, scanService, apikey);
        } else {
            scanFile(intent, scanService, apikey);
        }

    }
    public void showFragment(String data) {
        progressBarLoading.setVisibility(View.GONE);
        TabLayout tabLayout = findViewById(R.id.tab_layout_result);
        ViewPager viewPager = findViewById(R.id.view_pager_result);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPagerAdapterResult adapter = new ViewPagerAdapterResult(fragmentManager, ViewPagerAdapterResult.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, data);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void scanURL(Intent intent, ScanService scanService, String apikey) {
        //Scan URL
        String url = intent.getStringExtra("url");

        scanService.getResultURLScanning(url, apikey).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    showFragment(response.body().string());
                } catch (IOException e) {
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Toast ERROR
                System.out.println(t.getMessage());
            }
        });
    }

    public void scanFile(Intent intent, ScanService scanService, String apikey) {
        //Scan File
        String filePath = intent.getStringExtra("filePath");
        filePath = filePath.replaceFirst("/document/raw:", "");
        File file = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        final ScanResponse[] scanResponses = new ScanResponse[1];
        scanService.getResultFileScanning(multipartBody, apikey).enqueue(new Callback<ScanResponse>() {
            @Override
            public void onResponse(Call<ScanResponse> call, Response<ScanResponse> response) {
                scanResponses[0] = response.body();
                //get File Report
                scanService.getResultFileReport(scanResponses[0].getMd5(), apikey).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            showFragment(response.body().string());
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<ScanResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
}