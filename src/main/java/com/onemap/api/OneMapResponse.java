package com.onemap.api;

import com.onemap.api.json.JSONArray;
import com.onemap.api.json.JSONObject;

public class OneMapResponse {

    /**
     * Maybe {@link JSONObject} or {@link JSONArray}
     */
    private Object response;

    public Object getResponse() {
        return response;
    }

    public OneMapResponse(Object response) {
        super();
        this.response = response;
    }

    public boolean isJSONArrayResponse() {
        return response == null ? false : response instanceof JSONArray ? true : false;
    }

    @Override
    public String toString() {
        return "Response [response=" + response + "]";
    }
}
