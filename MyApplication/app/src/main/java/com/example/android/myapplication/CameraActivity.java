package com.example.android.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView buckysImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button buckyButton = (Button) findViewById(R.id.buckysButton);
        buckysImageView = (ImageView) findViewById(R.id.buckysImageView);

        //Disable the button if the user has no camera
        if(!hasCamera())
            buckyButton.setEnabled(false);
        final Button filter=(Button) findViewById(R.id.Filters);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fil(view);
            }
        });

    }

    //Check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            buckysImageView.setImageBitmap(photo);
        }
    }
    public static Bitmap invertImage(Bitmap original){

        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(),original.getHeight(),original.getConfig());
        int A, R, G, B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();

        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                pixelColor = original.getPixel(x,y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G =Color.green(pixelColor);
                B = 255-Color.blue(pixelColor);
                finalImage.setPixel(x,y,Color.argb(A,R,G,B));
            }
        }
        return finalImage;
    }
    public void fil(View view){
        ImageView kavyasImage = (ImageView) findViewById(R.id.buckysImageView);
        Bitmap bitmap=((BitmapDrawable)kavyasImage.getDrawable()).getBitmap();
        Bitmap newPhoto = invertImage(bitmap);
        kavyasImage.setImageBitmap(newPhoto);
    }
}