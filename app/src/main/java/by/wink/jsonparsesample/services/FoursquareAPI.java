package by.wink.jsonparsesample.services;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by amine on 01/03/17.
 */

public class FoursquareAPI {
    public FoursquareAPI(String city){
        this.city = city;
        BASE_URL = "https://api.foursquare.com/v2/venues/search?v=20161016&near="+city+"&query=";
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    private static String BASE_URL = "https://api.foursquare.com/v2/venues/search?v=20161016&near=rome&query=";
    private static String TOKEN = "&client_id=PRNHPU011KKTDFQUMCP3BGHF3K0532MFRTN5VJAVD4KTVVDM&client_secret=0NRKK422MKHOAAI31C524G4LFV41ADGMKGOIF2MONVW4X2GB";


    public JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String jsonString;
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        jsonString = sb.toString();
        Log.d("JSON: ", jsonString);

        return new JSONObject(jsonString);
    }

    public String getUrlString(String query) {
        Log.d("q= ", BASE_URL + TOKEN);
        return BASE_URL + query + TOKEN;
    }
}