package com.example.dell.fridaycamp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 7/13/2017.
 */

public class AllPostsAdapter extends ArrayAdapter {

    private Context context;

    public static final String EXTRA_BODY = "extra_message78";

    public static final String EXTRA_TITLE = "extra_message9898";

    public static final String EXTRA_OWNER = "extra_message45";

    public static final String EXTRA_IMAGE = "extra_message87778";


    List list = new ArrayList();

    AllPostsHolder allPostsHolder;

    public AllPostsAdapter(Context context, int resource) {

        super(context, resource);

    }


    public void add(AllPostsFeeder object) {
        super.add(object);
        list.add(object);
    }


    public int getCount() {
        return list.size();
    }


    public Object getItem(int position) {

        return list.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        row = convertView;


        if (row == null) {


            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflater.inflate(R.layout.all_posts_adapter, parent, false);

            allPostsHolder = new AllPostsHolder();

            allPostsHolder.title_txt = (TextView) row.findViewById(R.id.postTitle);

            allPostsHolder.body_txt = (TextView) row.findViewById(R.id.postBody);

            allPostsHolder.owner_txt = (TextView) row.findViewById(R.id.postOwner);


            allPostsHolder.img_txt = (ImageView) row.findViewById(R.id.postOwnerImage);

            row.setTag(allPostsHolder);


        } else {

            allPostsHolder = (AllPostsHolder) row.getTag();

        }




        AllPostsFeeder allPostsFeeder = (AllPostsFeeder) this.getItem(position);

        allPostsHolder.title_txt.setText(allPostsFeeder.getTitle());

        allPostsHolder.body_txt.setText(allPostsFeeder.getBody());

        allPostsHolder.owner_txt.setText(allPostsFeeder.getOwner());

        Picasso
                .with(getContext())
                .load("http://fridaycamp.com/" + allPostsFeeder.getImg())
                .transform(new CircleTransform())
                .placeholder(R.drawable.back)
                .resize(50, 50)
                .centerCrop()
               // .fit() // will explain later
                .into(allPostsHolder.img_txt);

        TextView myTextView7 = (TextView) row.findViewById(R.id.postOwner);
        Typeface typeface9 = Typeface.createFromAsset(getContext().getAssets(), "font/JosefinSlab-Bold.ttf");
        myTextView7.setTypeface(typeface9);

        TextView myTextView17 = (TextView) row.findViewById(R.id.postTitle);
        Typeface typeface19 = Typeface.createFromAsset(getContext().getAssets(), "font/Georgia.ttf");
        myTextView17.setTypeface(typeface19);

        TextView myTextView27 = (TextView) row.findViewById(R.id.postBody);
        Typeface typeface29 = Typeface.createFromAsset(getContext().getAssets(), "font/Georgia.ttf");
        myTextView27.setTypeface(typeface29);

        TextView goToSinglePost = (TextView) row.findViewById(R.id.postBody);
        // Cache row position inside the button using `setTag`
        goToSinglePost.setTag(position);
        // Attach the click event handler
        goToSinglePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                AllPostsFeeder allPostsFeeder = (AllPostsFeeder) getItem(position);
                // Do what you want here...



                Intent goToSinglePostNow = new Intent(getContext(), SinglePost.class);



                String sendBody = allPostsFeeder.getBody();

                goToSinglePostNow.putExtra(EXTRA_BODY, sendBody);



                String sendTitle = allPostsFeeder.getTitle();

                goToSinglePostNow.putExtra(EXTRA_TITLE, sendTitle);


                String sendOwner = allPostsFeeder.getOwner();

                goToSinglePostNow.putExtra(EXTRA_OWNER, sendOwner);


                String sendImage = allPostsFeeder.getImg();

                goToSinglePostNow.putExtra(EXTRA_IMAGE, sendImage);


                getContext().startActivity(goToSinglePostNow);

            }
        });




        return row;

    }





    static class AllPostsHolder {

        TextView title_txt, body_txt, owner_txt;

        ImageView img_txt;



    }


    void clearG() {

        list.clear();

    }





}
