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

import com.example.jmora.webservicesoap.Models.Empleado;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.ui.activities.EmpleadosActivity;
import com.example.jmora.webservicesoap.ui.fragments.EmpleadosFragment;

import java.util.List;

/**
 * Created by JMora on 22/08/2017.
 */

public class EmpleadoAdapter extends ArrayAdapter {
    private List<Empleado> listEmpleado;
    private Activity activity;
    private ListView listItems;

    static class ViewHolderItem {
        TextView tvNombre,
                 tvApellido,
                 tvEdad,
                 tvSector;
        LinearLayout llBackground;
    }

    public EmpleadoAdapter(Activity activity, List<Empleado> items, ListView listItems) {
        super(activity, R.layout.adapter_empleados, items);
        this.activity = activity;
        this.listEmpleado = items;
        this.listItems = listItems;
    }

    public EmpleadoAdapter(Activity activity, List<Empleado> items) {
        super(activity, R.layout.adapter_empleados, items);
        this.activity = activity;
        this.listEmpleado = items;
    }

    @Override
    public int getCount() {
        return listEmpleado.size();
    }

    @Override
    public Object getItem(int location) {
        return listEmpleado.get(location);
    }
/*
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.adapter_empleados_dropdown, parent, false);

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
        final Empleado objectItem = listEmpleado.get(position);

        // assign values if the object is not null
        if (objectItem != null) {
            viewHolder.txtDescription.setText(objectItem.getNombre());
        }

        return convertView;
    }
*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.adapter_empleados, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
            viewHolder.tvApellido = (TextView) convertView.findViewById(R.id.tvApellido);
            viewHolder.tvEdad = (TextView) convertView.findViewById(R.id.tvEdad);
            viewHolder.tvSector = (TextView) convertView.findViewById(R.id.tvDivicion);
        //    viewHolder.llBackground = (LinearLayout) convertView.findViewById(R.id.llBackground);
            // store the holder with the view.
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        // object item based on the position
        final Empleado objectItem = listEmpleado.get(position);

        // assign values if the object is not null
        if (objectItem != null) {
            viewHolder.tvNombre.setText(objectItem.getNombre().toString());
            viewHolder.tvApellido.setText(objectItem.getApellido().toString());
            viewHolder.tvEdad.setText(objectItem.getFecha_de_nacimiento().toString());
          //  viewHolder.tvSector.setText(objectItem.getSector().toString());
        }

        if(listItems!=null) {
            if (position == listItems.getCheckedItemPosition()) {
                viewHolder.tvNombre.setTypeface(Typeface.DEFAULT_BOLD);
             //   viewHolder.llBackground.setBackgroundColor(Color.parseColor("#97DF99"));
            } else {
                viewHolder.tvNombre.setTypeface(Typeface.DEFAULT);
             //   viewHolder.llBackground.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

        return convertView;

    }
}
