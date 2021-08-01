package com.example.chatmatch.Matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.Model.UserModel;
import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MatchFUIAdapter extends FirestoreRecyclerAdapter<UserModel, MatchFUIAdapter.MatchHolder> {

    private OnItemClickListener listener;

    public MatchFUIAdapter(@NonNull @NotNull FirestoreRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MatchFUIAdapter.MatchHolder holder, int position, @NonNull @NotNull UserModel model) {
        //Set image to imageView
        // holder.imageView.setImageResource(matchCardModel.get(position).getMatchCard());
        Glide.with(holder.imageView.getContext()).load(model.getPhotoURI()).into(holder.imageView);
        //validate button

        // holder.rejectbtn.setOnClickListener(new View.OnClickListener(){
        //     @Override
        //     public void onClick(View v) {
        //         /// button click event
        //         Log.d("click", "clicked");
        //     }
        // });
    }

    @NonNull
    @NotNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row_item, parent, false);
        return new MatchHolder(view);
    }

    public class MatchHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final MaterialButton waveBtn;

        public MatchHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            waveBtn = (MaterialButton) itemView.findViewById(R.id.btn_wave);

            waveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        try {
                            listener.onWaveClick(getSnapshots().getSnapshot(position), position);
                        } catch (InterruptedException | GeneralSecurityException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onWaveClick(DocumentSnapshot documentSnapshot, int position) throws InterruptedException, GeneralSecurityException, IOException;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
