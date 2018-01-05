package com.example.jmora.webservicesoap.Models;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.example.jmora.webservicesoap.Models.Sector;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SECTOR".
*/
public class SectorDao extends AbstractDao<Sector, Long> {

    public static final String TABLENAME = "SECTOR";

    /**
     * Properties of entity Sector.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id_sector = new Property(0, Long.class, "id_sector", true, "ID_SECTOR");
        public final static Property Nombre_sector = new Property(1, String.class, "nombre_sector", false, "NOMBRE_SECTOR");
        public final static Property PisoId = new Property(2, Long.class, "pisoId", false, "PISO_ID");
    };

    private DaoSession daoSession;

    private Query<Sector> piso_SectorQuery;

    public SectorDao(DaoConfig config) {
        super(config);
    }
    
    public SectorDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SECTOR\" (" + //
                "\"ID_SECTOR\" INTEGER PRIMARY KEY ," + // 0: id_sector
                "\"NOMBRE_SECTOR\" TEXT," + // 1: nombre_sector
                "\"PISO_ID\" INTEGER);"); // 2: pisoId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SECTOR\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Sector entity) {
        stmt.clearBindings();
 
        Long id_sector = entity.getId_sector();
        if (id_sector != null) {
            stmt.bindLong(1, id_sector);
        }
 
        String nombre_sector = entity.getNombre_sector();
        if (nombre_sector != null) {
            stmt.bindString(2, nombre_sector);
        }
 
        Long pisoId = entity.getPisoId();
        if (pisoId != null) {
            stmt.bindLong(3, pisoId);
        }
    }

    @Override
    protected void attachEntity(Sector entity) {
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
    public Sector readEntity(Cursor cursor, int offset) {
        Sector entity = new Sector( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id_sector
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nombre_sector
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // pisoId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Sector entity, int offset) {
        entity.setId_sector(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNombre_sector(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPisoId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Sector entity, long rowId) {
        entity.setId_sector(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Sector entity) {
        if(entity != null) {
            return entity.getId_sector();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "sector" to-many relationship of Piso. */
    public List<Sector> _queryPiso_Sector(Long pisoId) {
        synchronized (this) {
            if (piso_SectorQuery == null) {
                QueryBuilder<Sector> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PisoId.eq(null));
                piso_SectorQuery = queryBuilder.build();
            }
        }
        Query<Sector> query = piso_SectorQuery.forCurrentThread();
        query.setParameter(0, pisoId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPisoDao().getAllColumns());
            builder.append(" FROM SECTOR T");
            builder.append(" LEFT JOIN PISO T0 ON T.\"PISO_ID\"=T0.\"PISO\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Sector loadCurrentDeep(Cursor cursor, boolean lock) {
        Sector entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Piso piso = loadCurrentOther(daoSession.getPisoDao(), cursor, offset);
        entity.setPiso(piso);

        return entity;    
    }

    public Sector loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Sector> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Sector> list = new ArrayList<Sector>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Sector> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Sector> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
