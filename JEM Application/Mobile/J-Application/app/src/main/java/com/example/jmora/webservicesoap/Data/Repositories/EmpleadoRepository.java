package com.example.jmora.webservicesoap.Data.Repositories;

import android.content.Context;

import com.example.jmora.webservicesoap.Data.DataBaseManager;
import com.example.jmora.webservicesoap.Models.DaoSession;
import com.example.jmora.webservicesoap.Models.Empleado;
import com.example.jmora.webservicesoap.Models.EmpleadoDao;

import java.util.List;

/**
 * Created by JMora on 22/08/2017.
 */

public class EmpleadoRepository {

    public static void insertEmpleado(Empleado empleado, Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        EmpleadoDao empleadoDao = daoSession.getEmpleadoDao();
        empleadoDao.insertOrReplace(empleado);
    }

    public static void deleteEmpleado(Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        EmpleadoDao empleadoDao = daoSession.getEmpleadoDao();
        empleadoDao.deleteAll();
    }

    public static List<Empleado> getEmpleado(Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        EmpleadoDao empleadoDao = daoSession.getEmpleadoDao();
        List<Empleado> empleadoList = empleadoDao.loadAll();
        return empleadoList;
    }

    public static Empleado getEmpleado(long key, Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        EmpleadoDao empleadoDao = daoSession.getEmpleadoDao();
        Empleado cap = empleadoDao.load(key);
        return cap;
    }
}
