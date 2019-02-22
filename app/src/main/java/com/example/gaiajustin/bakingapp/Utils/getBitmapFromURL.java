package com.example.gaiajustin.bakingapp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class getBitmapFromURL extends AsyncTask<Void, Void, Bitmap>{

    String url;

    public getBitmapFromURL (String newUrl) {
        url = newUrl;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL url = new URL("http://image.tmdb.org/t/p/w185/i2dF9UxOeb77CAJrOflj0RpqJRF.jpg");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}