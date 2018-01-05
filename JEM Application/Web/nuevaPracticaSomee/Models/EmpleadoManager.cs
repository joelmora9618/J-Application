using nuevaPracticaSomee.ViewModel;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Models
{
    public class EmpleadoManager
    {

        public List<EmpleadoViewModel> Get()
        {
            SqlConnection conection = null;
            SqlTransaction trans = null;
            SqlDataReader dr = null;
            DataTable datosPersonales = new DataTable();
            List<EmpleadoViewModel> listaCliente = new List<EmpleadoViewModel>();

            try
            {
                conection = ManagerViewModel.OpenConection();
                trans = conection.BeginTransaction();

                string sqlGet = "SELECT * FROM empleado";
                SqlCommand commandConsulta = new SqlCommand(sqlGet, conection, trans);
                dr = commandConsulta.ExecuteReader();

                datosPersonales.Load(dr);
                // dr.Close();
                trans.Commit();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error Message " + e.Message + "\n" + e.StackTrace);
                if (trans != null)
                {
                    trans.Rollback();
                }
            }
            finally
            {
                if (dr != null)
                {
                    dr.Close();
                }
                if (conection != null)
                {
                    conection.Close();
                }
            }

            foreach (DataRow row in datosPersonales.Rows)
            {
                EmpleadoViewModel cliente = new EmpleadoViewModel();
                cliente.Dni = (int)row["dni_empleado"];
                cliente.Nombre = (string)row["nombre"];
                cliente.Apellido = (string)row["apellido"];
                cliente.Fecha_de_nacimiento = (DateTime)row["fecha_de_nacimiento"];
                cliente.Sexo = (string)row["sexo"];
                cliente.Password = (string)row["password"];

                listaCliente.Add(cliente);
            }

            return listaCliente;
        }

        /*
        public List<empleado> ObtenerEmpleado()
        {
            List<empleado> lista = new List<empleado>();
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "SELECT dni_empleado, nombre, apellido, fecha_de_nacimiento, sexo, password FROM empleado";
            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader reader =
                cmd.ExecuteReader(System.Data.CommandBehavior.CloseConnection);
            while (reader.Read())
            {
                empleado emp = new empleado();
                emp = new empleado();
                emp.dni_empleado = reader.GetInt32(0);
                emp.nombre = reader.GetString(1);
                emp.apellido = reader.GetString(2);
                emp.fecha_de_nacimiento = reader.GetDateTime(3);
                emp.sexo = reader.GetString(4);
                emp.password = reader.GetString(5);
                lista.Add(emp);
            }
            reader.Close();
            return lista;
        }*/


        public EmpleadoViewModel Get(int dni)
        {
            SqlConnection conection = null;

            SqlDataReader dr = null;
            DataTable datosPersonales = new DataTable();
            EmpleadoViewModel cliente = new EmpleadoViewModel();

            try
            {
                conection = ManagerViewModel.OpenConection();
                string sqlGet = "SELECT * FROM empleado WHERE dni_empleado = " + dni;
                SqlCommand commandConsulta = new SqlCommand(sqlGet, conection);
                dr = commandConsulta.ExecuteReader();

                datosPersonales.Load(dr);
                // dr.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine("Error Message " + e.Message + "\n" + e.StackTrace);

            }
            finally
            {
                if (dr != null)
                {
                    dr.Close();
                }
                if (conection != null)
                {
                    conection.Close();
                }
            }

            foreach (DataRow row in datosPersonales.Rows)
            {
                cliente.Dni = (int)row["dni_empleado"];
                cliente.Nombre = (string)row["nombre"];
                cliente.Apellido = (string)row["apellido"];
                cliente.Fecha_de_nacimiento = (DateTime)row["fecha_de_nacimiento"];
                cliente.Sexo = (string)row["sexo"];
                cliente.Password = (string)row["password"];

            }

            return cliente;
        }

        /*
        public empleado ObtenerEmpleado(int dni)
        {
            empleado emp = null;
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "SELECT dni_empleado, nombre, apellido, fecha_de_nacimiento, sexo, password FROM empleado WHERE dni_empleado = @dni_empleado";
            SqlCommand cmd = new SqlCommand(sql, con);
            cmd.Parameters.Add("@dni_empleado", System.Data.SqlDbType.NVarChar).Value = dni;
            SqlDataReader reader =
                cmd.ExecuteReader(System.Data.CommandBehavior.CloseConnection);
            if (reader.Read())
            {
                emp = new empleado();
                emp.dni_empleado = dni;
                emp.dni_empleado = reader.GetInt32(0);
                emp.nombre = reader.GetString(1);
                emp.apellido = reader.GetString(2);
                emp.fecha_de_nacimiento = reader.GetDateTime(3);
                emp.sexo = reader.GetString(4);
                emp.password = reader.GetString(5);
            }
            reader.Close();
            return emp;
        }*/


        public int Post(EmpleadoViewModel c)
        {
            SqlConnection conection = null;
            SqlTransaction trans = null;
            int rowCount = 0;
            try
            {
                conection = ManagerViewModel.OpenConection();
                trans = conection.BeginTransaction();
                string sql = "INSERT INTO empleado (dni_empleado,nombre,apellido,fecha_de_nacimiento,sexo,password) VALUES(@dni_empleado,@nombre,@apellido,@fecha_de_nacimiento,@sexo,@password)";
                SqlCommand command = new SqlCommand(sql, conection, trans);

                command.Parameters.AddWithValue("@dni_empleado", c.Dni);
                command.Parameters.AddWithValue("@nombre", c.Nombre);
                command.Parameters.AddWithValue("@apellido", c.Apellido);
              
                command.Parameters.AddWithValue("@fecha_de_nacimiento", c.Fecha_de_nacimiento);
                command.Parameters.AddWithValue("@sexo", c.Sexo);
                command.Parameters.AddWithValue("@password", c.Password);


                rowCount = command.ExecuteNonQuery();
                trans.Commit();

            }
            catch (Exception e)
            {
                Console.WriteLine("Error Message " + e.Message + "\n" + e.StackTrace);
                if (trans != null)
                {
                    trans.Rollback();
                }
                throw e;
            }
            finally
            {
                if (conection != null)
                {
                    conection.Close();
                }
            }
            return rowCount;
        }

        /*
        public bool InsertarEmpleado(empleado emp)
        {
            SqlConnection con = new SqlConnection(cadenaConexion);
            con.Open();
            string sql = "INSERT INTO empleado (dni_empleado, nombre, apellido, fecha_de_nacimiento, sexo, password) VALUES (@dni_empleado, @nombre, @apellido, @fecha_de_nacimiento, @sexo, @password)";
            SqlCommand cmd = new SqlCommand(sql, con);
            cmd.Parameters.Add("@dni_empleado", System.Data.SqlDbType.Int).Value = emp.dni_empleado;
            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.NVarChar).Value = emp.nombre;
            cmd.Parameters.Add("@apellido", System.Data.SqlDbType.NVarChar).Value = emp.apellido;
            cmd.Parameters.Add("@fecha_de_nacimiento", System.Data.SqlDbType.DateTime).Value = emp.fecha_de_nacimiento;
            cmd.Parameters.Add("@sexo", System.Data.SqlDbType.NVarChar).Value = emp.sexo;
            cmd.Parameters.Add("@password", System.Data.SqlDbType.NVarChar).Value = emp.password;

            int res = cmd.ExecuteNonQuery();
            con.Close();
            return (res == 1);
        }*/
    }
}