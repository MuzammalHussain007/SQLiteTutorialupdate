package com.example.sqlitetutorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sqlitetutorial.DataBaseClass.DataBaseHelper;
import com.example.sqlitetutorial.Modal.User;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity".getClass().getName() ;
    private EditText name,pass;
    private Button login;
    private ImageView mImageView;
    private DataBaseHelper mHelper;
    private String imageaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=mHelper.insert(name.getText().toString(),pass.getText().toString(),imageaddress);
                if (res==true)
                {
                    Toast.makeText(getApplicationContext(),"Record insert Successfully",Toast.LENGTH_SHORT).show();
                    mImageView.setImageResource(R.drawable.ic_launcher_background);
                    name.setText("");
                    pass.setText("");
                    startActivity(new Intent(MainActivity.this,ShowActivity.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"oops Record not inserted",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    pass.setText("");
                }
            }
        });
        mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), 1);
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            Uri image = data.getData();
            imageaddress=image.toString();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                mImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void connection() {
        name=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        mImageView=findViewById(R.id.imageview);
        mHelper= new DataBaseHelper(MainActivity.this);
        login=findViewById(R.id.login);
    }

    public void searchdata(View view) {
        startActivity(new Intent(MainActivity.this,SearchActivity.class));
    }
}