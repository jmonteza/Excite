package com.example.chatmatch.Messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatmatch.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ThreadAdapter extends FirestoreRecyclerAdapter<ThreadModel, ThreadAdapter.ThreadHolder> {

    private OnItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ThreadAdapter(@NonNull @NotNull FirestoreRecyclerOptions<ThreadModel> options) {
        super(options);
    }

    /**
     * @param holder
     * @param position
     * @param model    the model object containing the data that should be used to populate the view.
     * @see #onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    @Override
    protected void onBindViewHolder(@NonNull @NotNull ThreadAdapter.ThreadHolder holder, int position, @NonNull @NotNull ThreadModel model) {
        Glide.with(holder.thread_user_image.getContext()).load(model.getPhotoURI()).into(holder.thread_user_image);
        holder.thread_fullname.setText(model.getFull_name());
        holder.thread_recent_message.setText(model.getLast_message());
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @NotNull
    @Override
    public ThreadHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_thread, parent, false);
        return new ThreadHolder(view);
    }

    public void deleteThread(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class ThreadHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView thread_user_image;
        private TextView thread_fullname;
        private TextView thread_recent_message;

        public ThreadHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            thread_user_image = itemView.findViewById(R.id.thread_user_image_image_view);
            thread_fullname = itemView.findViewById(R.id.thread_full_name_text_view);
            thread_recent_message = itemView.findViewById(R.id.thread_recent_message_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION  && listener != null){
                        try {
                            listener.onItemClick(getSnapshots().getSnapshot(position), position);
                        } catch (InterruptedException | GeneralSecurityException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }

    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position) throws InterruptedException, GeneralSecurityException, IOException;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    
}
