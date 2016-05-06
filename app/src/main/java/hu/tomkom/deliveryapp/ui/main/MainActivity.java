package hu.tomkom.deliveryapp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
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

public class MainActivity extends AppCompatActivity implements MainScreen{

    @BindView(R.id.remainingDeliveries) TextView remainingDeliveries;
    @BindView(R.id.completedDeliveries) TextView completedDeliveries;
    @BindView(R.id.todayList) ListView todayList;

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


}
