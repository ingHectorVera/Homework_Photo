package com.example.hectorvera.homework_photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    TextView txtView_2;
    ImageView imageFromCamera;
    Bundle extras;
    String iPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageFromCamera = ((ImageView) findViewById(R.id.imageFromCamera));
        Intent mActivity2 = getIntent();
        txtView_2 = ((TextView) findViewById(R.id.txtView_2));
        txtView_2.setText(mActivity2.getStringExtra("iTakePicture"));
        displayCamera();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            extras = data.getExtras();
            Bitmap bImageFromCamera = (Bitmap) extras.get("data");
            imageFromCamera.setImageBitmap(bImageFromCamera);
        }
    }

    private void displayCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!= null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("sTxtView_2", txtView_2.getText().toString());
        //outState.putBundle("sExtras",extras);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtView_2.setText(savedInstanceState.getString("sTxtView_2","Default"));
        //Bitmap bImageFromCamera = (Bitmap)savedInstanceState.getBundle("sExtras").get("data");
        //imageFromCamera.setImageBitmap(bImageFromCamera);
    }
}
