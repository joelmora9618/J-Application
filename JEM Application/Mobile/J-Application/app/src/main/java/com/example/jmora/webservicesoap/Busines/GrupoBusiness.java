package com.example.jmora.webservicesoap.Busines;

import android.content.Context;

import com.example.jmora.webservicesoap.Data.Repositories.GrupoRepository;
import com.example.jmora.webservicesoap.Models.Grupo;

import java.util.List;

/**
 * Created by JMora on 05/01/2018.
 */

public class GrupoBusiness {

    public void insertGrupo(Grupo grupo, Context context)
    {
        GrupoRepository.insertGrupo(grupo,context);
    }
    public List<Grupo> grupoList(Context context)
    {
        List<Grupo>grupoList = GrupoRepository.getGrupo(context);
        return grupoList;
    }
}
