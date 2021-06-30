package com.example.chatmatch.Discover;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Matches.MatchCardModel;
import com.example.chatmatch.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class discoverAdapter extends RecyclerView.Adapter<discoverAdapter.ViewHolder> {

    ArrayList<MatchCardModel> matchCardModel;
    Context context;
    private MaterialButton materialButton;

    public discoverAdapter(Context context, ArrayList<MatchCardModel> matchCardModel){
        this.context = context;
        this.matchCardModel = matchCardModel;
    }
    @NonNull
    @Override
    public discoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_row_item, parent, false);
        return new discoverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull discoverAdapter.ViewHolder holder, int position) {
        //Set image to imageView
        holder.imageView.setImageResource(matchCardModel.get(position).getMatchCard());


    }

    @Override
    public int getItemCount() {
        return matchCardModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        MaterialButton rejectbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon);

        }
    }
}
