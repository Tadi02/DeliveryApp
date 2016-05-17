package hu.tomkom.deliveryapp.network;

import android.content.Context;

public interface NetworkStateHandler{

    void refreshState(Context context);

    boolean isNetworkAvailable();

}
