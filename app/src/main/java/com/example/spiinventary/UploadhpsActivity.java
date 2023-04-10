package com.example.spiinventary;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UploadhpsActivity extends AppCompatActivity {

    ImageView uploadHpsImg;
    Button saveButtonHps;
    EditText uploadHpsId, uploadHpsNombre, uploadHpsCavidades, uploadHpsMaterial;
    String imageHpsUrl;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadhps);

        uploadHpsImg = findViewById(R.id.uploadHps);
        saveButtonHps = findViewById(R.id.saveButtonHps);
        uploadHpsId = findViewById(R.id.uploadHpsId);
        uploadHpsNombre = findViewById(R.id.uploadHpsNombre);
        uploadHpsCavidades = findViewById(R.id.uploadHpsCavidades);
        uploadHpsMaterial = findViewById(R.id.uploadHpsMaterial);

    }
}