package com.example.jmora.webservicesoap.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.ui.activities.NavigationDrawerActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by JMora on 11/10/2017.
 */

public class ExampleAppWidgetProvider extends AppWidgetProvider {

    private int widgetId = 0;
    public static final String ACTION_AUTO_UPDATE = "AUTO_UPDATE";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //Iteramos la lista de widgets en ejecución
        for (int i = 0; i < appWidgetIds.length; i++)
        {
            //ID del widget actual
            int widgetId = appWidgetIds[i];

            //Actualizamos el widget actual
            actualizarWidget(context, appWidgetManager, widgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("com.example.jmora.webservicesoap.ACTUALIZAR_WIDGET")) {

            //Obtenemos el ID del widget a actualizar
            int widgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            //Obtenemos el widget manager de nuestro contexto
            AppWidgetManager widgetManager =
                    AppWidgetManager.getInstance(context);

            //Actualizamos el widget
            if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                actualizarWidget(context, widgetManager, widgetId);
            }
        }

        if(intent.getAction().equals(ACTION_AUTO_UPDATE)) {
            updateWidgetInstance(context);
        }
    }

    @Override
    public void onEnabled(Context context)
    {
        updateWidgetInstance(context);
        // start alarm
        AppWidgetAlarm appWidgetAlarm = new AppWidgetAlarm(context.getApplicationContext());
        appWidgetAlarm.startAlarm();
    }

    @Override
    public void onDisabled(Context context)
    {
        // TODO: alarm should be stopped only if all widgets has been disabled

        // stop alarm
        AppWidgetAlarm appWidgetAlarm = new AppWidgetAlarm(context.getApplicationContext());
        appWidgetAlarm.stopAlarm();
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        super.onDeleted(context, appWidgetIds);
        //Accedemos a las preferencias de la aplicación
        SharedPreferences prefs =
                context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        //Eliminamos las preferencias de los widgets borrados
        for(int i=0; i<appWidgetIds.length; i++)
        {
            //ID del widget actual
            int widgetId = appWidgetIds[i];

            editor.remove("msg_" + widgetId);
        }

        //Aceptamos los cambios
        editor.commit();

    }

    public static void updateWidgetInstance(Context context)
    {
        //Obtenemos el ID de los widgets instanciados
        int appWidgetIds[] = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetIds(
                new ComponentName(context.getApplicationContext(), ExampleAppWidgetProvider.class));

        //Obtenemos el widget manager de nuestro contexto
        AppWidgetManager widgetManager =
                AppWidgetManager.getInstance(context);

        //Actualizamos el widget
        for (int i = 0; i < appWidgetIds.length; i++)
        {
            //ID del widget actual
            int widgetId = appWidgetIds[i];

            //Actualizamos el widget actual
            actualizarWidget(context, widgetManager, widgetId);
        }
    }

    public static void actualizarWidget(Context context,
                                        AppWidgetManager appWidgetManager, int widgetId)
    {
        //Recuperamos el mensaje personalizado para el widget actual
        SharedPreferences prefs =
                context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
        String mensaje = prefs.getString("msg_" + widgetId, "Hora actual:");

        //Obtenemos la lista de controles del widget actual
        RemoteViews controles =
                new RemoteViews(context.getPackageName(), R.layout.example_appwidget);

        //Asociamos los 'eventos' al widget
        Intent intent = new Intent("com.example.jmora.webservicesoap.ACTUALIZAR_WIDGET");
        intent.putExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, widgetId,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

        controles.setOnClickPendingIntent(R.id.BtnActualizar, pendingIntent);

        Intent intent2 = new Intent(context, NavigationDrawerActivity.class);
        PendingIntent pendingIntent2 =
                PendingIntent.getActivity(context, widgetId,
                        intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        controles.setOnClickPendingIntent(R.id.FrmWidget, pendingIntent2);

        //Actualizamos el mensaje en el control del widget
        controles.setTextViewText(R.id.LblMensaje, mensaje);

        //Obtenemos la hora actual
        Calendar calendario = new GregorianCalendar();
        String hora = calendario.getTime().toLocaleString();

        //Actualizamos la hora en el control del widget
        controles.setTextViewText(R.id.LblHora, hora);

        //Notificamos al manager de la actualización del widget actual
        appWidgetManager.notifyAppWidgetViewDataChanged(widgetId,R.id.LblHora);
        appWidgetManager.updateAppWidget(widgetId, controles);
    }

}

