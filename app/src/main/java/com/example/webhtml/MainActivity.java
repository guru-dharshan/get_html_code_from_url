package com.example.webhtml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    class downlaod extends AsyncTask<String,Void,String>{

            URL url;
            HttpURLConnection urlConnection=null;
            @Override
            protected String doInBackground(String... urls) {
                String result=" ";
                URL url;
                HttpURLConnection urlConnection=null;
                try{
                url =new URL(urls[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader =new InputStreamReader(in);
                int data = reader.read();
                while(data!=-1){
                    char current=(char) data;
                    result+=current;
                    data=reader.read();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
    public void getul(View view){
        EditText ur = (EditText)findViewById(R.id.editText);
        String link1 = String.valueOf(ur.getText());
        EditText ht = (EditText)findViewById(R.id.editText2);
        String result1=null;
        downlaod a = new downlaod();
        try {
            result1= a.execute(link1).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ht.setText(result1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
