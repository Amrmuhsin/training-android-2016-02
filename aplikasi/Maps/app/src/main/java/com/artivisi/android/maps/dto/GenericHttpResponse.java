package com.artivisi.android.maps.dto;

import java.util.List;

/**
 * Created by jimmy on 15/04/16.
 */
public class GenericHttpResponse {

    private String rc;
    private String message;
    private List<Location> data;

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Location> getData() {
        return data;
    }

    public void setData(List<Location> data) {
        this.data = data;
    }
}
