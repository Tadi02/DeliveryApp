package hu.tomkom.deliveryapp.ui.delivery;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Delivery;
import hu.tomkom.deliveryapp.ui.adapter.DeliveryListAdapter;
import hu.tomkom.deliveryapp.ui.adapter.DeliveryListEventHandler;
import hu.tomkom.deliveryapp.ui.main.MainActivity;
import hu.tomkom.deliveryapp.ui.rent.RentActivity;

public class DeliveryActivity extends AppCompatActivity implements DeliveryScreen, DeliveryListEventHandler, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.todayList) ListView todayList;

    @Inject
    DeliveryPresenter deliveryPresenter;

    private Date date;
    private DeliveryListAdapter adapter;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        DeliveryApplication.injector.inject(this);
        ButterKnife.bind(this);

        this.adapter = new DeliveryListAdapter(this, this);
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
        adapter.setItems(deliveries);
    }

    @Override
    public void navigateToDetails(String id) {
        Intent intent = new Intent(this, RentActivity.class);
        intent.putExtra("rentId", id);
        startActivity(intent);
    }

    @Override
    public void showDate(Date date) {
        this.date = date;
        setTitle(getString(R.string.delivieries) + " " + simpleDateFormat.format(date));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_today) {
            Intent intent = new Intent(this, MainActivity.class);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.changeDate:
                DialogFragment newFragment = new DatePickerFragment();
                Bundle bundle = new Bundle();
                bundle.putLong("date", date.getTime());
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "datePicker");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void dateChanged(Date date){
        deliveryPresenter.setDate(date);
    }

    @Override
    public void itemClicked(String id) {
        deliveryPresenter.listItemClicked(id);
    }

    @Override
    public void butonPressed(String id) {
        deliveryPresenter.markDeliveryCompleted(id);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(this.getArguments().getLong("date")));

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            DeliveryActivity activity = (DeliveryActivity)this.getActivity();
            activity.dateChanged(new Date(year,month,day));
        }
    }


}
