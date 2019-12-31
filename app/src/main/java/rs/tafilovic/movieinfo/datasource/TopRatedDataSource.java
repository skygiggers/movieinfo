package rs.tafilovic.movieinfo.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.disposables.CompositeDisposable;
import rs.tafilovic.movieinfo.model.Result;
import rs.tafilovic.movieinfo.rest.MoviesController;
import rs.tafilovic.movieinfo.rest.NetworkState;

public class TopRatedDataSource extends PageKeyedDataSource<Integer, Result> {

    private static final String TAG = TopRatedDataSource.class.getSimpleName();

    private MoviesController moviesController;
    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<NetworkState> initialLoading;
    private CompositeDisposable compositeDisposable;

    public TopRatedDataSource(CompositeDisposable compositeDisposable, MoviesController moviesController) {
        this.compositeDisposable = compositeDisposable;
        this.moviesController = moviesController;
        this.networkState = new MutableLiveData<>();
        this.initialLoading = new MutableLiveData<>();
    }

    public LiveData<NetworkState> getState(){
        return networkState;
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Result> callback) {
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        compositeDisposable.addAll(
            moviesController.getTopRated(1)
                .subscribe(
                    response -> {
                        Log.d(TAG, "loadInitial() - page: 1 result: "+response.getResults().get(0).toString());
                        initialLoading.postValue(NetworkState.LOADED);
                        networkState.postValue(NetworkState.LOADED);
                        callback.onResult(response.getResults(), null, 2);
                    },
                    error -> {
                        String errorMessage = error.getMessage() != null ? error.getMessage() : "Unknown error";
                        initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                    })
        );
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Result> callback) {
        Log.d(TAG, "loadAfter() + pageKey="+params.key);
        compositeDisposable.addAll(
            moviesController.getTopRated(params.key).subscribe(
                response -> {
                    Log.d(TAG, "loadAfter() - page: " + params.key+" result: "+response.getResults().get(0).toString());
                    Integer nextPage = (params.key == response.getTotalPages()) ? null : params.key + 1;
                    callback.onResult(response.getResults(), nextPage);
                },
                error -> {
                    String errorMessage = error.getMessage() != null ? error.getMessage() : "Unknown error";
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                }
            )
        );
    }
}
