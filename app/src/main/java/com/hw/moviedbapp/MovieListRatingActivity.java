/*Homework 2
MovieListRatingActivity.java
Hozefa Haveliwala, Nikhil Nagori - Group 29
*/
package com.hw.moviedbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieListRatingActivity extends AppCompatActivity {
    ArrayList<Movie> movieArray;
    int selectedMovie = 0;
    TextView textViewMovieName, textViewDesc, textViewGenre, textViewRating, textViewYear, textViewIMDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_rating);

        textViewMovieName = (TextView) findViewById(R.id.textViewMovieName);
        textViewDesc = (TextView) findViewById(R.id.textViewDesc);
        textViewGenre = (TextView) findViewById(R.id.textViewGenre);
        textViewRating = (TextView) findViewById(R.id.textViewRating);
        textViewYear = (TextView) findViewById(R.id.textViewYear);
        textViewIMDB = (TextView) findViewById(R.id.textViewIMDB);

        movieArray = getIntent().getParcelableArrayListExtra(MainActivity.MOVIE_KEY);
        movieArraySort();
        displayMovie(movieArray.get(selectedMovie));

        //imageViewFirst on Click
        findViewById(R.id.imageViewFirst).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MovieListYearActivity.this, "First", Toast.LENGTH_SHORT).show();
                selectedMovie = 0;
                displayMovie(movieArray.get(selectedMovie));

            }
        });
        //imageViewPrevious on Click
        findViewById(R.id.imageViewPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMovie > 0) {
                    selectedMovie = selectedMovie - 1;
                    displayMovie(movieArray.get(selectedMovie));
                }

            }
        });
        //imageViewNext on Click
        findViewById(R.id.imageViewNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMovie < (movieArray.size() - 1)) {
                    selectedMovie = selectedMovie + 1;
                    displayMovie(movieArray.get(selectedMovie));
                }
            }
        });
        //imageViewLast on Click
        findViewById(R.id.imageViewLast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMovie = movieArray.size() - 1;
                displayMovie(movieArray.get(selectedMovie));
            }
        });
        //Finish Button on Click
        findViewById(R.id.btnFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Custom sort on Year
    private void movieArraySort() {
        Collections.sort(movieArray, new Comparator<Movie>() {
            public int compare(Movie a, Movie b) {
                if (a.rating < b.rating)
                    return 1;
                else if (a.rating > b.rating)
                    return -1;
                else
                    return 0;
            }
        });
    }

    //Display the selected Movie
    private void displayMovie(Movie m) {
        textViewMovieName.setText(m.name);
        textViewDesc.setText(m.description);
        textViewGenre.setText(m.genre);
        textViewRating.setText(m.rating + "");
        textViewYear.setText(m.year + "");
        textViewIMDB.setText(m.IMDBLink);
    }
}
