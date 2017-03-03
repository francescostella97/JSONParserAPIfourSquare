package by.wink.jsonparsesample.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import by.wink.jsonparsesample.R;
import by.wink.jsonparsesample.adapters.PlacesAdapter;
import by.wink.jsonparsesample.models.Place;
import by.wink.jsonparsesample.services.FoursquareAPI;

public class SampleApiActivity extends AppCompatActivity implements TextWatcher {

    private static final String CATEGORY  ="&categoryId=4bf58dd8d48988d110941735";

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PlacesAdapter adapter;

    EditText searchEt, cityET;
    Button searchBtn;
    ProgressBar loading;

    FoursquareAPI foursquareAPI;
    String city = "Rome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_api);
        adapter = new PlacesAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.search_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        cityET = (EditText) findViewById(R.id.city_et);
        cityET.addTextChangedListener(this);
        searchEt = (EditText) findViewById(R.id.search_et);
        searchEt.addTextChangedListener(this);
        searchBtn = (Button) findViewById(R.id.search_btn);
        loading = (ProgressBar) findViewById(R.id.loading);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city = cityET.getText().toString();
                doSearch();
            }
        });
        registerForContextMenu(recyclerView);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        System.out.println(item.getItemId());

        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }

    public void doSearch() {
        String query = searchEt.getText().toString();
        new FoursquareApiTask().execute(query+CATEGORY);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        doSearch();
    }


    private class FoursquareApiTask extends AsyncTask<String, Void, ArrayList<Place>> {

        private static final String RESPONSE = "response";
        private static final String VENUES = "venues";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Place> doInBackground(String... strings) {

            ArrayList<Place> placesArraylist = new ArrayList<>();
            try {
                foursquareAPI = new FoursquareAPI(city);
                String url = foursquareAPI.getUrlString(strings[0]);

                JSONObject jsonResponse = foursquareAPI.getJSONObjectFromURL(url);
                JSONArray jsonPlaces = jsonResponse.getJSONObject(RESPONSE)
                                                        .getJSONArray(VENUES);


                for(int i = 0; i < jsonPlaces.length(); i++){
                    placesArraylist.add(new Place(jsonPlaces.getJSONObject(i)));

                }

                return placesArraylist;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return placesArraylist;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);
            adapter.setDataSet(places);
            loading.setVisibility(View.GONE);
        }
    }
}
