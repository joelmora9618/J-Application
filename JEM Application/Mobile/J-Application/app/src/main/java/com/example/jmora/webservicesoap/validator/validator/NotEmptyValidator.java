package com.example.jmora.webservicesoap.validator.validator;

import android.content.Context;
import android.text.TextUtils;

import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.validator.AbstractValidator;

/**
 * Created by JMora on 22/05/2017.
 */

public class NotEmptyValidator extends AbstractValidator{
    public static final int DEFAULT_ERROR_MESSAGE_RESOURCE = R.string.validator_empty;

    public NotEmptyValidator(Context c){
        super(c,DEFAULT_ERROR_MESSAGE_RESOURCE);
    }

    public NotEmptyValidator(Context c, int errorMessage) {
        super(c, errorMessage);
    }


    @Override
    public boolean isValid(String text){
        return !TextUtils.isEmpty(text);
    }
}
