package com.example.yokaa.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yokaa.movieapp.RecyclerViewFolder.RecyclerViewAdapter;
import com.example.yokaa.movieapp.RecyclerViewFolder.ReviewsRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yokaa on 11/17/16.
 */

public class ReviewsFragment extends Fragment {

    public RecyclerView recyclerView ;
    private ReviewsRecyclerViewAdapter adapter;

    public Reviews ReviewOBJ;
    public ArrayList<Reviews> reviewsList;
    Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reviews_fragment, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.ReviewsList);


        Intent intent = getActivity().getIntent();
        bundle=intent.getBundleExtra("movie");
        int movieID = bundle.getInt("id");
        RequestQueue requestQueue=VolleySingletone.getInstance().getmRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, "http://api.themoviedb.org/3/movie/" + movieID + "/reviews?api_key=d751b799766ad8a466932e14190daf8a", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonobj = null;
                try {
                    jsonobj = new JSONObject(response);
                    JSONArray hh = jsonobj.getJSONArray("results");
                    //JSONArray jsonArray=new JSONArray(forecastJsonStr);

                    ReviewOBJ = new Reviews();

                    reviewsList= new ArrayList<Reviews>();

                    for(int i=0;i<hh.length();i++)
                    {
                        JSONObject jsonObject= hh.getJSONObject(i);
                        //jsonObject.getString("");
                        ReviewOBJ= new Reviews();
                        ReviewOBJ.reviewID=jsonObject.getString("id");
                        ReviewOBJ.auther=jsonObject.getString("author");
                        ReviewOBJ.reviewContent= jsonObject.getString("content");
                        reviewsList.add(ReviewOBJ);
                        //Toast.makeText(getActivity(), ReviewOBJ.auther, Toast.LENGTH_LONG).show();
                        // Log.i("trailer", "onResponse: I got the data");


                        adapter = new ReviewsRecyclerViewAdapter(getActivity(),reviewsList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"there's an Error in reviews",Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);

        return rootView;
    }
}
