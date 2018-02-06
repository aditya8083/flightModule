package com.coviam.util;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
    @Autowired
    FlightConstants flightConstants;

    public String successResponse(JSONObject responseObj, String interactionType, String uniqueId) {
        return new JSONObject(new ResponseDeco(flightConstants.SUCCESS_CODE, flightConstants.SUCCESS, uniqueId, interactionType, responseObj)).toString();
    }

    public String errorResponse(int errorCode, String errorMessage, String uniqueId, String interactionType) {
        return new JSONObject(new ResponseDeco(errorCode,errorMessage, uniqueId, interactionType, new JSONObject())).toString();

    }
}
