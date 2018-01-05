package com.example.jmora.webservicesoap.Models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.jmora.webservicesoap.Models.Piso;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PISO".
*/
public class PisoDao extends AbstractDao<Piso, Long> {

    public static final String TABLENAME = "PISO";

    /**
     * Properties of entity Piso.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Piso = new Property(0, Long.class, "piso", true, "PISO");
        public final static Property Oficina = new Property(1, String.class, "oficina", false, "OFICINA");
    };

    private DaoSession daoSession;


    public PisoDao(DaoConfig config) {
        super(config);
    }
    
    public PisoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PISO\" (" + //
                "\"PISO\" INTEGER PRIMARY KEY ," + // 0: piso
                "\"OFICINA\" TEXT);"); // 1: oficina
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PISO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Piso entity) {
        stmt.clearBindings();
 
        Long piso = entity.getPiso();
        if (piso != null) {
            stmt.bindLong(1, piso);
        }
 
        String oficina = entity.getOficina();
        if (oficina != null) {
            stmt.bindString(2, oficina);
        }
    }

    @Override
    protected void attachEntity(Piso entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Piso readEntity(Cursor cursor, int offset) {
        Piso entity = new Piso( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // piso
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // oficina
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Piso entity, int offset) {
        entity.setPiso(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOficina(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Piso entity, long rowId) {
        entity.setPiso(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Piso entity) {
        if(entity != null) {
            return entity.getPiso();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}