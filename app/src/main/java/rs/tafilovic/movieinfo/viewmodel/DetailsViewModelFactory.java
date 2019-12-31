package rs.tafilovic.movieinfo.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private int movieId;

    public DetailsViewModelFactory(int movieId) {
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailsViewModel(movieId);
    }
}
