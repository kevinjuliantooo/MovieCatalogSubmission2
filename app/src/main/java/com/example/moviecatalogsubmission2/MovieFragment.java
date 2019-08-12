package com.example.moviecatalogsubmission2;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private String[] dataTitle;
    private String[] dataDescription;
    private TypedArray dataPoster;
    private String[] dataDate;
    private FilmAdapter filmAdapter;
    private ArrayList<Film> films;
    public static final String EXTRA_DETAIL = "extra_detail";
    public static final String GET_THIS = "movie";
    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;
    private String current_language;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        current_language = String.valueOf(getResources().getConfiguration().locale);

        if (current_language.equals("in_ID")){
            current_language = "id";
        }

        System.out.println(current_language);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        setFilmItems();
        mainViewModel.getFilms().observe(this, getFilm);

        filmAdapter =  new FilmAdapter();
        filmAdapter.notifyDataSetChanged();

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(filmAdapter);



    }

    private void setFilmItems() {
        mainViewModel.setFilm(current_language, GET_THIS);
//        showLoading(true);
    }

    private Observer<ArrayList<FilmItems>> getFilm = new Observer<ArrayList<FilmItems>>() {
        @Override
        public void onChanged(ArrayList<FilmItems> filmItems) {
            if (filmItems != null) {
                filmAdapter.setData(filmItems);
                filmItems.clear();
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }




//    private void showRecyclerList() {
//
//    }


//    private void prepare(){
//        dataTitle= getResources().getStringArray(R.array.title_movies);
//        dataDescription= getResources().getStringArray(R.array.description_movies);
//        dataPoster= getResources().obtainTypedArray(R.array.poster_movies);
//        dataDate= getResources().getStringArray(R.array.date_movie);
//    }

//    private void addItem() {
//        films = new ArrayList<>();
//
//
//        for (int i = 0; i < dataTitle.length; i++) {
//            Film film = new Film();
//            film.setPosterImg(dataPoster.getResourceId(i, -1));
//            film.setTitle(dataTitle[i]);
//            film.setDescription(dataDescription[i]);
//            film.setDate(dataDate[i]);
//            films.add(film);
//        }
//
//    }
}
