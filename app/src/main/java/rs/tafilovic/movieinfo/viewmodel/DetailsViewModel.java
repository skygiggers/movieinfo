package rs.tafilovic.movieinfo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import rs.tafilovic.movieinfo.model.Casts;
import rs.tafilovic.movieinfo.rest.MoviesController;
import rs.tafilovic.movieinfo.rest.NetworkState;

public class DetailsViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<NetworkState> networkStateMutableLiveData;
    private MutableLiveData<Casts> castsLiveData;
    private MoviesController moviesController;
    private int movieId;

    public DetailsViewModel(int movieId) {
        moviesController = new MoviesController();
        castsLiveData = new MutableLiveData<>();
        networkStateMutableLiveData = new MutableLiveData<>();
        this.movieId = movieId;
    }

    public LiveData<Casts> getCastsLiveData() {
        compositeDisposable.addAll(
            moviesController.getCredits(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    result -> {
                        castsLiveData.postValue(result);
                        networkStateMutableLiveData.postValue(NetworkState.LOADED);
                    },
                    error -> {
                        String errMessage = error != null ? error.getMessage() : "Unknown error";
                        networkStateMutableLiveData.postValue(new NetworkState(NetworkState.Status.FAILED, errMessage));
                    }
                )
        );

        return castsLiveData;
    }

    public LiveData<NetworkState> getStatus() {
        return networkStateMutableLiveData;
    }
}
