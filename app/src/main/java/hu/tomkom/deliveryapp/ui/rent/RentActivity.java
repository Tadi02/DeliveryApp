package hu.tomkom.deliveryapp.ui.rent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Rent;

public class RentActivity extends AppCompatActivity implements RentScreen {

    @Inject
    RentPresenter rentPresenter;

    @BindView(R.id.rentName) TextView rentName;
    @BindView(R.id.rentStatus) TextView rentStatus;
    @BindView(R.id.rentDate) TextView rentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        DeliveryApplication.injector.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rentPresenter.attachScreen(this);
        rentPresenter.setRentId(getIntent().getExtras().getString("rentId"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        rentPresenter.fetchData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        rentPresenter.detachScreen();
    }

    @Override
    public void showRentData(Rent rent) {
        setTitle(rent.getName());
        rentName.setText(rent.getName());
        rentStatus.setText(rent.getStatus().toString());
        rentDate.setText(rent.getStart() + " - " + rent.getEnd());
    }

    @Override
    public void showComments(List<Comment> comments) {

    }
}
