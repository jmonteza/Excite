package com.example.chatmatch.Discover;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmatch.Matches.MatchCardModel;
import com.example.chatmatch.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class discoverAdapter extends RecyclerView.Adapter<discoverAdapter.ViewHolder> {

    ArrayList<MatchCardModel> matchCardModel;
    Context context;
    private MaterialButton materialButton;
    private Palette.Swatch vibrantSwatch;
    private Palette.Swatch lightVibrantSwatch;
    private Palette.Swatch darkVibrantSwatch;
    private Palette.Swatch mutedSwatch;
    private Palette.Swatch lightMutedSwatch;
    private Palette.Swatch darkMutedSwatch;

    public discoverAdapter(Context context, ArrayList<MatchCardModel> matchCardModel){
        this.context = context;
        this.matchCardModel = matchCardModel;
    }
    @NonNull
    @Override
    public discoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discover_row_item, parent, false);
        return new discoverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull discoverAdapter.ViewHolder holder, int position) {

        Bitmap bitmap = ((BitmapDrawable) holder.imageView.getDrawable()).getBitmap();


//            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.bottom_rect);
//            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
//            DrawableCompat.setTint(wrappedDrawable, vibrant.getRgb());

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                vibrantSwatch = palette.getVibrantSwatch();
                lightVibrantSwatch = palette.getLightVibrantSwatch();
                darkVibrantSwatch = palette.getDarkVibrantSwatch();
                mutedSwatch = palette.getMutedSwatch();
                lightMutedSwatch = palette.getLightMutedSwatch();
                darkMutedSwatch = palette.getDarkMutedSwatch();

                Palette.Swatch vibrant = palette.getVibrantSwatch();
                Log.d("vibrant", vibrant+"");
                if (vibrant != null) {
                    Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.bottom_rect);
                    assert unwrappedDrawable != null;
                    Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, vibrant.getRgb());
                    Log.d("vibrant color", vibrant.getRgb()+"");



                }
            }
        });
        //Set image to imageView
        holder.imageView.setImageResource(matchCardModel.get(position).getMatchCard());


    }

//    public void nextSwatch(View v){
//        Palette.Swatch currentSwatch = null;
//
//
//    }
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
