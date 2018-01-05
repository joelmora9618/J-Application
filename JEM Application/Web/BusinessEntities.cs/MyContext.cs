using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BusinessEntities.cs.Mapping;
using BusinessEntities.cs.Models;

namespace BusinessEntities.cs
{
   public class MyContext : DbContext, IDisposable
    {
        // Tablas BD
        public DbSet Empleado { get; set; }
 
        ///
/// Constructor de clase
        ///
        ///Cadena de conexión
        public MyContext(string connString)
            : base(connString)
        {
            // Se establece el inicializador de la base de datos a null para usar Code First con una base de datos ya existente
            Database.SetInitializer<Jem96DbEntities>(new CreateDatabaseIfNotExists<Jem96DbEntities>()); 
        }
 
        ///
/// Sobreescritura del evento OnModelCreating para añadir los mapeos manuales de las clases POCO usando Fluent API
        ///
        ///
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new EmpleadoMap());
 
            base.OnModelCreating(modelBuilder);
        }
    }
}
