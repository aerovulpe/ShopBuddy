package com.tdothacks.shopbuddy.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdothacks.shopbuddy.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }


    /**
     * Created by aerisvulpe on 22/02/15.
     */
    public static class EBayParser extends AsyncTask<Void, Void, String> {
        private static final String url = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=TimothyL-2278-4c3f-87b7-f2f6f8268c59&GLOBAL-ID=EBAY-US&RESPONSE-DATA-FORMAT=JSON&callback=_cb_findItemsByKeywords&REST-PAYLOAD&keywords=";
        private static final String LOG_TAG = EBayParser.class.getSimpleName();

        @Override
        protected String doInBackground(Void... param) {
            return getJSONString(url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }

        private String getJSONString(String urlStr){
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            if (urlStr == null)
                return null;


            try {
                URL url = new URL(urlStr);


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();


                InputStream inputStream = urlConnection.getInputStream();


                if (inputStream == null)
                    return null;


                StringBuffer buffer = new StringBuffer();


                reader = new BufferedReader(new InputStreamReader(inputStream));


                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }


                return buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error!", e);
                return null;
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();


                if (reader != null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Error closing the reader", e);
                    }
            }
        }
    }
}
