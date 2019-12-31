package rs.tafilovic.movieinfo.ui;

import android.view.View;

import rs.tafilovic.movieinfo.model.Result;

public interface ClickCallback {
    void onClick(Result movie, View sharedView);
}
