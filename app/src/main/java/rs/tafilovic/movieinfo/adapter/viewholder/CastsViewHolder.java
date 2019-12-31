package rs.tafilovic.movieinfo.adapter.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.function.Function;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rs.tafilovic.movieinfo.R;
import rs.tafilovic.movieinfo.model.Cast;
import rs.tafilovic.movieinfo.rest.GlideApp;

public class CastsViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG= CastsViewHolder.class.getSimpleName();

    private TextView tvCharacter, tvName;
    private ImageView ivProfile;
    private String baseImagePath = "https://image.tmdb.org/t/p/w500";


    public static CastsViewHolder init(ViewGroup parent){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cast, parent, false);
        return new CastsViewHolder(view);
    }

    private CastsViewHolder(@NonNull View view) {
        super(view);
        tvName=view.findViewById(R.id.tvName);
        tvCharacter=view.findViewById(R.id.tvCharacter);
        ivProfile=view.findViewById(R.id.ivProfile);
    }

    public void bind(Cast cast){
        tvName.setText(cast.getName());
        tvCharacter.setText(cast.getCharacter());
        String fullImagePath=baseImagePath+cast.getProfilePath();
        GlideApp.with(ivProfile)
            .load(fullImagePath)
            .into(ivProfile);
    }
}
