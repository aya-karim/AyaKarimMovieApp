package com.example.yokaa.movieapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yokaa.movieapp.jasonMovieObj;

/**
 * Created by yokaa on 11/11/16.
 */

public class dbHelper extends SQLiteOpenHelper {

    public static FavoriteList mDatabaseFavoriteList = new FavoriteList();
    public static final int DATABASE_VERSION = 6;
    SQLiteDatabase mSQLiteDatabase;

    jasonMovieObj movieObj = new jasonMovieObj();

    public dbHelper(Context context) {
        super(context, mDatabaseFavoriteList.DATABASE_NAME, null, DATABASE_VERSION);

    }

    public  void  setMovie (jasonMovieObj movie){
        //movieObj= movie;
        movieObj.setId(movie.id);
        movieObj.setUserRating(movie.userRating);
        movieObj.setImgPath(movie.imgPath);
        movieObj.setMovieTitle(movie.movieTitle);
        movieObj.setReleaseDate(movie.releaseDate);
        movieObj.setOverView(movie.overView);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(mDatabaseFavoriteList.CREATE_TABLE_MOVIES);


    }
    public String insertMovie (){

        mSQLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String userRating=String.valueOf(movieObj.userRating);
        String movieid=String.valueOf(movieObj.id);
        contentValues.put(mDatabaseFavoriteList.MOVIE_TITLE,movieObj.movieTitle);
        contentValues.put(mDatabaseFavoriteList.MOVIE_OVERVIEW,movieObj.overView);
        contentValues.put(mDatabaseFavoriteList.MOVIE_IMAGEPATH,movieObj.imgPath);
        contentValues.put(mDatabaseFavoriteList.MOVIE_RELEASEDATE,movieObj.releaseDate);
        contentValues.put(mDatabaseFavoriteList.MOVIE_USERRATING,userRating);
        contentValues.put(mDatabaseFavoriteList.MOVIE_JASONID,movieid);
//
//        public static final String MOVIE_TITLE = "name";
//        public static final String MOVIE_OVERVIEW = "overView";
//        public static final String MOVIE_IMAGEPATH = "imgPath";
//        public static final String MOVIE_RELEASEDATE = "releaseDate";
//        public static final String MOVIE_USERRATING = "userRating";

  //      Log.i("data",movieObj.imgPath);
//        String insertQuery = "insert into " + mDatabaseFavoriteList.TABLE_Movies + " ("
//                + mDatabaseFavoriteList.MOVIE_TITLE + "," + mDatabaseFavoriteList.MOVIE_OVERVIEW + "," +
//                mDatabaseFavoriteList.MOVIE_IMAGEPATH + "," + mDatabaseFavoriteList.MOVIE_RELEASEDATE + "," +
//                mDatabaseFavoriteList.MOVIE_USERRATING + ") values (" + movieObj.movieTitle + "," + movieObj.overView
//                + "," + movieObj.imgPath + "," + movieObj.releaseDate + "," + userRating + ")";


   //     mSQLiteDatabase.rawQuery(insertQuery,null);

        mSQLiteDatabase.insert(mDatabaseFavoriteList.TABLE_Movies,null,contentValues);
        String u = "Done!";
        return u;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + mDatabaseFavoriteList.TABLE_Movies );
        onCreate(db);
    }

    public Cursor getAllData() {

        mSQLiteDatabase = getReadableDatabase();
        Cursor mCursor = mSQLiteDatabase.rawQuery("select * from " + mDatabaseFavoriteList.TABLE_Movies, null);
        mCursor.moveToFirst();
      //  mSQLiteDatabase.close();
        return mCursor;
    }
//    public int numberofRecords()
//    {
//        mSQLiteDatabase=getReadableDatabase();
//        Cursor c = mSQLiteDatabase.rawQuery("SELECT COUNT(*) FROM "+mDatabaseFavoriteList.TABLE_Movies,null);
//        int count = c.getCount();
//        c.close();
//        return count;
//    }
}
