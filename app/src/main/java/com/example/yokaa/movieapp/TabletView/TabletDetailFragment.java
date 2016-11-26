package com.example.yokaa.movieapp.TabletView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yokaa.movieapp.MovieDetails.DetailFragment;
import com.example.yokaa.movieapp.MovieDetails.ReviewsFragment;
import com.example.yokaa.movieapp.MovieDetails.TrailersFragment;
import com.example.yokaa.movieapp.R;

/**
 * Created by yokaa on 11/25/16.
 */

public class TabletDetailFragment extends Fragment {
public Bundle bundle;
    ViewPager pager = null;

    public TabletDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.tablet_detail_fragment_layout, container, false);
        bundle = getArguments();

        if (bundle!=null) {
            FragmentManager manager = getActivity().getSupportFragmentManager();

            PageAdapter pageAdapter=new PageAdapter(manager);
            pager = (ViewPager) rootView.findViewById(R.id.viewPager);
            pager.setAdapter(new PageAdapter(manager));
        }
        return rootView;

}
class PageAdapter extends FragmentStatePagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        if ( position == 0)
        {
            fragment = new DetailFragment(bundle);

        }
        else if (position == 1)
        {
            fragment = new TrailersFragment(bundle);
        }
        else if (position==2)
        {
            fragment= new ReviewsFragment(bundle);
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
}

