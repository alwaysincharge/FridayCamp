package com.example.dell.fridaycamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.dell.fridaycamp.AllPostsAdapter.EXTRA_BODY;
import static com.example.dell.fridaycamp.AllPostsAdapter.EXTRA_IMAGE;
import static com.example.dell.fridaycamp.AllPostsAdapter.EXTRA_OWNER;
import static com.example.dell.fridaycamp.AllPostsAdapter.EXTRA_TITLE;

public class SinglePost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);


        Intent goToSinglePostNow = getIntent();




        String singleBody = goToSinglePostNow.getStringExtra(EXTRA_BODY);

        TextView singleBodyView = (TextView) findViewById(R.id.singlepostBody);

        singleBodyView.setText(singleBody);




        String singleOwner = goToSinglePostNow.getStringExtra(EXTRA_OWNER);

        TextView singleOwnerView = (TextView) findViewById(R.id.singlepostOwner);

        singleOwnerView.setText(singleOwner);


        String singleImage = goToSinglePostNow.getStringExtra(EXTRA_IMAGE);

        ImageView singleImgView = (ImageView) findViewById(R.id.singlepostImage);

         Picasso.with(getApplicationContext())
                .load("http://fridaycamp.com/" + singleImage)
                .resize(50, 50)
                .centerCrop()
                .into(singleImgView);




        String singleTitle = goToSinglePostNow.getStringExtra(EXTRA_TITLE);

        TextView singleTitleView = (TextView) findViewById(R.id.singlepostTitle);

        singleTitleView.setText(singleTitle);





    }
}
