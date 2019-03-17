package com.example.basiclistapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView pictureDescriptionTextView = findViewById(R.id.pictureDescTextView);

        //Get the intent that ran this activity
        Intent in = getIntent();
        //Store the array index value that is passed though via putExtra, set the default value to -1 if nothing is passed
        int index = in.getIntExtra("com.example.basiclistapp.INDEX", -1);

        //If index holds a value greater than -1 (i.e an array item value has been returned.
        if (index > -1) {
            //Store the picture that will be passed into scale image based on index number
            int pic = getImg(index);
            //Find the id of the image view and store in img
            ImageView img = findViewById(R.id.imageView);
            //Scale the image by passing it into scaleImg with the ID of the ImageView (img) and the picture to be processed (pic)
            scaleImg(img, pic);
            pictureDescriptionTextView.setText(setDescription(index));
        }
    }
    //Method which gets an image based on the index number of the array button which was clicked
    private int getImg(int index) {
        //Switch statement is used to select an image depending on the array value
        switch (index) {
            case 0: return R.drawable.purchase1;
            case 1: return R.drawable.purchase2;
            case 2: return R.drawable.purchase3;
            case 3: return R.drawable.purchase4;
            //Returns -1 by default if index value is anything apart from the defined ones.
            default: return -1;
        }
    }

    private String setDescription(int index) {
        String array[] = getResources().getStringArray(R.array.pictureDescriptions);
        switch (index) {
            case 0: return array[0];
            case 1: return array[1];
            case 2: return array[2];
            case 3: return array[3];
            default: return "Error";
        }
    }

    //Method which scales the images
    private void scaleImg(ImageView img, int pic) {
        //Creates a display class into the screen variable, gets the display size
        Display screen = getWindowManager().getDefaultDisplay();
        //Bitmap factory options class is created and stored in the options variable
        BitmapFactory.Options options = new BitmapFactory.Options();

        //Setting to true allows the bitmap to be queried without allocating memory for the pixels, improving memory usage.
        options.inJustDecodeBounds = true;
        //Passes the image into the Bitmap factory using getResources and with the options declared.
        BitmapFactory.decodeResource(getResources(), pic, options);

        //Get the width of the image
        int imgWidth = options.outWidth;
        //Get the width of the screen.
        int screenWidth = screen.getWidth();

        //If the image width is greater than screen width then continue
        if (imgWidth > screenWidth) {
            //Calculates a scaling ratio using a round of image width and screen width
            int ratio = Math.round( (float)imgWidth / (float)screenWidth );
            //Applies ratio to the image via inSampleSzie, which will scale the image down if value is greater than 1
            options.inSampleSize = ratio;
        }

        //Set variable back to false for when image is displayed, now that size is acceptable
        options.inJustDecodeBounds = false;
        //Store the scaled image as a bitmap in scaledImg
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        //Set the image using the scaled image that is output from Bitmap Factory.
        img.setImageBitmap(scaledImg);
    }
}
