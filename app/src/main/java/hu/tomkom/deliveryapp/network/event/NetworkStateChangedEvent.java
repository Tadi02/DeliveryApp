package hu.tomkom.deliveryapp.network.event;

public class NetworkStateChangedEvent {

    private boolean networkAvailable;

    public boolean isNetworkAvailable() {
        return networkAvailable;
    }

    public void setNetworkAvailable(boolean networkAvailable) {
        this.networkAvailable = networkAvailable;
    }
}
