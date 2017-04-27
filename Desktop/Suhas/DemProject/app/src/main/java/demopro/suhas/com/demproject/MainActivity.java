package demopro.suhas.com.demproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Commit json url
    private static final String url = "https://api.github.com/repos/rails/rails/commits";
    private ProgressDialog pDialog;
    private List<DataDictionary> commitList = new ArrayList<DataDictionary>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, commitList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Check for internet
        if(isNetworkAvilable()) {
            JsonArrayRequest commitRequest = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);

                                    JSONObject authorObject = obj.getJSONObject("author");

                                    JSONObject commitObject = obj.getJSONObject("commit");

                                    JSONObject autJsonObj = commitObject.getJSONObject("author");

                                    DataDictionary DataDictionary = new DataDictionary();
                                    DataDictionary.setAuthorName(autJsonObj.getString("name"));
                                    DataDictionary.setThumbnailUrl(authorObject.getString("avatar_url"));
                                    DataDictionary.setCommitMessage(commitObject.getString("message"));
                                    DataDictionary.setCommitID(obj.getString("sha"));
                                    commitList.add(DataDictionary);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            // notifying list adapter about data changes
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    hidePDialog();

                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(commitRequest);
        } else{
        hidePDialog();
        Toast.makeText(getApplicationContext(),"Enable internet",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    private boolean isNetworkAvilable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null;
    }

}

