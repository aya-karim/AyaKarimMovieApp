package com.example.yokaa.movieapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class main_fragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match


    // TODO: Rename and change types of parameters

    jasonMovieObj JMovie;
    String sort= "popular";

    List<jasonMovieObj> jMoviesList;
    public main_fragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters

    List<jasonMovieObj> mMovieList = new ArrayList<>();


    movieAdapter MovieAdapt;
    FetchMovies fetch;
    GridView MoviesListGV;
    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
       if (item.getItemId()==R.id.popular)
       {
           sort="popular";
           new FetchMovies().execute(sort);

       }
        else if (item.getItemId()==R.id.TopRated)
       {
           sort="top_rated";
           new FetchMovies().execute(sort);
       }
                return super.onOptionsItemSelected(item);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v = inflater.inflate(R.layout.fragment_main_fragment, container, false);
        if (isNetworkAvailable(getActivity())==true)
        {
            fetch = new FetchMovies();
            fetch.execute(sort);
        }
        else
        Toast.makeText(getActivity(),"No network Availale",Toast.LENGTH_LONG).show();

        return v;
    }

    class FetchMovies extends AsyncTask<String,Void,List<jasonMovieObj>>{

        @Override
        protected List<jasonMovieObj> doInBackground(String[] params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String forecastJsonStr = null;


            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are available at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                   // URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=d751b799766ad8a466932e14190daf8a");
                final String BaseUrl = "http://api.themoviedb.org/3/movie/";
                final  String ApiKey = "api_key";
                Uri builder = Uri.parse(BaseUrl).buildUpon().appendPath(params[0])
                        .appendQueryParameter(ApiKey,BuildConfig.TheMovieDataBaseKey).build();
                URL url= new URL(builder.toString());
                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    forecastJsonStr = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    forecastJsonStr = null;
                }

                forecastJsonStr = buffer.toString();

                JSONObject jsonobj = new JSONObject(forecastJsonStr);

                JSONArray hh = jsonobj.getJSONArray("results");
                //JSONArray jsonArray=new JSONArray(forecastJsonStr);

                JMovie = new jasonMovieObj();

                jMoviesList= new ArrayList<jasonMovieObj>();

                for(int i=0;i<hh.length();i++){
                    JSONObject jsonObject= hh.getJSONObject(i);
                    //jsonObject.getString("");
                    JMovie= new jasonMovieObj();
                    JMovie.imgPath="http://image.tmdb.org/t/p/w342"+ jsonObject.getString("poster_path");
                    //JMovie.imgPath="http://image.tmdb.org/t/p/w185/9M5ibpQUjoVFjjnP2AdLcof4hAk.jpg";
                    JMovie.overView=jsonObject.getString("overview");
                    JMovie.releaseDate= jsonObject.getString("release_date");
                    JMovie.movieTitle=jsonObject.getString("original_title");
                    JMovie.userRating = jsonObject.getInt("vote_average");

                    jMoviesList.add(JMovie);
                }
                //Toast.makeText(getActivity(), jMoviesList.size(), Toast.LENGTH_LONG).show();
                return jMoviesList;
            } catch (Exception e) {
                Log.e( "ForeCastFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                forecastJsonStr = null;
                Toast.makeText(getActivity(),"Fi error",Toast.LENGTH_LONG).show();
            }


            return null;
        }

        @Override
        protected void onPostExecute(List<jasonMovieObj> MoviesList) {

            MovieAdapt = new movieAdapter(getActivity(),MoviesList);
            MoviesListGV =  (GridView)v.findViewById(R.id.movieGridView);
            MoviesListGV.setAdapter(MovieAdapt);
            MoviesListGV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Bundle movieBundle = new Bundle();

                    int movieIndex = position;
                    movieBundle.putString("movieTitle",jMoviesList.get(movieIndex).movieTitle);
                    movieBundle.putString("releaseDate",jMoviesList.get(movieIndex).releaseDate);
                    movieBundle.putString("overView",jMoviesList.get(movieIndex).overView);
                    movieBundle.putString("imgPath",jMoviesList.get(movieIndex).imgPath);
                    movieBundle.putInt("userRating",jMoviesList.get(movieIndex).userRating);
                    Intent i = new Intent(getActivity(), DetailActivity.class).putExtra("movie",movieBundle);

                   // i.putExtra("movieInfo", movieBundle);
                    startActivity(i);

                }
            });


        }





    }



}
