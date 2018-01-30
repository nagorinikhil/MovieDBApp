/*Homework 2
MainActivity.java
Hozefa Haveliwala, Nikhil Nagori - Group 29
*/
package com.hw.moviedbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static int REQ_ADD_MOVIE = 100;
    final static int REQ_EDIT_MOVIE = 101;
    final static String MOVIE_KEY = "Movie";
    private ArrayList<Movie> movieArray;
    private int selectedMovieIndex;
    private CharSequence[] movieNameList;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieArray = new ArrayList<Movie>();

        //Add Movie Button on Click
        findViewById(R.id.btnAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intent, REQ_ADD_MOVIE);
            }
        });
        //Edit Movie Button on Click
        findViewById(R.id.btnEditMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieArray.size() > 0) {
                    movieNameList = createMovieNameList(movieArray);
                    AlertDialog.Builder aDBuilder = new AlertDialog.Builder(MainActivity.this);
                    aDBuilder.setTitle("Pick a Movie")
                            .setItems(movieNameList, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    selectedMovieIndex = which;
                                    //  Toast.makeText(MainActivity.this, movieArray.get(selectedMovieIndex).name, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, EditMovieActivity.class);
                                    //get the selected movie index from the dialogbox.
                                    intent.putExtra(MOVIE_KEY, movieArray.get(selectedMovieIndex));
                                    startActivityForResult(intent, REQ_EDIT_MOVIE);
                                }
                            });
                    alertDialog = aDBuilder.create();
                    alertDialog.show();

                } else {
                    Toast.makeText(MainActivity.this, "No Movies Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Delete Movie Button on Click
        findViewById(R.id.btnDeleteMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieArray.size() > 0) {
                    movieNameList = createMovieNameList(movieArray);
                    AlertDialog.Builder aDBuilder = new AlertDialog.Builder(MainActivity.this);
                    aDBuilder.setTitle("Pick a Movie")
                            .setItems(movieNameList, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this, movieArray.get(which).name + " Deleted", Toast.LENGTH_SHORT).show();
                                    movieArray.remove(which);
                                }
                            });
                    alertDialog = aDBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "No Movies Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //MovieListYear Button on Click
        findViewById(R.id.btnMovieListYear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieArray.size() > 0) {
                    Intent intent = new Intent("com.hw.moviedbapp.intent.action.MOVIELISTYEAR");
                    intent.putParcelableArrayListExtra(MOVIE_KEY, movieArray);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No Movies Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //MovieListRating Button on Click
        findViewById(R.id.btnMovieListRating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieArray.size() > 0) {
                    Intent intent = new Intent("com.hw.moviedbapp.intent.action.MOVIELISTRATING");
                    intent.putParcelableArrayListExtra(MOVIE_KEY, movieArray);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No Movies Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_ADD_MOVIE) {
                Movie newMovie = (Movie) data.getParcelableExtra(MOVIE_KEY);
                movieArray.add(newMovie);
            } else if (requestCode == REQ_EDIT_MOVIE) {

                // For edit first remove the selected movie from the list and then add the newly edited movie as new movie
                // set selectedMovieIndex to -1 to reset.
                movieArray.remove(selectedMovieIndex);
                Movie newMovie = (Movie) data.getParcelableExtra(MOVIE_KEY);
                movieArray.add(selectedMovieIndex, newMovie);
                selectedMovieIndex = -1;

            }
        }
    }

    private CharSequence[] createMovieNameList(ArrayList<Movie> array) {
        ArrayList<String> str = new ArrayList<String>();
        // Log.d("Name11", array.get(0).name);
        for (Movie movie : array) {
            str.add(movie.name);
            // Log.d("Name", movie.name);
        }
        return str.toArray(new CharSequence[str.size()]);
    }

    private AlertDialog createAlertDialog(CharSequence[] cs) {

        AlertDialog.Builder aDBuilder = new AlertDialog.Builder(MainActivity.this);
        aDBuilder.setTitle("Pick a Movie")
                .setItems(cs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedMovieIndex = which;
                        Intent intent = new Intent(MainActivity.this, EditMovieActivity.class);
                        //get the selected movie index from the dialogbox.
                        intent.putExtra(MOVIE_KEY, movieArray.get(selectedMovieIndex));
                        startActivityForResult(intent, REQ_EDIT_MOVIE);
                    }
                });
        return aDBuilder.create();
    }
}
