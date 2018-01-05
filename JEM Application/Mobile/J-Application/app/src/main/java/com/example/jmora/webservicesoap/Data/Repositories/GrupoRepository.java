package com.example.jmora.webservicesoap.Data.Repositories;

import android.content.Context;

import com.example.jmora.webservicesoap.Data.DataBaseManager;
import com.example.jmora.webservicesoap.Models.DaoSession;
import com.example.jmora.webservicesoap.Models.Grupo;
import com.example.jmora.webservicesoap.Models.GrupoDao;

import java.util.List;

/**
 * Created by JMora on 05/01/2018.
 */

public class GrupoRepository {

    public static void insertGrupo(Grupo grupo, Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        GrupoDao grupoDao = daoSession.getGrupoDao();
        grupoDao.insertOrReplace(grupo);
    }

    public static void deleteGrupo(Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        GrupoDao grupoDao = daoSession.getGrupoDao();
        grupoDao.deleteAll();
    }

    public static List<Grupo> getGrupo(Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        GrupoDao grupoDao = daoSession.getGrupoDao();
        List<Grupo> grupoList = grupoDao.loadAll();
        return grupoList;
    }

    public static Grupo getGrupo(long key, Context context) {
        DaoSession daoSession = DataBaseManager.getInstance(context).getDaoSession();
        GrupoDao grupoDao = daoSession.getGrupoDao();
        Grupo cap = grupoDao.load(key);
        return cap;
    }
}
