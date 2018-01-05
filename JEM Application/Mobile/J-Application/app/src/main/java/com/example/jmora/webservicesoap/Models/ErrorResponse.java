package com.example.jmora.webservicesoap.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JMora on 08/08/2017.
 */

public class ErrorResponse {
    @SerializedName("messages")
    //public ErrorResponseItem[] errorMessages;
    public String[] errorMessages;

    @SerializedName("error_description")
    public String azureErrorMessage;


    @SerializedName("errorType")
    public int errorType;

    public String getMessage() {
        String mensaje = "";
        for (int i = 0; i < errorMessages.length; i++) {
            mensaje += errorMessages[i];
            if (i + 1 != errorMessages.length)
                mensaje += '\n';
        }

        return mensaje;
    }
}
