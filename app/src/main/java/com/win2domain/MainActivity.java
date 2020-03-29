package com.win2domain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_CODE_RECORD_VIDEO =1101;
    private static final String TAG = "[MainActivity]";
    Button start;
    Button settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start_button);
        settings = findViewById(R.id.settings_button);
        start.setOnClickListener(this);
        settings.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG,"[onClick] start");
        switch (view.getId()){
            case R.id.start_button:
                SimpleDateFormat date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                String FileTime =date.format(new Date()).toString();
                String filePath = Environment.getExternalStorageDirectory() + "/video/" +FileTime + ".mp4";   // 保存路径
                Uri uri = Uri.fromFile(new File(filePath));   // 将路径转换为Uri对象
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);  // 表示跳转至相机的录视频界面
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);    // MediaStore.EXTRA_VIDEO_QUALITY 表示录制视频的质量，从 0-1，越大表示质量越好，同时视频也越大
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);    // 表示录制完后保存的录制，如果不写，则会保存到默认的路径，在onActivityResult()的回调，通过intent.getData中返回保存的路径
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);   // 设置视频录制的最长时间
                this.startActivityForResult(intent, REQUEST_CODE_RECORD_VIDEO);  // 跳转
                Log.d(TAG,"[onClick] end");
                break;
            case R.id.settings_button:
                Intent settingIntent = new Intent();
                settingIntent.setClass(this,SettingsActivity.class);
                startActivity(settingIntent);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"[onActivityResult] requestCode = "+requestCode+" resultCode = "+resultCode);
        Log.d(TAG,"[onActivityResult] path = "+data.getData().getPath()+"  Activity.RESULT_OK = "+ Activity.RESULT_OK);
        if(resultCode == RESULT_OK){

        }


    }
}
