package com.example.yokaa.movieapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yokaa.movieapp.RecyclerViewFolder.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by yokaa on 11/17/16.
 */

public class TrailersFragment extends Fragment {

    public Trailer trailerOBJ;
    public ArrayList<Trailer> trailersList;
    Bundle bundle = new Bundle();
    public RecyclerView recyclerView ;
    private RecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trailers_fragment, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.trailerList);

        Intent intent = getActivity().getIntent();
        bundle=intent.getBundleExtra("movie");
        int movieID = bundle.getInt("id");
        RequestQueue requestQueue=VolleySingletone.getInstance().getmRequestQueue();
        //http://api.themoviedb.org/3/movie/{id}/videos?api_key=d751b799766ad8a466932e14190daf8a
        StringRequest request = new StringRequest(Request.Method.GET,"http://api.themoviedb.org/3/movie/"+movieID+"/videos?api_key=d751b799766ad8a466932e14190daf8a", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonobj = null;
                try {
                    jsonobj = new JSONObject(response);
                    JSONArray hh = jsonobj.getJSONArray("results");
                    //JSONArray jsonArray=new JSONArray(forecastJsonStr);

                    trailerOBJ = new Trailer();

                    trailersList= new ArrayList<Trailer>();

                    for(int i=0;i<hh.length();i++)
                    {
                        JSONObject jsonObject= hh.getJSONObject(i);
                        trailerOBJ= new Trailer();
                        trailerOBJ.key=jsonObject.getString("key");
                        trailerOBJ.trailerTitle=jsonObject.getString("name");
                        trailersList.add(trailerOBJ);
                       // Toast.makeText(getActivity(), trailerOBJ.trailerTitle, Toast.LENGTH_LONG).show();

                    }

                    adapter = new RecyclerViewAdapter(getActivity(),trailersList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(getContext(),"error response !",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);

        return rootView;
    }
}