package com.assignment.makeup_app.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ColorList implements Serializable {

    private String hexValue;
    private String colourName;

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }


    public static ArrayList<ColorList> fromJsonu(JSONArray jsonArray) {
        JSONObject Jsonobject;
        ArrayList<ColorList> colorLists = new ArrayList<ColorList>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Jsonobject = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            ColorList list = ColorList.fromJsonn(Jsonobject);
            if (list != null) {
                colorLists.add(list);
            }
        }

        return colorLists;
    }


    // Decodes business json into business model object
    public static ColorList fromJsonn(JSONObject jsonObject) {
        ColorList b = new ColorList();
        // Deserialize json into object fields
        b.hexValue = jsonObject.optString("hex_value");
        b.colourName = jsonObject.optString("colour_name");
        // Return new object
        return b;
    }

}
