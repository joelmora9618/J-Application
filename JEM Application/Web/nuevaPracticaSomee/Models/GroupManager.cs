using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;

namespace nuevaPracticaSomee.Models
{
    public class GroupManager
    {
        String cadenaConexion = @"Data Source=Jem96Db.mssql.somee.com;Initial Catalog=Jem96Db;Persist Security Info=True;User ID=WKS831L_SQLLogin_1;Password=m1kz4gyxuw;MultipleActiveResultSets=True;Application Name=EntityFramework";

        public List<grupo> ObtenerGrupos()
        {
            List<grupo> lista = new List<grupo>();
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "SELECT id_grupo, nombre_grupo, estado FROM grupo";
            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader reader =
                cmd.ExecuteReader(System.Data.CommandBehavior.CloseConnection);
            while (reader.Read())
            {
                grupo gru = new grupo();
                gru = new grupo();
                gru.id_grupo = reader.GetInt32(0);
                gru.nombre_grupo = reader.GetString(1);
                gru.estado = reader.GetInt32(2);
                lista.Add(gru);
            }
            reader.Close();
            return lista;
        }

        public bool InsertarGrupo(grupo gru)
        {
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "INSERT INTO grupo (nombre_grupo, estado) VALUES (@nombre_grupo, @estado)";
            SqlCommand cmd = new SqlCommand(sql, con);
            cmd.Parameters.Add("@nombre_grupo", System.Data.SqlDbType.NVarChar).Value = gru.nombre_grupo;
            cmd.Parameters.Add("@estado", System.Data.SqlDbType.Int).Value = gru.estado;

            int res = cmd.ExecuteNonQuery();
            con.Close();
            return (res == 1);
        }
    }
}