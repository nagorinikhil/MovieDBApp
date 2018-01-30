/*Homework 2
AddMovieActivity.java
Hozefa Haveliwala, Nikhil Nagori - Group 29
*/
package com.hw.moviedbapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {
    EditText editTextName, editTextDesc, editTextYear, editTextIMDB;
    SeekBar sBRating;
    Spinner spinnerGenre;
    TextView textViewRatingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDesc = (EditText) findViewById(R.id.editTextDesc);
        editTextYear = (EditText) findViewById(R.id.editTextYear);
        editTextIMDB = (EditText) findViewById(R.id.editTextIMDB);
        sBRating = (SeekBar) findViewById(R.id.seekBar4);
        spinnerGenre = (Spinner) findViewById(R.id.spinnerGenre);
        textViewRatingValue = (TextView) findViewById(R.id.textViewRatingValue);
/*///*//*************************//*///
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.GenreArray));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(arrayAdapter);
        //*//**************************************//*//*/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,
                getResources().getStringArray(R.array.GenreArray)){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerGenre.setAdapter(arrayAdapter);


        //spinnerGenre.setSelection(getResources().getStringArray(R.array.GenreArray).length -1);
        //spinnerGenre.setSelection(getResources().getString(R.string.selectgenre));

        sBRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewRatingValue.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        findViewById(R.id.btnAddNewMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.length() == 0 ) {
                    Toast.makeText(AddMovieActivity.this, "Enter Name ", Toast.LENGTH_SHORT).show();

                } else if(editTextDesc.length() == 0){
                    Toast.makeText(AddMovieActivity.this, "Enter Description ", Toast.LENGTH_SHORT).show();
                } else if(spinnerGenre.getSelectedItemPosition() == 0) {
                    Toast.makeText(AddMovieActivity.this, "Choose Movie Genre ", Toast.LENGTH_SHORT).show();
                } else if(editTextYear.length() != 4){
                    Toast.makeText(AddMovieActivity.this, "Enter correct year ", Toast.LENGTH_SHORT).show();
                } else if(editTextIMDB.length()==0 || checkLink(editTextIMDB.getText().toString()) == false){
                    Toast.makeText(AddMovieActivity.this, "Enter correct IMDB link ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Movie newMovie = new Movie(editTextName.getText().toString(), editTextDesc.getText().toString(), spinnerGenre.getSelectedItem().toString(), spinnerGenre.getSelectedItemPosition(), sBRating.getProgress(), Integer.valueOf(editTextYear.getText().toString()), editTextIMDB.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.MOVIE_KEY, newMovie);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AddMovieActivity.this, "Movie Added", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }

    public boolean checkLink(String link){
        if(link.contains("www.imdb.com"))
            return true;
        return false;
    }
}
