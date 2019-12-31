package rs.tafilovic.movieinfo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.disposables.CompositeDisposable;
import rs.tafilovic.movieinfo.datasource.PopularDataSource;
import rs.tafilovic.movieinfo.datasource.PopularDataSourceFactory;
import rs.tafilovic.movieinfo.model.Result;
import rs.tafilovic.movieinfo.rest.MoviesController;
import rs.tafilovic.movieinfo.rest.NetworkState;

public class PopularViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private LiveData<NetworkState> networkLoadStatus;
    private PopularDataSourceFactory popularDataSourceFactory;
    private LiveData<PagedList<Result>> popularLiveData;

    public PopularViewModel() {
        popularDataSourceFactory = new PopularDataSourceFactory(compositeDisposable, new MoviesController());
        popularLiveData = new LivePagedListBuilder<Integer, Result>(popularDataSourceFactory, buildConfig()).build();
        networkLoadStatus = Transformations.switchMap(popularDataSourceFactory.getMutableLiveData(), PopularDataSource::getState);
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

    public LiveData<PagedList<Result>> getPopularLiveData() {
        return popularLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
