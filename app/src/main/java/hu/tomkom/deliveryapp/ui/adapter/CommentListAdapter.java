package hu.tomkom.deliveryapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Comment;

public class CommentListAdapter extends BaseAdapter {

    List<Comment> items = new ArrayList<>();

    LayoutInflater inflater;
    CommentListEventHandler eventHandler;

    public CommentListAdapter(Activity activity, CommentListEventHandler eventHandler) {
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.eventHandler = eventHandler;
    }

    public void setItems(List<Comment> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Comment getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.comment_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        final Comment comment = getItem(position);

        holder.text.setText(comment.getText());
        holder.date.setText(comment.getTime());

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventHandler.commentRemoveClicked(String.valueOf(getItem(position).getId()));
            }
        });

        return view;
    }

    static class ViewHolder{
        @BindView(R.id.commentDate) TextView date;
        @BindView(R.id.commentText) TextView text;
        @BindView(R.id.commentRemoveButton) ImageButton removeButton;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
