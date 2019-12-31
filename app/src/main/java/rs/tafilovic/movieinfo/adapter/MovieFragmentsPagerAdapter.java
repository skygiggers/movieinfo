package rs.tafilovic.movieinfo.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import rs.tafilovic.movieinfo.R;
import rs.tafilovic.movieinfo.ui.fragment.FavoriteFragment;
import rs.tafilovic.movieinfo.ui.fragment.MostPopularFragment;
import rs.tafilovic.movieinfo.ui.fragment.TopRatedFragment;

public class MovieFragmentsPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> moviesFragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public MovieFragmentsPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        titles.add(0, context.getString(R.string.top_rated));
        titles.add(1, context.getString(R.string.most_popular));
        titles.add(2, context.getString(R.string.favorite));

        moviesFragments.add(0, TopRatedFragment.getInstance(null));
        moviesFragments.add(1, MostPopularFragment.getInstance(null));
        moviesFragments.add(2, FavoriteFragment.getInstance(null));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return position > -1 && position < 3 ? moviesFragments.get(position) : moviesFragments.get(0);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return position > -1 && position < 3 ? titles.get(position) : "";
    }

    @Override
    public int getCount() {
        return moviesFragments.size();
    }
}
