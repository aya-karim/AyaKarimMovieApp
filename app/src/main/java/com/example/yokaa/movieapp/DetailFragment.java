package com.example.yokaa.movieapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters

    Bundle bundle = new Bundle();
    jasonMovieObj movie = new jasonMovieObj();
    jasonMovieObj mFavoriteMovie = new jasonMovieObj();
    dbHelper mDataBaseHelper;
    ImageView MovieImageView ;
    TextView movieTitle ;
    TextView releaseDate ;
    TextView overView ;
    TextView movieRating ;

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
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("movie"))
        {
            bundle=intent.getBundleExtra("movie");
            movie.userRating=bundle.getInt("userRating");
            movie.imgPath=bundle.get("imgPath").toString();
            movie.overView=bundle.get("overView").toString();
            movie.releaseDate=bundle.get("releaseDate").toString();
            movie.movieTitle=bundle.get("movieTitle").toString();
            Toast.makeText(getActivity(),"intent loaded",Toast.LENGTH_LONG);

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
        }
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== R.id.AddToFavorite)
        {
            mFavoriteMovie=movie;
            mDataBaseHelper = new dbHelper(getContext());


            mDataBaseHelper.setMovie(mFavoriteMovie);

            try {
                String i = mDataBaseHelper.insertMovie();
             Toast.makeText(getActivity(),"Movie Added to your favorites", Toast.LENGTH_LONG).show();
              Log.i("eshta3'al",i);


            }catch (Exception e)
            {
                Toast.makeText(getActivity(),"Couldn't Add Movie", Toast.LENGTH_SHORT).show();
                Log.i("mashta3'alsh",e.getMessage());
            }
        }
        else
            Toast.makeText(getContext(),"Nothing Selected", Toast.LENGTH_SHORT).show();


        return super.onOptionsItemSelected(item);
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
