package com.example.recyclowaste;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclowaste.model.Advertisment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostAd extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button btnChoooseImage;
    private Uri mImageUri;
    private ProgressBar progressBar;
    StorageReference storageRef;
    DatabaseReference dbRef;
    String key_username;
    TextView et_title;
    TextView et_description;
   // TextView et_image;
    TextView et_price;
    TextView et_quantity;
    ImageView productImg;
    Advertisment ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad);

        et_title = findViewById(R.id.et_post_prdtTitle);
        et_description = findViewById(R.id.et_post_prdt_Desc);
        et_price = findViewById(R.id.post_prdtPrice);
        et_quantity = findViewById(R.id.et_post_prdtQuantity);
        btnChoooseImage = findViewById(R.id.btn_post_addImage);
        progressBar = findViewById(R.id.progressBar);
        productImg = findViewById(R.id.img_displayProduct);
    }

    private void ClearControls(){
        et_quantity.setText("");
        et_title.setText("");
        et_description.setText("");
        et_quantity.setText("1");
    }

    public void ChooseImage(View view){
        openFileChooser();
    }

    public void PostAd(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Advertisment");
        storageRef = FirebaseStorage.getInstance().getReference().child("Advertisment");
        try{
            //Validation for empty form


            /*ad.setUsername(key_username);
            ad.setTitle(et_title.getText().toString().trim());
            ad.setDescription(et_description.getText().toString().trim());
            ad.setPrice(Float.parseFloat(et_price.getText().toString().trim()));
            ad.setQuantity(Integer.parseInt(et_quantity.getText().toString().trim()));*/




            Toast.makeText(getApplicationContext(),"Ad posted!",Toast.LENGTH_SHORT).show();
            ClearControls();
        }
        catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Please enter a correct amount for the price",Toast.LENGTH_SHORT).show();

        }

        //Getting Image and uploading data to database

        if(mImageUri != null){
            StorageReference fileReference = storageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();  //Delays reset of progress bar by 5s
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },500);
                    Toast.makeText(getApplicationContext(),"Upload Successfull!",Toast.LENGTH_SHORT).show();

                    Advertisment ad = new Advertisment(et_title.getText().toString().trim(),et_description.getText().toString().trim(),taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(),Float.parseFloat(et_price.getText().toString().trim()),Integer.parseInt(et_quantity.getText().toString().trim()));
                    String uploadId = dbRef.push().getKey();
                    dbRef.child(uploadId).setValue(ad);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);

                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"No File Selected",Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResult.launch(intent);
       // startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        mImageUri = data.getData();

                        productImg.setImageURI(mImageUri);

                    }
                }
            }
    );

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();
        }
    }*/

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

/*    private void uploadFile(){

        if(mImageUri != null){
            StorageReference fileReference = storageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();  //Delays reset of progress bar by 5s
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },5000);
                    Toast.makeText(getApplicationContext(),"Upload Succesfull!",Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);

                }
            })
        }
        else{
            Toast.makeText(getApplicationContext(),"No File Selected",Toast.LENGTH_SHORT).show();
        }

    }*/
}