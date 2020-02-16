package com.mvp.newsappexample.views.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mvp.newsappexample.R;
import com.mvp.newsappexample.interfaces.IView;

public class BaseActivity extends Activity implements IView {

    ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Interface Methods
    @Override
    public void showLoadingProgress() {
        progressDialog = ProgressDialog.show(this, "",getResources().getString(R.string.loading), true);  // Display Progress Dialog
    }

    @Override
    public void hideLoadingProgress() {
        if (progressDialog == null) return;
        progressDialog.dismiss();
    }

    @Override
    public void onError(String reason) {
        Toast.makeText(this,reason,Toast.LENGTH_LONG).show();
    }

}
