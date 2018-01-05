using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using nuevaPracticaSomee.Models;

namespace nuevaPracticaSomee.Controllers
{
    public class GrupoController : ApiController
    {
        GroupManager grupoManager = new GroupManager();
        public IEnumerable<grupo> Get()
        {
            var listaGrupos = grupoManager.ObtenerGrupos();
            return listaGrupos;
        }

        [HttpPost]
        public void Post(grupo item)
        {
            grupoManager.InsertarGrupo(item);
        }
    }
}
