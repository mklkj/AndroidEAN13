package com.project.sean.androidean13;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.seanse.ean13.Ean13View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AndroidEAN13Activity extends Activity {

    Ean13View t;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_android_ean13);
        Button btn = findViewById(R.id.btn_create);
        btn.setOnClickListener(arg0 -> createEAN13Code());
        Button btn2 = findViewById(R.id.btn_image);
        btn2.setOnClickListener(arg0 -> imageEAN13Code());

        t = findViewById(R.id.barcode);
        t.setEan("1249587613107"); // $!24J5IH-gbdbah!
    }

    public void createEAN13Code() {
        EditText e = findViewById(R.id.etBarcode);
        String s = e.getText().toString();
        if (s == null || s.length() != 12) {
            Toast.makeText(this, "A barcode requires 12 numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        Ean13View t = findViewById(R.id.barcode);
        t.setEan(s);
        Toast.makeText(this, "Barcode generated successfully!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Converts the TextView into a Bitmap so that it can be stored as a png file.
     */
    public void imageEAN13Code() {
        t.setDrawingCacheEnabled(true); // Enable drawing cache before calling the getDrawingCache() method
        // Get bitmap object from the TextView
        Bitmap tvImage = Bitmap.createBitmap(t.getDrawingCache());

        String filename = "tvimage.png";

        System.out.print(getFilesDir());
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(filename, MODE_PRIVATE);
            // Save the bitmap object to file
            tvImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ioex) {
                    Log.d("IOException", ioex.toString());
                }
            }
        }
    }
}
