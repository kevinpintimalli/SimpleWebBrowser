package edu.temple.simplewebbrowser;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kevin on 10/27/15.
 */
public class DownloadURL extends AsyncTask<String,Void,String> {
    private WebView webView;

    public DownloadURL(WebView webView){
        this.webView = webView;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("Async Start: ","Yay");
        try {
            String newUrl = params[0];

            if(!newUrl.startsWith("http://")){
                newUrl = "http://" + newUrl;
            }

            URL url = new URL(newUrl);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String response = "",tmpResponse;

            tmpResponse = reader.readLine();
            while(tmpResponse != null){
                response = response + tmpResponse;
                tmpResponse = reader.readLine();
            }

            return response;

        }catch (MalformedURLException e){
            Log.d("MalformedURLException",e.toString());
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }

    @Override
    protected void onPostExecute(String result){

        if(webView != null && result!=null){
            webView.loadData(result,"text/html","UTF-8");
        }
    }
}
