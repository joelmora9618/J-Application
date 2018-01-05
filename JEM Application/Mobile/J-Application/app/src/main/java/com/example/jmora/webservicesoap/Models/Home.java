package com.example.jmora.webservicesoap.Models;

import java.util.List;
import com.example.jmora.webservicesoap.Models.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "HOME".
 */
public class Home {

    private Long id_home;
    private Integer estado;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient HomeDao myDao;

    private List<DiasSemana> diasSemana;

    public Home() {
    }

    public Home(Long id_home) {
        this.id_home = id_home;
    }

    public Home(Long id_home, Integer estado) {
        this.id_home = id_home;
        this.estado = estado;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHomeDao() : null;
    }

    public Long getId_home() {
        return id_home;
    }

    public void setId_home(Long id_home) {
        this.id_home = id_home;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<DiasSemana> getDiasSemana() {
        if (diasSemana == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DiasSemanaDao targetDao = daoSession.getDiasSemanaDao();
            List<DiasSemana> diasSemanaNew = targetDao._queryHome_DiasSemana(id_home);
            synchronized (this) {
                if(diasSemana == null) {
                    diasSemana = diasSemanaNew;
                }
            }
        }
        return diasSemana;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetDiasSemana() {
        diasSemana = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
