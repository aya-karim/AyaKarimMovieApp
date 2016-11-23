package com.example.yokaa.movieapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.annotation.Retention;

public class DetailActivity extends AppCompatActivity {

    ViewPager pager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        pager = (ViewPager)findViewById(R.id.viewPager);

        FragmentManager manager = getSupportFragmentManager();
        pager.setAdapter(new PageAdapter(manager));


//        DetailFragment Detail = new DetailFragment();
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//
//        fragmentTransaction.add(R.id.FramelayoutDetail,Detail);
//        fragmentTransaction.commit();

    }
}

class PageAdapter extends FragmentStatePagerAdapter{

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment= null;
        if ( position == 0)
        {
            fragment = new DetailFragment();

        }
        else if (position == 1)
        {
            fragment = new TrailersFragment();
        }
        else if (position==2)
        {
            fragment= new ReviewsFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position==0)
            return "Movie Details";
        else if (position==1)
            return "Trailers";
        else if(position==2)
            return "Reviews";

        return "none";
    }
}
