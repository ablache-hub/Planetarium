package com.alex.planets.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.planets.DetailsActivity;
import com.alex.planets.R;
import com.alex.planets.database.DatabaseClient;
import com.alex.planets.database.PlanetDao;
import com.alex.planets.models.Planet;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclingListPlanetAdapter extends RecyclerView.Adapter<RecyclingListPlanetAdapter.ViewHolder> {

    private final List<Planet> planets;
    private final Context context;
    private final LifecycleOwner viewLifecycleOwner;
    private PlanetDao planetDao;

    public RecyclingListPlanetAdapter(List<Planet> planets, Context context, LifecycleOwner viewLifecycleOwner) {
        this.planets = planets;
        this.context = context;
        this.viewLifecycleOwner = viewLifecycleOwner;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Déclarer les elements de la card
        private final TextView textView;
        private final CircleImageView imageView;

        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.tvCard);
            imageView = view.findViewById(R.id.profile_image);
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
        Glide.with(this.context).load(planets.get(position).getImage()).into(viewHolder.imageView);
        planetDao = DatabaseClient.getInstance(context).getAppDatabase().planetDao();


        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View cardView) {
                TextView cardText = cardView.findViewById(R.id.tvCard);
                String clickedPlanetName = cardText.getText().toString();

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("planetName", clickedPlanetName);
                context.startActivity(intent);
//                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
//                Log.v("test", ""+test.getName());

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (!(planets == null)) {
            return planets.size();
        } else {
            return 0;
        }
    }
}

