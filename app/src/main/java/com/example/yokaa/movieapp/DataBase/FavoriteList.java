package com.example.yokaa.movieapp.DataBase;

/**
 * Created by yokaa on 11/11/16.
 */

public class FavoriteList {
    public static final String DATABASE_NAME = "FavoritesList";

    public static final String TABLE_Movies = "Movies";
    public static final String MOVIE_ID = "id";
    public static final String MOVIE_TITLE = "name";
    public static final String MOVIE_OVERVIEW = "overView";
    public static final String MOVIE_IMAGEPATH = "imgPath";
    public static final String MOVIE_RELEASEDATE = "releaseDate";
    public static final String MOVIE_USERRATING = "userRating";
    public static final String MOVIE_JASONID = "jasonID";




    public static final String CREATE_TABLE_MOVIES = "create table " + TABLE_Movies +
            "(" + MOVIE_ID + " integer primary key autoincrement, " + MOVIE_TITLE
            + " text, " + MOVIE_OVERVIEW + " text, "+
            MOVIE_IMAGEPATH + " text, "+ MOVIE_RELEASEDATE + " text, "+ MOVIE_USERRATING + " text, "+ MOVIE_JASONID + " text)";
}
