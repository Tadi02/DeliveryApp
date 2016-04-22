package hu.tomkom.deliveryapp.ui.rent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Rent;

public class RentActivity extends AppCompatActivity implements RentScreen {

    @Inject
    RentPresenter rentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        DeliveryApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rentPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        rentPresenter.detachScreen();
    }

    @Override
    public void showRentData(Rent rent) {

    }

    @Override
    public void showComments(List<Comment> comments) {

    }

    @Override
    public void showCommentDialog() {

    }
}
