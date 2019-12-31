package rs.tafilovic.movieinfo.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import rs.tafilovic.movieinfo.adapter.viewholder.MovieViewHolder;
import rs.tafilovic.movieinfo.model.Result;
import rs.tafilovic.movieinfo.ui.ClickCallback;

public class MoviesListAdapter extends PagedListAdapter<Result, MovieViewHolder> {

    private ClickCallback callback;

    public MoviesListAdapter(ClickCallback callback) {
        super(Result.RESULT_DIFF_CALLBACK);
        this.callback = callback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MovieViewHolder.init(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.itemView.setOnClickListener(v -> {
            if (callback != null) {
                callback.onClick(getItem(holder.getAdapterPosition()), holder.itemView);
            }
        });
    }

    public void release() {
        this.callback = null;
    }
}
