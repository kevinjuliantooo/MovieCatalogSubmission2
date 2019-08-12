package com.example.moviecatalogsubmission2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DETAIL = "extra_detail";
    private TextView tvTitle, tvDescription, tvDate;
    private ImageView tvPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvDate = findViewById(R.id.tvDate);
        tvPoster = findViewById(R.id.tvPoster);

        FilmItems film = getIntent().getParcelableExtra(EXTRA_DETAIL);
        tvTitle.setText(film.getTitle());
        tvDate.setText(film.getDate());
        tvDescription.setText(film.getDescription());
        Picasso.get().load("https://image.tmdb.org/t/p/w1280" + film.getPoster()).into(tvPoster);

//        tvPoster.setImageResource(film.getPoster());


    }
}
