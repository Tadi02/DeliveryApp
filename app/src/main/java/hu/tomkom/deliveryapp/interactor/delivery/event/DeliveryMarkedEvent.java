package hu.tomkom.deliveryapp.interactor.delivery.event;

public class DeliveryMarkedEvent {

    private boolean success = false;

    private String source = "";

    public boolean isSuccess() {
        return success;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
