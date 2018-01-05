package com.badassapp.generator;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class Main {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(3, "com.example.jmora.webservicesoap.Models");
        createTables(schema);
        createUploadTables(schema);

        //TODO change abs path to rltive
        new DaoGenerator().generateAll(schema, "C:\\Facu\\JEM-Application-master\\JEM Application\\Mobile\\J-Application\\app\\src\\main\\java");
    }

    public static void createTables(Schema schema)
    {

        //Piso table
        Entity piso = schema.addEntity("Piso");
        piso.addLongProperty("piso").primaryKey();
        piso.addStringProperty("oficina");

        //Sector table
        Entity sector = schema.addEntity("Sector");
        sector.addLongProperty("id_sector").primaryKey();
        sector.addStringProperty("nombre_sector");

        //relacion Sector - piso
        Property pisoId = sector.addLongProperty("pisoId").getProperty();
        sector.addToOne(piso,pisoId);

        ToMany sectorToPiso = piso.addToMany(sector,pisoId);
        sectorToPiso.setName("sector");

        //Empleado table
        Entity empleado = schema.addEntity("Empleado");
        empleado.addLongProperty("Dni").primaryKey();
        empleado.addStringProperty("Nombre");
        empleado.addStringProperty("Apellido");
        empleado.addDateProperty("Fecha_de_nacimiento");
        empleado.addStringProperty("Sexo");
        empleado.addStringProperty("Password");

        //relacion Empleado - Sector
        Property sectorId = empleado.addLongProperty("id_sector").getProperty();
        empleado.addToOne(sector,sectorId);

        ToMany sectorToEmpleado = sector.addToMany(empleado,sectorId);
        sectorToEmpleado.setName("empleado");


        //Grupo table
        Entity grupo = schema.addEntity("Grupo");
        grupo.addLongProperty("id_grupo").primaryKey();
        grupo.addStringProperty("nombre_grupo");
        grupo.addIntProperty("estado");

        //GrupoEmpleado table
        Entity grupoEmpleado = schema.addEntity("GrupoEmpleado");
        grupoEmpleado.addLongProperty("id_grupo_empleado").primaryKey();

        //Relación GrupoEmpleado - grupo
        Property grupoId = grupoEmpleado.addLongProperty("id_grupo").getProperty();
        grupoEmpleado.addToOne(grupo,grupoId);

        ToMany grupoToGrupoEmpleado = grupo.addToMany(grupoEmpleado,grupoId);
        grupoToGrupoEmpleado.setName("grupoEmpleado");

        //Relación GrupoEmpleado - empleado
        Property grupoEmpleadoId = grupoEmpleado.addLongProperty("dni_empleado").getProperty();
        grupoEmpleado.addToOne(empleado,grupoEmpleadoId);

        ToMany empleadoToGrupoEmpleado = empleado.addToMany(grupoEmpleado,grupoEmpleadoId);
        empleadoToGrupoEmpleado.setName("grupoEmpleado");


        //Home table
        Entity home = schema.addEntity("Home");
        home.addLongProperty("id_home").primaryKey();
        home.addIntProperty("estado");

        //DiasSemana table
        Entity diasSemana = schema.addEntity("DiasSemana");
        diasSemana.addLongProperty("id_dia").primaryKey();
        diasSemana.addStringProperty("dia");


        //Relación DiasSemana - grupo
        Property _grupoId = diasSemana.addLongProperty("id_grupo").getProperty();
        diasSemana.addToOne(grupo,_grupoId);

        ToMany grupoToDiasSemana = grupo.addToMany(diasSemana,_grupoId);
        grupoToDiasSemana.setName("diasSemana");

        //Relación DiasSemana - home
        Property homeId = diasSemana.addLongProperty("id_home").getProperty();
        diasSemana.addToOne(home,homeId);

        ToMany homeToDiasSemana = home.addToMany(diasSemana,homeId);
        homeToDiasSemana.setName("diasSemana");

    }

    public static void createUploadTables(Schema schema)
    {
        //empleadoQueue table
        Entity empleadoQueue = schema.addEntity("EmpleadoQueue");
        empleadoQueue.addLongProperty("dni_empleado").primaryKey();
        empleadoQueue.addStringProperty("nombre");
        empleadoQueue.addStringProperty("apellido");
        empleadoQueue.addDateProperty("fecha_de_nacimiento");
        empleadoQueue.addStringProperty("sexo");
        empleadoQueue.addLongProperty("id_sector");
        empleadoQueue.addStringProperty("password");

    }
}
