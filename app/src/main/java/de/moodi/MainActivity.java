package de.moodi;

/**
 * Created by Daniel on 03.11.2017.
 *
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements DownloadCallback {

    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "https://moodibackend.herokuapp.com/mood");
        startDownload();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return false;
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            //mNetworkFragment.startDownload();
            //mDownloading = true;
        }
    }

    @Override
    public void updateFromDownload(String result) {
        if (result != null) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            System.out.println("Lukas result: " + result);
        } else {
            Toast.makeText(getApplicationContext(), "Connection error", Toast.LENGTH_SHORT).show();
            System.out.println("Lukas: Connection error");
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                System.out.println("Lukas: Progress error");
                break;
            case Progress.CONNECT_SUCCESS:
                System.out.println("Lukas: Progress connect success");
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                System.out.println("Lukas: Progress get input stream success");
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                System.out.println("Lukas: Progress process input stream in progress");
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                System.out.println("Lukas: Progress process input stream success");
                break;
        }
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }
}
