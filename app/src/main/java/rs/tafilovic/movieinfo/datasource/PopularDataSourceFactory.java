package rs.tafilovic.movieinfo.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import io.reactivex.disposables.CompositeDisposable;
import rs.tafilovic.movieinfo.model.Result;
import rs.tafilovic.movieinfo.rest.MoviesController;

public class PopularDataSourceFactory extends DataSource.Factory<Integer, Result> {

    private PopularDataSource topRatedDataSource;
    private MutableLiveData<PopularDataSource> mutableLiveData;

    private CompositeDisposable compositeDisposable;
    private MoviesController moviesController;

    public PopularDataSourceFactory(CompositeDisposable compositeDisposable, MoviesController moviesController) {
        this.compositeDisposable = compositeDisposable;
        this.mutableLiveData = new MutableLiveData<>();
        this.moviesController=moviesController;
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        topRatedDataSource = new PopularDataSource(compositeDisposable, moviesController);
        mutableLiveData.postValue(topRatedDataSource);
        return topRatedDataSource;
    }

    public MutableLiveData<PopularDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

}
