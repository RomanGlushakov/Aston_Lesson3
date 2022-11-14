package com.example.astonlesson3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button showPhoto = findViewById(R.id.showPhoto);
        EditText editText = findViewById(R.id.editText);
        ImageView imageView = findViewById(R.id.imageView);
        Button firstScreen = findViewById(R.id.previousScreen);
        editText.setText("");

        firstScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });

        showPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = editText.getText().toString();

                Glide.with(MainActivity2.this).
                        load(link).placeholder(R.drawable.no_photo).
                        skipMemoryCache(true).
                        diskCacheStrategy(DiskCacheStrategy.NONE).
                        listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e,
                                                        Object model, Target<Drawable> target,
                                                        boolean isFirstResource) {
                                Toast.makeText(MainActivity2.this,
                                        "Connection problem or field is empty", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model,
                                                           Target<Drawable> target,
                                                           DataSource dataSource,
                                                           boolean isFirstResource) {
                                return false;
                            }
                        }).into(imageView);



                editText.setText("");
            }
        });
    }
}