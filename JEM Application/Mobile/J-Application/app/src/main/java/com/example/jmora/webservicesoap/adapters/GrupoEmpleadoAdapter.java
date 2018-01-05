package com.example.jmora.webservicesoap.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.Models.GrupoEmpleado;

import java.util.List;

/**
 * Created by JMora on 05/06/2017.
 */

public class GrupoEmpleadoAdapter extends ArrayAdapter {

    private List<GrupoEmpleado> listGroup;
    private Activity activity;
    private ListView listItems;

    static class ViewHolderItem {
        TextView txtDescription;
        LinearLayout llBackground;
    }

    public GrupoEmpleadoAdapter(Activity activity, List<GrupoEmpleado> items, ListView listItems) {
        super(activity,  R.layout.adapter_group_empleado, items);
        this.activity = activity;
        this.listGroup = items;
        this.listItems = listItems;
    }

    public GrupoEmpleadoAdapter(Activity activity, List<GrupoEmpleado> items) {
        super(activity,  R.layout.adapter_group_empleado, items);
        this.activity = activity;
        this.listGroup = items;
    }

    @Override
    public int getCount() {
        return listGroup.size();
    }

    @Override
    public Object getItem(int location) {
        return listGroup.get(location);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.adapter_group, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
            viewHolder.llBackground = (LinearLayout) convertView.findViewById(R.id.llBackground);
            // store the holder with the view.
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        // object item based on the position
        final GrupoEmpleado objectItem = listGroup.get(position);

        // assign values if the object is not null
        if (objectItem != null) {
            viewHolder.txtDescription.setText(objectItem.getGrupo().getNombre_grupo());
        }

        if(listItems!=null) {
            if (position == listItems.getCheckedItemPosition()) {
                viewHolder.txtDescription.setTypeface(Typeface.DEFAULT_BOLD);
                viewHolder.llBackground.setBackgroundColor(Color.parseColor("#97DF99"));
            } else {
                viewHolder.txtDescription.setTypeface(Typeface.DEFAULT);
                viewHolder.llBackground.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

        return convertView;

    }
}
