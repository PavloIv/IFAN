package com.ip.ifan;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ResponseToUserActivity extends AppCompatActivity {
    private TextView responseToUser;
    private Button toMain;
    private String urlFactUserNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_to_user);

        responseToUser = findViewById(R.id.responseToUser);

        Intent intentStartActivity = getIntent();
        if (intentStartActivity.hasExtra(Intent.EXTRA_TEXT)){
            urlFactUserNumber = intentStartActivity.getStringExtra(Intent.EXTRA_TEXT);
            new GetUrlData().execute(urlFactUserNumber);
        }

        toMain = findViewById(R.id.toMaim);

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ResponseToUserActivity.this;
                Class destActivity = MainActivity.class;
                Intent intent = new Intent(context,destActivity);
                startActivity(intent);
            }
        });

    }

    private class GetUrlData extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            responseToUser.setText("Please wait...");
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                System.out.println(buffer.toString());
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();

                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            responseToUser.setText(result);
            System.out.println(responseToUser);
        }
    }
}