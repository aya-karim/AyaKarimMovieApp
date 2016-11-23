package com.example.yokaa.movieapp;

/**
 * Created by yokaa on 10/21/16.
 */

public class jasonMovieObj {

    public String imgPath ;
    public  String movieTitle;
    public  String overView;
    public  String releaseDate;
    public  int userRating;
    public  int id;

    public String getImgPath() {
        return imgPath;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getUserRating() {
        return userRating;
    }

    public int getId() {
        return id;
    }

    public String getMovieTitle() {

        return movieTitle;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public void setId(int id) {
        this.id = id;
    }
}

