package com.example.moviecatalogsubmission2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private String current;

    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {

        Fragment fragment;

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()){
                case 0:
                    fragment = new FilmFragment("movie");
                    break;
                case 1:
                    fragment = new FilmFragment("tv");
                    break;

            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment, fragment.getClass().getSimpleName())
                    .commit();

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab);

        if (savedInstanceState == null) {
            Fragment defaultFragment = new FilmFragment("movie");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, defaultFragment, defaultFragment.getClass().getSimpleName())
                    .commit();
        }

        tabLayout.addOnTabSelectedListener(mOnTabSelectedListener);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.language_setting, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.setting:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                return true;
            default:
                return true;
        }
    }



}
