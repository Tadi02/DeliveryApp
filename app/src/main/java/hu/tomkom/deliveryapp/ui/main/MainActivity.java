package hu.tomkom.deliveryapp.ui.main;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.DeliveryListAdapter;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryActivity;

public class MainActivity extends AppCompatActivity implements MainScreen, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.remainingDeliveries) TextView remainingDeliveries;
    @BindView(R.id.completedDeliveries) TextView completedDeliveries;
    @BindView(R.id.todayList) ListView todayList;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeliveryApplication.injector.inject(this);
        ButterKnife.bind(this);
        DeliveryListAdapter adapter = new DeliveryListAdapter(this);
        todayList.setAdapter(adapter);
        List<Delivery> test = new ArrayList<>();
        Delivery d = new Delivery();
        d.setName("Cica Bt.");
        d.setAddress("Máté utca 4.");
        d.setPhone("+36 30 548 6684");
        d.setCompleted(false);
        d.setTime("12:00");
        test.add(d);
        Delivery d2 = new Delivery();
        d2.setName("Big money Kft.");
        d2.setAddress("Kalap utca 4.");
        d2.setPhone("+36 30 999 6684");
        d2.setCompleted(true);
        d2.setTime("13:00");
        test.add(d2);
        adapter.setItems(test);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.fetchData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void setDeliveryNumbers(int completed, int remaining) {
        completedDeliveries.setText(String.valueOf(completed));
        remainingDeliveries.setText(String.valueOf(remaining));
    }

    @Override
    public void showTodaysDeliveries(List<Delivery> deliveries) {

    }

    @Override
    public void navigateToDetails(String id) {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
}
