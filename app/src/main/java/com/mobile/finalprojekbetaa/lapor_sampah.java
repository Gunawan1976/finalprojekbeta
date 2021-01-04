package com.mobile.finalprojekbetaa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;

public class lapor_sampah extends AppCompatActivity implements DialogImageOptionsListener, View.OnClickListener {

    public final static String TAG_DATA_INTENT = "data_user";
    public final static int REQUEST_CAMERA = 101;
    public final static int REQUEST_GALLERY = 202;
    public final static int PICK_CAMERA = 1001;
    public final static int PICK_GALLERY = 2002;
    private UserDao userdao;
    private EditText etjudul,etdesk,etalamat;
    private Button button;
    private File fileImage;
    private SampahEntity sampahEntity;
    private ImageView imageView;
    private RequestOptions requestOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor_sampah);
        //mengirim data ke room
        userdao = CrudRoomApp.getInstance().getDataBase().userDao();
        if (getIntent()!=null){
            int id = getIntent().getIntExtra(TAG_DATA_INTENT, 0);
            sampahEntity = userdao.findbyid(id);
        }else {
            sampahEntity = new SampahEntity();
        }

        requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(false)
                .centerCrop()
                .circleCrop();

        etjudul = findViewById(R.id.etjudul);
        etdesk = findViewById(R.id.etdeskripsi);
        etalamat = findViewById(R.id.etalamat);
        button = findViewById(R.id.btn_tambah);
        //mengirim
        imageView = findViewById(R.id.imagecam);

        imageView.setOnClickListener(this);

    }

    @Override
    public void onCameraClick() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }

    }

    private void openCamera() {
        try {
            fileImage = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg",
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            Uri imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", fileImage);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, PICK_CAMERA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGalleryClick() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY);
        }
    }

    @SuppressLint("IntentReset")
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_GALLERY);
    }

    private void loadImage(File image) {
        if (image == null) return;

        Glide.with(this)
                .asBitmap()
                .apply(requestOptions)
                .load(image)
                .into(imageView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            openCamera();
        } else if (requestCode == REQUEST_GALLERY) {
            openGallery();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //mengirim
            case R.id.btn_tambah:
                addOrUpdate();
                if (sampahEntity.getId()==0){
                    userdao.insertdata(sampahEntity);
                }else {
                    userdao.update(sampahEntity);
                }
                break;
                //mengirim
            case R.id.imagecam:
            default:
                new CustomDialogImageOptions(lapor_sampah.this,lapor_sampah.this)
                        .show();
        }
    }
    //mengirim
    private void addOrUpdate() {
        sampahEntity.setJudul(etjudul.getText().toString());
        sampahEntity.setDeskripsi(etdesk.getText().toString());
        sampahEntity.setAlamat(etalamat.getText().toString());
    }
    //mengririm
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String path;
            if (requestCode == PICK_GALLERY) {
                path = getRealPathFromUri(data.getData());
            } else {
                path = fileImage.getAbsolutePath();
            }

            loadImage(new File(path));
        }
    }
    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null
                , MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
}