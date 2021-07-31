package com.example.chatmatch.Discovery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.Model.UserModel;
import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DiscoveryAdapter extends FirestoreRecyclerAdapter<UserModel, DiscoveryAdapter.DiscoveryHolder> {

    private OnItemClickListener listener;

    public DiscoveryAdapter(@NonNull @NotNull FirestoreRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull DiscoveryAdapter.DiscoveryHolder holder, int position, @NonNull @NotNull UserModel model) {
        holder.userName.setText(model.getFirstName());
        Glide.with(holder.itemView.getContext()).load(model.getPhotoURI()).into(holder.userDisplay);
    }

    @NonNull
    @NotNull
    @Override
    public DiscoveryHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_row_item,
                parent, false);
        return new DiscoveryHolder(view);
    }

    class DiscoveryHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final ImageView userDisplay;
        private final ImageButton winkBtn;
        private final ImageButton waveBtn;

        public DiscoveryHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.usrCardname);
            userDisplay = itemView.findViewById(R.id.icon);
            winkBtn = itemView.findViewById(R.id.btn_wink);
            waveBtn = itemView.findViewById(R.id.btn_wave);

            winkBtn.setOnClickListener(new View.OnClickListener() {
                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        try {
                            listener.onWinkClick(getSnapshots().getSnapshot(position), position);
                        } catch (InterruptedException | GeneralSecurityException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

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
        void onWinkClick(DocumentSnapshot documentSnapshot, int position) throws InterruptedException, GeneralSecurityException, IOException;

        void onWaveClick(DocumentSnapshot documentSnapshot, int position) throws InterruptedException, GeneralSecurityException, IOException;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
