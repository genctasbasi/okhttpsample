package com.escmobile.okhttpsample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    ImageView imageView;
    TextView txtBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListeners();
        init();
    }

    private void setListeners() {
        this.findViewById(R.id.btnStart).setOnClickListener(this);
        imageView = (ImageView)this.findViewById(R.id.imgImage);
        txtBytes = (TextView)this.findViewById(R.id.txtBytes);
    }

    private void init() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStart:
                downloadImage();
                break;
        }
    }

    private void downloadImage() {
        OkHttpHandler handler = new OkHttpHandler();

        byte[] image = new byte[0];

        try {
            image = handler.execute().get();

            if (image != null && image.length > 0){

                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageView.setImageBitmap(bitmap);
                txtBytes.setText("Total bytes downloaded: " + image.length);
            }

        } catch (Exception e) {
            txtBytes.setText("Hmm sorry, something went wrong!");
        }

    }
}
