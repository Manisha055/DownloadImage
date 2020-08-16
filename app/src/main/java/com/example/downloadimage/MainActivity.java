package com.example.downloadimage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    public void downloadImage(View view){
       DownloadingImage task=new DownloadingImage();
      Bitmap bitmap;
      try {
          bitmap=task.execute("https://i.ytimg.com/vi/mq4FNZFwRjo/maxresdefault.jpg").get();
          imageView.setImageBitmap(bitmap);
      }catch (Exception e){
          e.printStackTrace();

      }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
    }
    @SuppressLint("StaticFieldLeak")
    public  class DownloadingImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();
                connection.connect();
                Bitmap bitmap;
                try (InputStream in = connection.getInputStream()) {
                    bitmap = BitmapFactory.decodeStream(in);
                }
                return bitmap;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
    }
}