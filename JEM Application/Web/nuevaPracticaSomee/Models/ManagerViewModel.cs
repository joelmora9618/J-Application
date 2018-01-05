using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Models
{
    public class ManagerViewModel
    {
        //static string conectionString = ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;
        static string conectionString = @"Data Source=Jem96Db.mssql.somee.com;Initial Catalog=Jem96Db;Persist Security Info=True;User ID=WKS831L_SQLLogin_1;Password=m1kz4gyxuw;MultipleActiveResultSets=True;Application Name=EntityFramework";

        public static SqlConnection OpenConection()
        {
            SqlConnection conection = new SqlConnection(conectionString);
            conection.Open();
            return conection;
        }

        public static SqlConnection CloseConection()
        {
            SqlConnection conection = new SqlConnection(conectionString);
            conection.Close();
            return conection;
        }
    }
}