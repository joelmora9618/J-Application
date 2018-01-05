package com.example.jmora.webservicesoap.validator.validate;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.validator.AbstractValidate;

/**
 * Created by JMora on 22/05/2017.
 */

public class ConfirmValidate extends AbstractValidate {

    private static final int DEFAULT_MENNSAGE_ERROR = R.string.validator_confirm;
    private EditText mFirstField;
    private EditText mSecondField;
    private TextView mSourceView;
    private Context mContext;
    private final int mErrorMessage;

    public ConfirmValidate (EditText field1, EditText field2){
        mFirstField = field1;
        mSecondField = field2;
        mSourceView = mSecondField;
        mContext = mSourceView.getContext();
        mErrorMessage = DEFAULT_MENNSAGE_ERROR;
    }

    public ConfirmValidate(EditText field1, EditText field2,int errorMessageRes) {
        mFirstField = field1;
        mSecondField = field2;
        mSourceView = mSecondField;
        mContext = mSourceView.getContext();
        mErrorMessage = errorMessageRes;
    }

    @Override
    public boolean isValid() {
        final String firstFieldtx = mFirstField.getText().toString();
        final String secondFieldtx = mSecondField.getText().toString();
        if (firstFieldtx.equals(secondFieldtx)) {
            mSourceView.setError(null);
            return true;
        }else {
            mSourceView.setError(mContext.getString(mErrorMessage));
        }
        return false;
    }

    private TextView getSource(){
        return mSourceView;
    }

    private boolean isEmpty(String text){
        return !TextUtils.isEmpty(text);
    }
}
