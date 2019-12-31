package rs.tafilovic.movieinfo.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import io.reactivex.disposables.CompositeDisposable;
import rs.tafilovic.movieinfo.model.Result;
import rs.tafilovic.movieinfo.rest.MoviesController;

public class TopRatedDataSourceFactory extends DataSource.Factory<Integer, Result> {

    private TopRatedDataSource topRatedDataSource;
    private MutableLiveData<TopRatedDataSource> mutableLiveData;

    private CompositeDisposable compositeDisposable;
    private MoviesController moviesController;

    public TopRatedDataSourceFactory(CompositeDisposable compositeDisposable, MoviesController moviesController) {
        this.compositeDisposable = compositeDisposable;
        this.mutableLiveData = new MutableLiveData<>();
        this.moviesController=moviesController;
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        topRatedDataSource = new TopRatedDataSource(compositeDisposable, moviesController);
        mutableLiveData.postValue(topRatedDataSource);
        return topRatedDataSource;
    }

    public MutableLiveData<TopRatedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
