﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código se generó a partir de una plantilla.
//
//     Los cambios manuales en este archivo pueden causar un comportamiento inesperado de la aplicación.
//     Los cambios manuales en este archivo se sobrescribirán si se regenera el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace nuevaPracticaSomee.Models
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class Jem96DbEntities : DbContext
    {
        public Jem96DbEntities()
            : base("name=Jem96DbEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<dias_semana> dias_semana { get; set; }
        public virtual DbSet<empleado> empleado { get; set; }
        public virtual DbSet<grupo> grupo { get; set; }
        public virtual DbSet<grupo_empleado> grupo_empleado { get; set; }
        public virtual DbSet<home> home { get; set; }
        public virtual DbSet<piso> piso { get; set; }
        public virtual DbSet<sector> sector { get; set; }
        public virtual DbSet<sysdiagrams> sysdiagrams { get; set; }
    }
}
