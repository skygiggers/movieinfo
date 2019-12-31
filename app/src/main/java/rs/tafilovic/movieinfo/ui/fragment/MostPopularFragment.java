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
import rs.tafilovic.movieinfo.viewmodel.PopularViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostPopularFragment extends Fragment implements ClickCallback {

    private static final String TAG = MostPopularFragment.class.getSimpleName();

    private PopularViewModel popularViewModel;
    private MoviesListAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public static MostPopularFragment getInstance(Bundle bundle) {
        MostPopularFragment mostPopularFragment = new MostPopularFragment();
        if (bundle != null)
            mostPopularFragment.setArguments(bundle);
        return mostPopularFragment;
    }


    public MostPopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progressBar);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 1 : 2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new MoviesListAdapter(this);

        popularViewModel = ViewModelProviders.of(this).get(PopularViewModel.class);

        popularViewModel.getNetworkLoadStatus().observe(this, state -> {
            progressBar.setVisibility(state.getStatus().getStatus() == NetworkState.Status.LOADED ? View.GONE : View.VISIBLE);
        });

        popularViewModel.getPopularLiveData().observe(this, results -> adapter.submitList(results));

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
