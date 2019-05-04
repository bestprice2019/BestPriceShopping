package com.example.bpsl;

import com.example.bpsl.Remote.IGoogleAPIService;
import com.example.bpsl.Remote.RetrofitClient;


public class Common {

    private static final String GOOGLE_API_URL ="https://maps.googleapis.com/";

    public static IGoogleAPIService getGoogleAPIService() {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }

}
