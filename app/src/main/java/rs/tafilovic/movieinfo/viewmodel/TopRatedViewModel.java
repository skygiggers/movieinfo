package rs.tafilovic.movieinfo.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.disposables.CompositeDisposable;
import rs.tafilovic.movieinfo.datasource.TopRatedDataSource;
import rs.tafilovic.movieinfo.datasource.TopRatedDataSourceFactory;
import rs.tafilovic.movieinfo.model.Result;
import rs.tafilovic.movieinfo.rest.MoviesController;
import rs.tafilovic.movieinfo.rest.NetworkState;

public class TopRatedViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private LiveData<NetworkState> networkLoadStatus;
    private TopRatedDataSourceFactory topRatedDataSourceFactory;
    private LiveData<PagedList<Result>> topRatedLiveData;

    public TopRatedViewModel() {
        topRatedDataSourceFactory = new TopRatedDataSourceFactory(compositeDisposable, new MoviesController());
        topRatedLiveData = new LivePagedListBuilder<Integer, Result>(topRatedDataSourceFactory, buildConfig()).build();
        networkLoadStatus = Transformations.switchMap(topRatedDataSourceFactory.getMutableLiveData(), TopRatedDataSource::getState);
    }

    private PagedList.Config buildConfig() {
        return new PagedList.Config.Builder()
            .setEnablePlaceholders(true) // show not loaded items yet
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .build();
    }

    public LiveData<NetworkState> getNetworkLoadStatus() {
        return networkLoadStatus;
    }

    public LiveData<PagedList<Result>> getTopRatedLiveData() {
        return topRatedLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
