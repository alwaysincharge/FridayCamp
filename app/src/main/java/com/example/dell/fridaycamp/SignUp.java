package com.example.dell.fridaycamp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUp extends AppCompatActivity {

    String JSON_STRING;
    String json_string;
    public static final String EXTRA_MESSAGE = "extra_message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

    }


    public void getJson(View view) {

    new GetAllPosts().execute();

    }




    public class GetAllPosts extends AsyncTask<Void, Void, String> {





        String json_url;

        @Override
        protected void onPreExecute() {


            json_url = "http://fridaycamp.com/views/mobile_all_posts.php";

        }


        @Override
        protected String doInBackground(Void... params) {


            try {

                URL url = new URL(json_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                StringBuilder stringBuilder = new StringBuilder();


                while((JSON_STRING = bufferedReader.readLine())!= null) {

                    stringBuilder.append(JSON_STRING+"\n");

                }

                bufferedReader.close();

                inputStream.close();

                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();





            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }



            return null;
        }







        @Override
        protected void onPostExecute(String result) {



            TextView newText = (TextView) findViewById(R.id.jsontext);

            newText.setText("done");

            json_string = result;

            if (json_string == null) {

                Toast.makeText(getApplicationContext(), "There is no data", Toast.LENGTH_LONG).show();

            } else {

                Intent goList = new Intent(SignUp.this, AllPosts.class);

                String message = json_string.toString();

                goList.putExtra(EXTRA_MESSAGE, message);


                startActivity(goList);

            }


        }


        @Override
        protected void onProgressUpdate(Void... values) {


        }




    }




    public void parseJson(View view) {

        if (json_string == null) {

            Toast.makeText(this, "There is no data", Toast.LENGTH_LONG).show();

        } else {

            Intent goList = new Intent(SignUp.this, AllPosts.class);

            String message = json_string.toString();

            goList.putExtra(EXTRA_MESSAGE, message);


            startActivity(goList);

        }

    }




}
