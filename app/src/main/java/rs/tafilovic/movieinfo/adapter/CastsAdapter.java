package rs.tafilovic.movieinfo.adapter;

import android.view.ViewGroup;

import java.util.function.Function;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rs.tafilovic.movieinfo.adapter.viewholder.CastsViewHolder;
import rs.tafilovic.movieinfo.model.Cast;
import rs.tafilovic.movieinfo.model.Casts;

public class CastsAdapter extends RecyclerView.Adapter<CastsViewHolder> {

    private Casts casts;

    public CastsAdapter() {
    }

    public void update(Casts casts) {
        this.casts = casts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CastsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CastsViewHolder.init(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CastsViewHolder holder, int position) {
        holder.bind(casts.getCast().get(position));
    }

    @Override
    public int getItemCount() {
        return casts != null && casts.getCast() != null ? casts.getCast().size() : 0;
    }
}
