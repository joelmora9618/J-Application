using BusinessEntities.cs.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace BusinessEntities.cs.Mapping
{
    class EmpleadoMap : EntityTypeConfiguration<empleado>
    {
        internal EmpleadoMap()
            : base()
        {
            HasKey(e => e.dni_empleado);

            Property(e => e.dni_empleado).HasColumnName("dni_empleado").IsRequired();   // Podemos mapear cualquier propiedad a campos de la base de datos aunque no tengan el mismo nombre
            Property(e => e.nombre).HasColumnName("nombre");
            Property(e => e.apellido).HasColumnName("apellido");
            Property(e => e.fecha_de_nacimiento).HasColumnName("fecha_de_nacimiento");
            Property(e => e.sexo).HasColumnName("sexo");
            Property(e => e.id_sector).HasColumnName("id_sector");
            Property(e => e.password).HasColumnName("password");
            Property(e => e.piso).HasColumnName("piso");

            ToTable("empleado");
        }
    }
}
