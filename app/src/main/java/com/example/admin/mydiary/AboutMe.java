package com.example.admin.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutMe extends AppCompatActivity {

    EditText name_et, gender_et, email_et, number_et, dob_et;
    public static final int Pick_Image_From_Gallery = 1;
    CircleImageView circleImageView;
    Button changeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        name_et =(EditText)findViewById(R.id.name_et);
        gender_et =(EditText)findViewById(R.id.gender_et);
        email_et =(EditText)findViewById(R.id.email_et);
        number_et =(EditText)findViewById(R.id.number_et);
        dob_et =(EditText)findViewById(R.id.dateOfBirth_et);
        circleImageView = (CircleImageView)findViewById(R.id.circleImageView) ;
        changeImage = (Button)findViewById(R.id.changeImageView);

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Pick image"), Pick_Image_From_Gallery);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == Pick_Image_From_Gallery && resultCode == Activity.RESULT_OK && data != null) {

            Uri uri = data.getData();
            circleImageView.setImageURI(uri);

        }
    }

}
