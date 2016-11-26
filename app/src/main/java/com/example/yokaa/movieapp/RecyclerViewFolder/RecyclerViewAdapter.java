package com.example.yokaa.movieapp.RecyclerViewFolder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yokaa.movieapp.R;
import com.example.yokaa.movieapp.MovieDetails.Trailer;

import java.util.ArrayList;

/**
 * Created by yokaa on 11/20/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<viewholder> {
    private  LayoutInflater inflater;
    ArrayList<Trailer> trailers;
    viewholder mviewHolder;

    Context c;

    public RecyclerViewAdapter(Context context , ArrayList<Trailer> trailersParameter) {
        inflater = LayoutInflater.from(context);
        this.trailers=trailersParameter;
        c=context;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =inflater.inflate(R.layout.trailer_item,parent,false);
         mviewHolder = new viewholder(v);
        return mviewHolder;
    }

    @Override
    public void onBindViewHolder(viewholder holder, final int position) {
        final Trailer currentTrailer=trailers.get(position);
        mviewHolder.text.setText(currentTrailer.trailerTitle.toString());

      mviewHolder.text.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             // Toast.makeText(c,currentTrailer.trailerTitle.toString(), Toast.LENGTH_SHORT).show();
              Intent webIntent = new Intent(Intent.ACTION_VIEW,
                      Uri.parse("http://www.youtube.com/watch?v=" + currentTrailer.key.toString()));
              c.startActivity(webIntent);
          }
      });
    }



    @Override
    public int getItemCount() {
        return trailers.size();
    }
}
 class viewholder extends RecyclerView.ViewHolder
{
    ImageView imageView;
    TextView text;
    public viewholder(View itemView) {

        super(itemView);

        text =(TextView) itemView.findViewById(R.id.trailerItemText);
        imageView = (ImageView)itemView.findViewById(R.id.trailerItemImage);
    }
}
