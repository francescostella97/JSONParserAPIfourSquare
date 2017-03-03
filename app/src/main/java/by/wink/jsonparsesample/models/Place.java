package by.wink.jsonparsesample.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amine on 01/03/17.
 */

public class Place {

    private int id;
    private String name;
    private String address;
    private Double latitiude;
    private Double longitude;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    private Contact contact;
    private String city;

    // KEYS
    private static final String NAME_KEY = "name";
    private static final String LOCATION_KEY = "location";
    private static final String ADDRESS_KEY = "address";
    private static final String LAT_KEY = "lat";
    private static final String LONG_KEY = "lng";
    private static final String CONTACT_KEY = "contact";
    private static final String CITY_KEY = "city";

    public Place(JSONObject jsonPlace) {

        try {
            name = jsonPlace.getString(NAME_KEY);
            address = jsonPlace.getJSONObject(LOCATION_KEY).optString(ADDRESS_KEY,"");
            latitiude = jsonPlace.getJSONObject(LOCATION_KEY).getDouble(LAT_KEY);
            longitude = jsonPlace.getJSONObject(LOCATION_KEY).getDouble(LONG_KEY);
            city = jsonPlace.getJSONObject(LOCATION_KEY).optString(CITY_KEY,"");
            contact = new Contact(jsonPlace.getJSONObject(CONTACT_KEY));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(Double latitiude) {
        this.latitiude = latitiude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
