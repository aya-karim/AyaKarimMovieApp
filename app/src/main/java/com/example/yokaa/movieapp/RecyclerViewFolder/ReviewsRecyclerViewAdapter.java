package com.example.yokaa.movieapp.RecyclerViewFolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yokaa.movieapp.R;
import com.example.yokaa.movieapp.MovieDetails.Reviews;

import java.util.ArrayList;

/**
 * Created by yokaa on 11/21/16.
 */

public class ReviewsRecyclerViewAdapter extends RecyclerView.Adapter<com.example.yokaa.movieapp.RecyclerViewFolder.ReviewViewHolder> {


        private LayoutInflater inflater;
        ArrayList<Reviews> ReviewsList;
        com.example.yokaa.movieapp.RecyclerViewFolder.ReviewViewHolder mReviewViewHolder;

        Context c;

        public ReviewsRecyclerViewAdapter(Context context , ArrayList<Reviews> ReviewsPrameter) {
            inflater = LayoutInflater.from(context);
            this.ReviewsList=ReviewsPrameter;
            c=context;
        }

        @Override
        public com.example.yokaa.movieapp.RecyclerViewFolder.ReviewViewHolder onCreateViewHolder(ViewGroup
        parent, int viewType) {
            View v =inflater.inflate(R.layout.review_item,parent,false);
            mReviewViewHolder = new com.example.yokaa.movieapp.RecyclerViewFolder.ReviewViewHolder(v);
            return mReviewViewHolder;
        }

        @Override
        public void onBindViewHolder(com.example.yokaa.movieapp.RecyclerViewFolder.ReviewViewHolder holder, final int position) {
            final Reviews currentReview=ReviewsList.get(position);
            mReviewViewHolder.author.setText(currentReview.auther.toString());
            mReviewViewHolder.reviewContent.setText(currentReview.reviewContent.toString());


        }



        @Override
        public int getItemCount() {
            return ReviewsList.size();
        }
    }

class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView reviewContent;

        public ReviewViewHolder(View itemView) {

            super(itemView);

            author =(TextView) itemView.findViewById(R.id.author);
            reviewContent= (TextView)itemView.findViewById(R.id.reviewcontent);
        }
    }


