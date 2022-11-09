package com.alex.planets.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.alex.planets.fragments.PlanetFragment;
import com.alex.planets.fragments.SatelitteFragment;

public class ViewPagerAdapterPlanets extends FragmentStateAdapter {
    public ViewPagerAdapterPlanets(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        Fragment fragment;

        switch (position) {
            case 0:
                fragment = PlanetFragment.newInstance();
                break;
            case 1:
                fragment = SatelitteFragment.newInstance();
                break;
            default:
                return null;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
