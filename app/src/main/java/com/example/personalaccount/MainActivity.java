package com.example.personalaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    EditText number, Fam, Name, pass;
    Button Enter;

    HttpURLConnection httpURLConnection;
    OutputStreamWriter os = null;

    URL url = null;
    public String response = null;
    String res = null;

    InputStreamReader isr = null;
    BufferedReader reader = null;
    StringBuilder sb = null;

    final OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .followRedirects(false)
            .followSslRedirects(false)
            .build();

    class AsyncRequest extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... arg) {

            RequestBody form = new FormBody.Builder()
                    .add("studentBookId", "170387")
                    .addEncoded("f","%CB%F3%EA%FC%FF%ED%EE%E2%E0")
                    .addEncoded("i","%C0%ED%ED%E0")
                    .addEncoded("pass","yehAFFQDfZ")
                    .add("class","pwd")
                    .add("submit","%C2%EE%E9%F2%E8")
                    .build();

            Request request = new Request.Builder()
                    .url("http://oreluniver.ru/student")
                    .post(form)
                    .build();

            Call call = client.newCall(request);
            Response response = null;
            try {
                response = call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("Status Code", String.valueOf(response.code()));
            Log.i("String message", response.message());
            Log.i("String",response.toString());
            try {
                Log.i("String", response.body().string());
               // Log.i("String", response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("Finall","True");

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*
        number = findViewById(R.id.textNumber);
        Fam = findViewById(R.id.textFam);
        Name = findViewById(R.id.textName);
        pass = findViewById(R.id.textPass);*/
        Enter = findViewById(R.id.enter);

        Enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*String  mNumber = number.getText().toString();
                String  mUserfam = Fam.getText().toString();
                String  mUsername = Name.getText().toString();
                String  mPass = pass.getText().toString();

                 */
                /*String parameters = "studentBookId=170387" +
                        "&f="+URLEncoder.encode("Лукьянова") +
                        "&i="+URLEncoder.encode("Анна")+"&pass=yehAFFQDfZ" +
                        "&class=pwd&submit="+URLEncoder.encode("Войти");*/

                String parameters = "studentBookId=170387&f=%CB%F3%EA%FC%FF%ED%EE%E2%E0&i=%C0%ED%ED%E0&pass=yehAFFQDfZ&class=pwd&submit=%C2%EE%E9%F2%E8";

                res = String.valueOf(new AsyncRequest().execute(parameters));

                //Log.i("Res",res); "studentBookId="+"170387"+
                //                                    "&fam="+"%D0%9B%D1%83%D0%BA%D1%8C%D1%8F%D0%BD%D0%BE%D0%B2%D0%B0"+
                //                                    "&i="+"%D0%90%D0%BD%D0%BD%D0%B0"+"&pass="+"yehAFFQDfZ"+
                //                                    "&class=pwd&submit=%D0%92%D0%BE%D0%B9%D1%82%D0%B8";
            }
        });
    }

}