package hu.tomkom.deliveryapp.ui.delivery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Delivery;

public class DeliveryActivity extends AppCompatActivity implements DeliveryScreen {

    @Inject
    DeliveryPresenter deliveryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DeliveryApplication.injector.inject(this);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        deliveryPresenter.attachScreen(this);
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

}
