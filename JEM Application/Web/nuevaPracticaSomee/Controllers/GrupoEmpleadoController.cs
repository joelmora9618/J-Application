using nuevaPracticaSomee.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace nuevaPracticaSomee.Controllers
{
    public class GrupoEmpleadoController : ApiController
    {

        GrupoEmpleadoManager grupoEmpleadoManager = new GrupoEmpleadoManager();
        
        public IEnumerable<grupo_empleado> Get()
        {
            var listaGrupoEmpleado = grupoEmpleadoManager.ObtenerGrupo_empleado();
            return listaGrupoEmpleado;
        }
      
 
        public IEnumerable<grupo_empleado> Get(int dni)
        {
            var listaGrupoEmpleado = grupoEmpleadoManager.ObtenerGrupo_empleado(dni);
            return listaGrupoEmpleado;

        }

        [HttpPost]
        public void Post(grupo_empleado item)
        {
            grupoEmpleadoManager.InsertarGrupo_empleado(item);
        }
    }
}
