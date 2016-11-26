package com.example.yokaa.movieapp.MovieDetails;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yokaa.movieapp.R;
import com.example.yokaa.movieapp.TabletView.TabletDetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TabletDetailFragment Detail = new TabletDetailFragment();


        Intent intent = this.getIntent();
        Bundle bundle= new Bundle();
        bundle=intent.getBundleExtra("movie");
        Detail.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.DetailActivityLayout,Detail);
        fragmentTransaction.commit();





    }
}

