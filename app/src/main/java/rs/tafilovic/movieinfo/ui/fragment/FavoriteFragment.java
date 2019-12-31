package rs.tafilovic.movieinfo.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.tafilovic.movieinfo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public static FavoriteFragment getInstance(Bundle bundle) {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        if (bundle != null)
            favoriteFragment.setArguments(bundle);
        return favoriteFragment;
    }

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

}
