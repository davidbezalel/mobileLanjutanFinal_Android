package com.example.davidbezalellaoli.mobile.api;

import com.example.davidbezalellaoli.mobile.cores.models.Response;

/**
 * Created by davidbezalellaoli on 11/8/17.
 */

public interface ResponseCallback {
    public void success(Response response);
    public void fail(String message);
    public void internalServerError(int message);
}
