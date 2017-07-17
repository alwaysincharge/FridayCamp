package com.example.dell.fridaycamp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        TextView myTextView = (TextView) findViewById(R.id.titleSignUp);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/JosefinSlab-Bold.ttf");
        myTextView.setTypeface(typeface);



        TextView myTextView2 = (TextView) findViewById(R.id.subtitleSignUp);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "font/JosefinSlab-Bold.ttf");
        myTextView2.setTypeface(typeface2);

    }





    public void trySignUp(View view) {

        EditText username1 = (EditText) findViewById(R.id.register_username_txt) ;

        EditText password1 = (EditText) findViewById(R.id.register_password_txt1);

        EditText password2 = (EditText) findViewById(R.id.register_password_txt2);

        EditText email1 = (EditText) findViewById(R.id.register_email_txt);

        String username = username1.getText().toString();

        String password = password1.getText().toString();

        String email = email1.getText().toString();

        String type = "signup";

        SignUpAPI signUpAPI = new SignUpAPI(this);

        signUpAPI.execute(type, username, password, email);


    }





    public class SignUpAPI extends AsyncTask<String, String, String> {




        AlertDialog alertDialog;

        Context context;

        Intent intents;

        SignUpAPI (Context ctx) {
            context = ctx;
        }





        @Override
        protected String doInBackground(String... params) {

            String type = params[0];

            String login_url = "http://tsutsus.com/login.php";

            if(type.equals("signup")) {

                try {

                    String user_name = params[1];

                    String password = params[2];

                    String email = params[3];

                    URL url = new URL(login_url);

                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                    httpURLConnection.setRequestMethod("POST");

                    httpURLConnection.setDoOutput(true);

                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");

                    bufferedWriter.write(post_data);

                    bufferedWriter.flush();

                    bufferedWriter.close();

                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                    String result="";

                    String line="";

                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }

                    bufferedReader.close();

                    inputStream.close();

                    httpURLConnection.disconnect();

                    return result;



                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }


            }

            return null;
        }



        @Override
        protected void onPreExecute() {

            alertDialog = new AlertDialog.Builder(context).create();

            alertDialog.setTitle("Login Status");

            View loadingImg = (View) findViewById(R.id.loadingSignUp);

            loadingImg.setVisibility(View.VISIBLE);


        }


        @Override
        protected void onPostExecute(String result) {


            View loadingImg = (View) findViewById(R.id.loadingSignUp);

            loadingImg.setVisibility(View.INVISIBLE);



            String resultGet =    result;

            if (resultGet == null) {

                Toast.makeText(getApplicationContext(), "Poor network connection.", Toast.LENGTH_LONG).show();

            }





            //Intent intent_name = new Intent();
            // intent_name.setClass(getApplicationContext(),DestinationClassName.class);
            // startActivity(intent_name);

        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);








        }






    }










}
