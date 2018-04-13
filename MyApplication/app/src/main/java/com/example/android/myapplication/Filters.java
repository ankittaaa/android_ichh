package com.example.android.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Filters extends AppCompatActivity {
    ImageView kavyasImage;
    Drawable kavyasFace;
    Bitmap bitmapImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        Intent intent=getIntent();
        intent.getExtras();
        kavyasImage = (ImageView) findViewById(R.id.kavyasImage);
        bitmapImage = ((BitmapDrawable)kavyasFace).getBitmap();
        Bitmap newPhoto = invertImage(bitmapImage);
        kavyasImage.setImageBitmap(newPhoto);
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
}
