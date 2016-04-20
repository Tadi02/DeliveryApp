package hu.tomkom.deliveryapp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;

public class MainActivity extends AppCompatActivity implements MainScreen{

    @Bind(R.id.remainingDeliveries) TextView remainingDeliveries;
    @Bind(R.id.completedDeliveries) TextView completedDeliveries;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeliveryApplication.injector.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void showGreeting() {
        Toast.makeText(getApplicationContext(), "Cica", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDeliveryNumbers(int completed, int remaining) {
        completedDeliveries.setText(String.valueOf(completed));
        remainingDeliveries.setText(String.valueOf(remaining));
    }
}
