using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Models
{
    public class GrupoEmpleadoManager
    {
        String cadenaConexion = @"Data Source=Jem96Db.mssql.somee.com;Initial Catalog=Jem96Db;Persist Security Info=True;User ID=WKS831L_SQLLogin_1;Password=m1kz4gyxuw;MultipleActiveResultSets=True;Application Name=EntityFramework";

        public List<grupo_empleado> ObtenerGrupo_empleado()
        {
            List<grupo_empleado> lista = new List<grupo_empleado>();
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "select ge.id_grupo_empleado, ge.id_grupo, ge.dni_empleado, g.nombre_grupo from grupo_empleado as ge"+
                         "join grupo as g on g.id_grupo = ge.id_grupo";
            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader reader =
                cmd.ExecuteReader(System.Data.CommandBehavior.CloseConnection);
            while (reader.Read())
            {
                grupo_empleado gru = new grupo_empleado();
                gru = new grupo_empleado();
                gru.id_grupo_empleado = reader.GetInt32(0);
                gru.id_grupo = reader.GetInt32(1);
                gru.dni_empleado = reader.GetInt32(2);
                gru.grupo.nombre_grupo = reader.GetString(3);
                lista.Add(gru);
            }
            reader.Close();
            return lista;
        }

        public List<grupo_empleado> ObtenerGrupo_empleado(int dni)
        {
            List<grupo_empleado> lista = new List<grupo_empleado>();
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "SELECT id_grupo_empleado, id_grupo, dni_empleado FROM grupo_empleado WHERE dni_empleado = @dni_empleado";
            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader reader =
                cmd.ExecuteReader(System.Data.CommandBehavior.CloseConnection);
            while (reader.Read())
            {
                grupo_empleado gru = new grupo_empleado();
                gru = new grupo_empleado();
                gru.id_grupo_empleado = reader.GetInt32(0);
                gru.id_grupo = reader.GetInt32(1);
                gru.dni_empleado = reader.GetInt32(2);
                lista.Add(gru);
            }
            reader.Close();
            return lista;
        }

        public bool InsertarGrupo_empleado(grupo_empleado gru)
        {
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "INSERT INTO grupo_empleado (id_grupo, dni_empleado) VALUES (@id_grupo, @dni_empleado)";
            SqlCommand cmd = new SqlCommand(sql, con);
            cmd.Parameters.Add("@id_grupo", System.Data.SqlDbType.Int).Value = gru.id_grupo;
            cmd.Parameters.Add("@dni_empleado", System.Data.SqlDbType.Int).Value = gru.dni_empleado;

            int res = cmd.ExecuteNonQuery();
            con.Close();
            return (res == 1);
        }
    }
}