package com.example.android.a7learntutorialapp.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.a7learntutorialapp.DataFakeGenerator;
import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.adapter.AppFeaturesAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AppFeaturesAdapter appFeaturesAdapter;
    private Snackbar connectivityMessageSnackBar;
    private CoordinatorLayout coordinatorLayout;
    private ConnectivityListener connectivityListener;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }


    @Override
    protected void onStart() {
        super.onStart();
        connectivityListener = new ConnectivityListener();
        registerReceiver(connectivityListener, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(connectivityListener);
        super.onStop();
    }


    private void setupViews() {
        setupRecyclerView();
        setupToolbar();
        setupNavigationView();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.float_action_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(coordinatorLayout, "Float Action Button Clicked!!!", Snackbar.LENGTH_LONG)
                        .setAction("retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Retry Button Clicked!!!", Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (i == 0)
                    return 2;
                else
                    return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        appFeaturesAdapter = new AppFeaturesAdapter(this);
        recyclerView.setAdapter(appFeaturesAdapter);
        appFeaturesAdapter.setAppFeatures(DataFakeGenerator.getAppFeatures(this));

    }

    private void setupToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, 0, 0);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void setupNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;
                    case R.id.navigation_menu_store:
                        startActivity(new Intent(MainActivity.this, BoutiqueActivity.class));
                        break;
                    case R.id.navigation_menu_main_activity:
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }

    private class ConnectivityListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = networkInfo != null && networkInfo.isConnected();

            if (isConnected) {
                if (connectivityMessageSnackBar != null) {
                    connectivityMessageSnackBar.dismiss();
                }
            } else {
                connectivityMessageSnackBar = Snackbar.make(coordinatorLayout, "دسترسی به اینترنت ندارید، اینترنت خود را بررسی کنید.", Snackbar.LENGTH_INDEFINITE);
                connectivityMessageSnackBar.show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_view_github:
                Toast.makeText(this, "Github menu item clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_item_view_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.menu_item_sign_out:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
