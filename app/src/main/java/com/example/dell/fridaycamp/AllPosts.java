package com.example.dell.fridaycamp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AllPosts extends AppCompatActivity {

    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
     //   super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_all_posts);





        JSONObject json_object;

        JSONArray json_array;

        AllPostsAdapter allPostsAdapter;

        ListView listOfPosts;

        String url = "http://fridaycamp.com/views/mobile_all_posts.php";

        ProgressDialog dialog;

        SwipeRefreshLayout mySwipeRefreshLayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_all_posts);


            Sessions login_sessions = new Sessions(getApplicationContext());
            Toast.makeText(getApplicationContext(), login_sessions.getID(), Toast.LENGTH_LONG).show();

            listOfPosts = (ListView) findViewById(R.id.list1);


            allPostsAdapter = new AllPostsAdapter(this, R.layout.all_posts_adapter);


            listOfPosts.setAdapter(allPostsAdapter);



           // dialog = new ProgressDialog(this);
           // dialog.setMessage("Loading....");
          //  dialog.show();

            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String string) {


                    parseJsonData(string);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            RequestQueue rQueue = Volley.newRequestQueue(AllPosts.this);
            rQueue.add(request);

            mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swee);

            mySwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {


                            // This method performs the actual data-refresh operation.
                            // The method calls setRefreshing(false) when it's finished.
                            myUpdateOperation();
                        }
                    }
            );



        //      Intent goList = getIntent();


        //    String json_string = goList.getStringExtra(SignUp.EXTRA_MESSAGE);



        /*    try {

                json_object = new JSONObject(json_string);

                json_array = json_object.getJSONArray("allposts");

                int count = 0;

                while(count < json_array.length()) {


                    JSONObject JO = json_array.getJSONObject(count);

                    String title = JO.getString("title");

                    String body = JO.getString("body");

                    String img = JO.getString("img");

                    String owner = JO.getString("owner");



                    AllPostsFeeder allPostsFeeder = new AllPostsFeeder(title, body, img, owner);

                    allPostsAdapter.add(allPostsFeeder);

                    count++;


                }


            } catch (JSONException e) {


                e.printStackTrace();


            }
            */

            // TextView postTitleFont = (TextView) findViewById(R.id.postTitle);

          //  Typeface titleTypeFace = Typeface.createFromAsset(getAssets(), "font/JosefinSlab-Bold.ttf");

          //  postTitleFont.setTypeface(titleTypeFace);

        }








    void parseJsonData(String jsonString) {



           try {

                json_object = new JSONObject(jsonString);

                allPostsAdapter.clearG();

                json_array = json_object.getJSONArray("allposts");

                int count = 0;

                while(count < json_array.length()) {


                    JSONObject JO = json_array.getJSONObject(count);

                    String title = JO.getString("title");

                    String body = JO.getString("body");

                    String img = JO.getString("img");

                    String owner = JO.getString("owner");



                    AllPostsFeeder allPostsFeeder = new AllPostsFeeder(title, body, img, owner);

                    allPostsAdapter.add(allPostsFeeder);

                    count++;


                }


            } catch (JSONException e) {


                e.printStackTrace();


            }

      //  dialog.dismiss();
    }



void myUpdateOperation() {

    StringRequest request = new StringRequest(url, new Response.Listener<String>() {
        @Override
        public void onResponse(String string) {


            parseJsonData(string);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    });

    RequestQueue rQueue = Volley.newRequestQueue(AllPosts.this);
    rQueue.add(request);

    allPostsAdapter.notifyDataSetChanged();

    Toast.makeText(getApplicationContext(), "Cool as f", Toast.LENGTH_LONG).show();

    mySwipeRefreshLayout.setRefreshing(false);

}

    }

