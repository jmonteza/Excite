package com.example.chatmatch.Matches;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    ArrayList<MatchCardModel> matchCardModel;
    Context context;
    private MaterialButton materialButton;

    public MatchAdapter(Context context, ArrayList<MatchCardModel> matchCardModel){
        this.context = context;
        this.matchCardModel = matchCardModel;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Set image to imageView
        holder.imageView.setImageResource(matchCardModel.get(position).getMatchCard());

        //validate button

        holder.rejectbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
                Log.d("click", "clicked");
            }
        });
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
            imageView = itemView.findViewById(R.id.imgView);
            this.rejectbtn = (MaterialButton) itemView.findViewById(R.id.btn_reject);
        }
    }
}
