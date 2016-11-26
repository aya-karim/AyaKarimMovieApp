package com.example.yokaa.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yokaa.movieapp.DataBase.dbHelper;
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {



    @SuppressLint("ValidFragment")
    public DetailFragment(Bundle bundle) {
        this.bundle=bundle;
    }


    public DetailFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    Bundle bundle;
    jasonMovieObj movie = new jasonMovieObj();
    jasonMovieObj mFavoriteMovie = new jasonMovieObj();
    dbHelper mDataBaseHelper;
    ImageView MovieImageView ;
    TextView movieTitle ;
    TextView releaseDate ;
    TextView overView ;
    TextView movieRating ;
    Button AddToFavorite;
    Button RemoveFromFavorites;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        RemoveFromFavorites=(Button)rootView.findViewById(R.id.DeleteFromFavorite);
        AddToFavorite=(Button)rootView.findViewById(R.id.AddToFavorite);
        mDataBaseHelper = new dbHelper(getContext());
        if (bundle != null)
        {
            movie.userRating=bundle.getInt("userRating");
            movie.imgPath=bundle.get("imgPath").toString();
            movie.overView=bundle.get("overView").toString();
            movie.releaseDate=bundle.get("releaseDate").toString();
            movie.movieTitle=bundle.get("movieTitle").toString();
            movie.id =bundle.getInt("id");
            mFavoriteMovie=movie;

            MovieImageView = (ImageView)rootView.findViewById(R.id.imageView);
            movieTitle = (TextView)rootView.findViewById(R.id.txtMovieTitle);
            releaseDate = (TextView)rootView.findViewById(R.id.txtReleasDate);
            overView = (TextView)rootView.findViewById(R.id.txtOverView);
            movieRating = (TextView)rootView.findViewById(R.id.txtVotingRate);

            Picasso.with(getContext()).load(movie.imgPath).fit().into(MovieImageView);
            movieTitle.setText(movie.movieTitle);
            releaseDate.setText(movie.releaseDate);
            overView.setText( movie.overView);
            movieRating.setText(String.valueOf(movie.userRating));

            if (checkMovieIsInFavorites())
            {
               // Toast.makeText(getActivity(),"Movie Already exists in favorites", Toast.LENGTH_SHORT).show();
                RemoveFromFavorites.setVisibility(View.VISIBLE);
            }
            else {
                AddToFavorite.setVisibility(View.VISIBLE);
            }
            AddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    mDataBaseHelper.setMovie(mFavoriteMovie);
                    try {

                            String i = mDataBaseHelper.insertMovie();
                            Toast.makeText(getActivity(), "Movie Added to your favorites", Toast.LENGTH_LONG).show();

                    }catch (Exception e)
                    {
                        Toast.makeText(getActivity(),"Couldn't Add Movie", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            RemoveFromFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataBaseHelper.setMovie(mFavoriteMovie);
                    try {
                        mDataBaseHelper.deleteMovie();

                    }catch (Exception e){
                        Toast.makeText(getActivity(),"Couldn't Remove Movie", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        return rootView;
    }

    public boolean checkMovieIsInFavorites()
    {
        mDataBaseHelper.setMovie(mFavoriteMovie);
        Cursor c= mDataBaseHelper.getMovie();
        if (c.getCount()!=0)
            return true;
        else
            return false;

    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu,menu);
    }

/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
