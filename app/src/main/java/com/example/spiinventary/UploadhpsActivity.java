package com.example.spiinventary;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadHpsImg.setImageURI(uri);
                        } else {
                            Toast.makeText(UploadhpsActivity.this, "No ha seleccionado alguna imagen.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadHpsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("imageHps/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButtonHps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pendiente
            }
        });
    }

    public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Hps Moldes").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadhpsActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImgHps = uriTask.getResult();
                imageHpsUrl = urlImgHps.toString();
                uploadHpsData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadHpsData(){
        String hpsid = uploadHpsId.getText().toString();
        String hpsnombre = uploadHpsNombre.getText().toString();
        String hpscav = uploadHpsCavidades.getText().toString();
        String hpsmaterial = uploadHpsMaterial.getText().toString();

        DataClass dataClass = new DataClass(hpsid,hpsnombre,hpscav,hpsmaterial,imageHpsUrl);

        FirebaseDatabase.getInstance().getReference("Hps Moldes").child(hpsid).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UploadhpsActivity.this, "Guardado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadhpsActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}