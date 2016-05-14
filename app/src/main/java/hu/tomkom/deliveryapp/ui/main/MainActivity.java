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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.adapter.DeliveryListAdapter;
import hu.tomkom.deliveryapp.ui.adapter.DeliveryListEventHandler;
import hu.tomkom.deliveryapp.ui.delivery.DeliveryActivity;
import hu.tomkom.deliveryapp.ui.rent.RentActivity;

public class MainActivity extends AppCompatActivity implements MainScreen, DeliveryListEventHandler, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.remainingDeliveries) TextView remainingDeliveries;
    @BindView(R.id.completedDeliveries) TextView completedDeliveries;
    @BindView(R.id.todayList) ListView todayList;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    @Inject
    MainPresenter mainPresenter;

    private DeliveryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeliveryApplication.injector.inject(this);
        ButterKnife.bind(this);
        adapter = new DeliveryListAdapter(this, this);
        todayList.setAdapter(adapter);

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
        adapter.setItems(deliveries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToDetails(String id) {
        Intent intent = new Intent(this, RentActivity.class);
        intent.putExtra("rentId", id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_deliveries) {
            Intent intent = new Intent(this, DeliveryActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void itemClicked(String id) {
        mainPresenter.listItemClicked(id);
    }

    @Override
    public void butonPressed(String id) {
        mainPresenter.markDeliveryCompleted(id);
    }
}
