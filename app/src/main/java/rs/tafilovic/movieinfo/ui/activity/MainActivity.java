package rs.tafilovic.movieinfo.ui.activity;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import rs.tafilovic.movieinfo.R;
import rs.tafilovic.movieinfo.adapter.MovieFragmentsPagerAdapter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    /**
     * Inherited from {@link FragmentPagerAdapter} class instance which holds three main pages
     * {@link rs.tafilovic.movieinfo.ui.fragment.TopRatedFragment},
     * {@link rs.tafilovic.movieinfo.ui.fragment.MostPopularFragment} and
     * {@link rs.tafilovic.movieinfo.ui.fragment.FavoriteFragment} fragments
     */
    private MovieFragmentsPagerAdapter movieFragmentsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the pager adapter contents.
     */
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        movieFragmentsPagerAdapter = new MovieFragmentsPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(movieFragmentsPagerAdapter);

        TabLayout mTabLayout=findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
