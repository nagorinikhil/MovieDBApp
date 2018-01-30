/*Homework 2
Movie.java
Hozefa Haveliwala, Nikhil Nagori - Group 29
*/
package com.hw.moviedbapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Admin on 25-01-2017.
 */

public class Movie implements Parcelable {
    String name, description, genre, IMDBLink;
    int rating, year, genreIndex;

    public Movie(String name, String description, String genre, int genreIndex, int rating, int year, String IMDBLink) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.genreIndex = genreIndex;
        this.IMDBLink = IMDBLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(genre);
        dest.writeInt(genreIndex);
        dest.writeInt(rating);
        dest.writeInt(year);
        dest.writeString(IMDBLink);

    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {

            return new Movie[size];
        }

    };

    private Movie(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.genre = in.readString();
        this.genreIndex = in.readInt();
        this.rating = in.readInt();
        this.year = in.readInt();
        this.IMDBLink = in.readString();
    }


}
