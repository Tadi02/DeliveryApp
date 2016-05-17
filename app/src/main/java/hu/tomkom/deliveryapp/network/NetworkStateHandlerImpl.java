package hu.tomkom.deliveryapp.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;

import hu.tomkom.deliveryapp.network.event.NetworkStateChangedEvent;

public class NetworkStateHandlerImpl extends BroadcastReceiver implements NetworkStateHandler{

    private boolean networkAvailable = true;

    public void refreshState(Context context){
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info != null && info.isConnectedOrConnecting()){
            networkAvailable = true;
        }else{
            networkAvailable = false;
        }
        NetworkStateChangedEvent event = new NetworkStateChangedEvent();
        event.setNetworkAvailable(networkAvailable);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        refreshState(context);
    }

    public boolean isNetworkAvailable() {
        return networkAvailable;
    }

}
