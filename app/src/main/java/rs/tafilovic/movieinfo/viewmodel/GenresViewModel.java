package rs.tafilovic.movieinfo.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import rs.tafilovic.movieinfo.model.Genres;
import rs.tafilovic.movieinfo.rest.MoviesController;
import rs.tafilovic.movieinfo.rest.NetworkState;

public class GenresViewModel extends ViewModel {

    private MoviesController moviesController;
    private MutableLiveData<Genres> genresMutableLiveData;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<NetworkState> networkStateMutableLiveData;

    public GenresViewModel() {
        moviesController = new MoviesController();
        genresMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Genres> getGenresMutableLiveData() {

        if (genresMutableLiveData.getValue() == null) {
            networkStateMutableLiveData.postValue(NetworkState.LOADING);

            compositeDisposable.addAll(
                moviesController.getGenres()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        result -> {
                            genresMutableLiveData.postValue(result);
                            networkStateMutableLiveData.postValue(NetworkState.LOADED);
                        },
                        error -> {
                            String errMessage = error != null ? error.getMessage() : "Unknown error";
                            networkStateMutableLiveData.postValue(new NetworkState(NetworkState.Status.FAILED, errMessage));
                        }
                    )
            );
        }
        return genresMutableLiveData;
    }

    public MutableLiveData<NetworkState> getNetworkStateMutableLiveData() {
        return networkStateMutableLiveData;
    }
}
