package by.wink.jsonparsesample.models;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by ${Francesco} on 02/03/2017.
 */

public class Contact {

    private String phone;

    private static final String PHONE_KEY = "phone";

    public Contact(JSONObject jsonContact){
        phone = jsonContact.optString(PHONE_KEY,"");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
