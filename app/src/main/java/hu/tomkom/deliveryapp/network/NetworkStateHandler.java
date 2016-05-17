package hu.tomkom.deliveryapp.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class NetworkStateHandler extends BroadcastReceiver{

    public abstract void refreshState(Context context);

    @Override
    public void onReceive(Context context, Intent intent) {
        refreshState(context);
    }

    public abstract boolean isNetworkAvailable();

}
