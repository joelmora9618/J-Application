package com.example.jmora.webservicesoap.ui.activities;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.ui.fragments.GroupFragment;

public class ViewGroupActivity extends AppCompatActivity {

    private Spannable text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        initControls();
    }

    private void initControls()
    {
        String nameGroup = getIntent().getExtras().getString(GroupFragment.NAME_GROUP);
        changeTitleActionBar(nameGroup);
    }

    public void changeTitleActionBar(CharSequence sequence){
        if (sequence != null) {
            text = new SpannableString(sequence);
        }else{
            text = new SpannableString(getResources().getString(R.string.title_activity_navigation_drawer));
        }
        text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorWhite)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        text.setSpan(boldSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        this.getSupportActionBar().setTitle(text);
    }

}
