package com.example.moviecatalogsubmission2;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "652ae94b7a67745bd01006a794b01893";
    private MutableLiveData<ArrayList<FilmItems>> listFilm = new MutableLiveData<>();

    void setFilm(String language, final String get_film){


        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<FilmItems> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/" + get_film + "/popular?api_key=" + API_KEY + "&language=" + language + "&page=1";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);
                        FilmItems filmItems = new FilmItems(film, get_film);
                        listItems.add(filmItems);
                    }
                    listFilm.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    LiveData<ArrayList<FilmItems>> getFilms(){
        return listFilm;
    }
}
