package rs.tafilovic.movieinfo.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import rs.tafilovic.movieinfo.rest.MoviesController;

public class TopRatedViewModelFactory implements ViewModelProvider.Factory {

    private MoviesController moviesController;
    public TopRatedViewModelFactory(MoviesController moviesController){
        this.moviesController=moviesController;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new TopRatedViewModel();
    }
}
