package com.student.down_counter;

import android.app.AutomaticZenRule;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class SetAlarm extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Button huoguo,shafa,baiyang,ok;
    private EditText tab;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        initView();

    }

    private void initView() {

        huoguo = findViewById(R.id.huoguo);
        shafa = findViewById(R.id.shafa);
        baiyang = findViewById(R.id.baiyang);
        ok = findViewById(R.id.ok);
        tab = findViewById(R.id.tab);


        huoguo.setOnClickListener(this);
        shafa.setOnClickListener(this);
        baiyang.setOnClickListener(this);
        ok.setOnClickListener(this);





        mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.huoguo:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

                else {
                    try {

                        mediaPlayer.reset();

                        AssetManager assetManager = getAssets();

                        AssetFileDescriptor assetFileDescriptor = assetManager.openFd("火锅底料.mp3");
                        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();


                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                break;

            case R.id.shafa:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

                else {
                    try {

                        mediaPlayer.reset();

                        AssetManager assetManager = getAssets();

                        AssetFileDescriptor assetFileDescriptor = assetManager.openFd("杀伐.mp3");
                        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();


                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                break;



            case R.id.baiyang:

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

                else {
                    try {

                        mediaPlayer.reset();

                        AssetManager assetManager = getAssets();

                        AssetFileDescriptor assetFileDescriptor = assetManager.openFd("白羊.mp3");
                        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        mediaPlayer.prepare();
                        mediaPlayer.start();


                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                break;



            case R.id.ok:

                String content = tab.getText().toString();
                Toast toast1=Toast.makeText(SetAlarm.this, "选择不能为空", Toast.LENGTH_SHORT);
                Toast toast2=Toast.makeText(SetAlarm.this, "输入有误，请重新选择", Toast.LENGTH_SHORT);

                if(content.isEmpty()){
                    toast1.show();
                }


                else if ( Integer.parseInt(tab.getText().toString())!=1 && Integer.parseInt(tab.getText().toString())!=2 && Integer.parseInt(tab.getText().toString())!=3 ){
                    toast2.show();
                }


                else {
                    Intent intent = new Intent(SetAlarm.this, MainActivity.class);
                    intent.putExtra("lable", Integer.parseInt(tab.getText().toString()));
                    startActivity(intent);
                    finish();
                }
                break;




            default:
                break;


            }


    }


}
