package hu.tomkom.deliveryapp.mock;

import android.content.Context;

import hu.tomkom.deliveryapp.network.NetworkStateHandler;

public class MockNetworkStateHandler implements NetworkStateHandler {

    @Override
    public void refreshState(Context context) {

    }

    @Override
    public boolean isNetworkAvailable() {
        return true;
    }
}
