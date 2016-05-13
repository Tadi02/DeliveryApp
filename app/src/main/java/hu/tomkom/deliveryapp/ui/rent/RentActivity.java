package hu.tomkom.deliveryapp.ui.rent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.tomkom.deliveryapp.DeliveryApplication;
import hu.tomkom.deliveryapp.R;
import hu.tomkom.deliveryapp.model.Comment;
import hu.tomkom.deliveryapp.model.Rent;
import hu.tomkom.deliveryapp.ui.adapter.CommentListAdapter;
import hu.tomkom.deliveryapp.ui.adapter.CommentListEventHandler;

public class RentActivity extends AppCompatActivity implements RentScreen, CommentListEventHandler {

    @Inject
    RentPresenter rentPresenter;

    @BindView(R.id.rentName) TextView rentName;
    @BindView(R.id.rentStatus) TextView rentStatus;
    @BindView(R.id.rentDate) TextView rentDate;
    @BindView(R.id.commentList) ListView commentList;
    @BindView(R.id.addCommentButton) FloatingActionButton addCommentButton;

    private CommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        DeliveryApplication.injector.inject(this);
        ButterKnife.bind(this);
        adapter = new CommentListAdapter(this, this);
        commentList.setAdapter(adapter);

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new AddCommentDialogFragment();
                newFragment.show(getSupportFragmentManager(), "addComment");
            }
        });
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
        adapter.setItems(comments);
    }

    @Override
    public void commentRemoveClicked(String id) {
        rentPresenter.removeComment(id);
    }

    public void commentAdded(String text){
        rentPresenter.addComment(text);
    }


    public static class AddCommentDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(R.layout.dialog_add_comment)
                    .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            EditText commentText = (EditText) getDialog().findViewById(R.id.newComment);
                            RentActivity activity = (RentActivity) getActivity();
                            activity.commentAdded(commentText.getText().toString());
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), null);
            return builder.create();
        }
    }
}
