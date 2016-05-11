package hu.tomkom.deliveryapp.ui.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.main.MainActivity;

public class DeliveryActivity extends AppCompatActivity implements DeliveryScreen,  NavigationView.OnNavigationItemSelectedListener  {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    @Inject
    DeliveryPresenter deliveryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        DeliveryApplication.injector.inject(this);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        deliveryPresenter.attachScreen(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        deliveryPresenter.fetchData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        deliveryPresenter.detachScreen();
    }

    @Override
    public void showDeliveries(List<Delivery> deliveries) {

    }

    @Override
    public void navigateToDetails(String id) {

    }

    @Override
    public void showDate(Date date) {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_today) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_deliveries) {
            Intent intent = new Intent(this, DeliveryActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delivery, menu);
        return true;
    }
}
