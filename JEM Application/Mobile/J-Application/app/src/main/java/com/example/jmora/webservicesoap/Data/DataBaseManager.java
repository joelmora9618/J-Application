package com.example.jmora.webservicesoap.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jmora.webservicesoap.Models.DaoMaster;
import com.example.jmora.webservicesoap.Models.DaoSession;

/**
 * Created by JMora on 08/08/2017.
 */

public class DataBaseManager {
    private static final String DATABASE_NAME = "assetsfinder-db";
    private DaoSession daoSession;
    private static DataBaseManager instance = null;
    protected DataBaseManager() {

    }
    public static DataBaseManager getInstance(Context context) {
        if(instance == null) {
            instance = new DataBaseManager();
            instance.setupDatabase(context);
        }
        return instance;
    }

    private void setupDatabase(Context c) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(c, DATABASE_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession()
    {
        return daoSession;
    }
}
