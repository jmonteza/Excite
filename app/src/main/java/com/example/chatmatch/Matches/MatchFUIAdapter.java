package com.example.chatmatch.Matches;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.example.chatmatch.Model.UserModel;

import org.jetbrains.annotations.NotNull;

public class MatchFUIAdapter extends FirestoreRecyclerAdapter<UserModel, MatchFUIAdapter.MatchHolder> {


    public MatchFUIAdapter(@NonNull @NotNull FirestoreRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
   protected void onBindViewHolder(@NonNull @NotNull MatchFUIAdapter.MatchHolder holder, int position, @NonNull @NotNull UserModel model) {
        //Set image to imageView
        // holder.imageView.setImageResource(matchCardModel.get(position).getMatchCard());
        Glide.with(holder.imageView.getContext()).load(model.getPhotoURI()).into(holder.imageView);
        //validate button

        holder.rejectbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
                Log.d("click", "clicked");
            }
        });
   }

   @NonNull
   @NotNull
   @Override
   public MatchHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row_item, parent, false);
       return new MatchHolder(view);
   }

    public static class MatchHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final MaterialButton rejectbtn;
        public MatchHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            rejectbtn = (MaterialButton) itemView.findViewById(R.id.btn_wave);
        }
    }
}
