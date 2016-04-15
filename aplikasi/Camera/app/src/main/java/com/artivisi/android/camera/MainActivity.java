package com.artivisi.android.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnAmbilGambar;
    ImageView ivGambar;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAmbilGambar = (Button) findViewById(R.id.btnAmbil);
        ivGambar = (ImageView) findViewById(R.id.ivGambar);

        btnAmbilGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                saveToStorage(intent);
                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234){
            switch (resultCode) {
                case Activity.RESULT_OK :
                    String fullPath = Environment
                            .getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DCIM)
                            .getAbsolutePath() + File.separator
                            + fileName;

                    loadToImageView(fullPath, ivGambar);
                    break;
                default:
                    break;
            }
        }
    }

    private void saveToStorage(Intent i) {
        File dir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);

        fileName = new Random().nextInt() + "-file.jpg";

        File output = new File (dir, fileName);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
    }

    private void loadToImageView(String filePath, ImageView iv) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        iv.setImageBitmap(bitmap);
    }
}
