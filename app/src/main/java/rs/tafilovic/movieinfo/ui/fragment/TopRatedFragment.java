package rs.tafilovic.movieinfo.ui.fragment;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rs.tafilovic.movieinfo.R;
import rs.tafilovic.movieinfo.adapter.MoviesListAdapter;
import rs.tafilovic.movieinfo.model.Result;
import rs.tafilovic.movieinfo.rest.NetworkState;
import rs.tafilovic.movieinfo.ui.ClickCallback;
import rs.tafilovic.movieinfo.ui.activity.DetailsActivity;
import rs.tafilovic.movieinfo.util.Const;
import rs.tafilovic.movieinfo.viewmodel.TopRatedViewModel;

/**
 * Class to present list of Top rated movies
 */
public class TopRatedFragment extends Fragment implements ClickCallback {

    private static final String TAG = TopRatedFragment.class.getSimpleName();

    private TopRatedViewModel topRatedViewModel;
    private MoviesListAdapter adapter;
    private ProgressBar progressBar;

    public static TopRatedFragment getInstance(Bundle bundle) {
        TopRatedFragment topRatedFragment = new TopRatedFragment();
        if (bundle != null)
            topRatedFragment.setArguments(bundle);
        return topRatedFragment;
    }

    public TopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progressBar);

        int orientation = getResources().getConfiguration().orientation;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), orientation == Configuration.ORIENTATION_PORTRAIT ? 1 : 2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new MoviesListAdapter(this);

        topRatedViewModel = ViewModelProviders.of(this).get(TopRatedViewModel.class);
        topRatedViewModel.getNetworkLoadStatus().observe(this, state -> {
            progressBar.setVisibility(state.getStatus().getStatus() == NetworkState.Status.LOADED ? View.GONE : View.VISIBLE);
        });
        topRatedViewModel.getTopRatedLiveData().observe(this, results -> adapter.submitList(results));

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Result movie, View sharedView) {
        if (movie != null) {
            ImageView view=sharedView.findViewById(R.id.ivPoster);
            Intent intent = new Intent(getContext(), DetailsActivity.class);
            intent.putExtra(Const.MOVIE_TITLE, movie.getTitle());
            intent.putExtra(Const.MOVIE_POSTER, movie.getPosterPath());
            intent.putExtra(Const.MOVIE_ID, movie.getId());
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(Objects.requireNonNull(getActivity()), view, "poster");

            startActivity(intent, options.toBundle());
        }
    }
}
