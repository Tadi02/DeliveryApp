package hu.tomkom.deliveryapp.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Delivery;

public class DeliveryListAdapter extends BaseAdapter {

    List<Delivery> items = new ArrayList<>();

    LayoutInflater inflater;

    public DeliveryListAdapter(Activity activity) {
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setItems(List<Delivery> items){
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Delivery getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Delivery delivery = getItem(position);

        holder.name.setText(delivery.getName());
        holder.address.setText(delivery.getAddress());
        holder.contact.setText(delivery.getPhone());
        holder.time.setText(delivery.getTime());

        if(delivery.isCompleted()){
            holder.completeButton.setEnabled(false);
        }else{
            holder.completeButton.setEnabled(true);
        }

        return view;
    }

    static class ViewHolder{
        @BindView(R.id.itemName) TextView name;
        @BindView(R.id.itemAddress) TextView address;
        @BindView(R.id.itemContact) TextView contact;
        @BindView(R.id.itemTime) TextView time;
        @BindView(R.id.itemCompleteButton) Button completeButton;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
