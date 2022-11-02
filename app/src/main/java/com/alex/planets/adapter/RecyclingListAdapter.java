package com.alex.planets.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.planets.models.Planet;
import com.alex.planets.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclingListAdapter extends RecyclerView.Adapter<RecyclingListAdapter.ViewHolder> {

    private List<Planet> planets;
    private Context context;

    public RecyclingListAdapter(List<Planet> planets, Context context) {
        this.planets = planets;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Déclarer les elements de la card
        private final TextView textView;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView);
            imageView = view.findViewById(R.id.imageView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rc_view_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.setText(planets.get(position).getName());
        Log.i("test", planets.get(position).getImage());
        Glide.with(this.context).load(planets.get(position).getImage()).into(viewHolder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return planets.size();
    }
}
