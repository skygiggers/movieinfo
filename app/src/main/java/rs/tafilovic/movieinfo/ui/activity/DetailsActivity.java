package rs.tafilovic.movieinfo.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rs.tafilovic.movieinfo.R;
import rs.tafilovic.movieinfo.adapter.CastsAdapter;
import rs.tafilovic.movieinfo.rest.GlideApp;
import rs.tafilovic.movieinfo.util.Const;
import rs.tafilovic.movieinfo.viewmodel.DetailsViewModel;
import rs.tafilovic.movieinfo.viewmodel.DetailsViewModelFactory;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CastsAdapter castsAdapter;
    private DetailsViewModel detailsViewModel;
    private ImageView ivPoster;
    private Toolbar toolbar;

    private final String baseImagePath = "https://image.tmdb.org/t/p/w500";

    private String title, posterPath;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();
        init();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler);
        ivPoster = findViewById(R.id.ivPoster);
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString(Const.MOVIE_TITLE);
            posterPath = baseImagePath + bundle.getString(Const.MOVIE_POSTER);
            movieId = bundle.getInt(Const.MOVIE_ID);

            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(title);

            castsAdapter = new CastsAdapter();
            detailsViewModel = ViewModelProviders.of(this, new DetailsViewModelFactory(movieId)).get(DetailsViewModel.class);
            detailsViewModel.getCastsLiveData().observe(this, casts -> castsAdapter.update(casts));
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(castsAdapter);

            GlideApp.with(ivPoster)
                .load(posterPath)
                .into(ivPoster);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
