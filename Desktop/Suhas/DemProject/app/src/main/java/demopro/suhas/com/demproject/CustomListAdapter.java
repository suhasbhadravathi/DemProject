package demopro.suhas.com.demproject;

/**
 * Created by suhasvijay on 27/04/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataDictionary> commitItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<DataDictionary> commitItems) {
        this.activity = activity;
        this.commitItems = commitItems;
    }

    @Override
    public int getCount() {
        return commitItems.size();
    }

    @Override
    public Object getItem(int location) {
        return commitItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView commitID = (TextView) convertView.findViewById(R.id.commit_id);
        TextView commitMessage = (TextView) convertView.findViewById(R.id.commit_message);

        // getting data for the row
        DataDictionary dataDictionary = commitItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(dataDictionary.getThumbnailUrl(), imageLoader);

        // Name
        name.setText(dataDictionary.getAuthorName());

        // CommitID
        commitID.setText("Commit ID: " + String.valueOf(dataDictionary.getCommitID()));

        //Commit Message
        commitMessage.setText("Commit Message: " + String.valueOf(dataDictionary.getCommitMessage()));

        return convertView;
    }

}