//------------------------------------------------------------------------------
// <auto-generated>
//     Este código se generó a partir de una plantilla.
//
//     Los cambios manuales en este archivo pueden causar un comportamiento inesperado de la aplicación.
//     Los cambios manuales en este archivo se sobrescribirán si se regenera el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace BusinessEntities.cs.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class grupo_empleado
    {
        public int id_grupo_empleado { get; set; }
        public Nullable<int> id_grupo { get; set; }
        public Nullable<int> dni_empleado { get; set; }
    
        public virtual empleado empleado { get; set; }
        public virtual grupo grupo { get; set; }
    }
}
