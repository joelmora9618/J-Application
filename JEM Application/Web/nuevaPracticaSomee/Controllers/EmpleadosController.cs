using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using nuevaPracticaSomee.Models;
using System.Data.Entity;
using nuevaPracticaSomee.ViewModel;

namespace nuevaPracticaSomee.Controllers
{
    public class EmpleadosController : ApiController
    {
        EmpleadoManager empleadoManager = new EmpleadoManager();

        [HttpGet]
        public List<EmpleadoViewModel> Get()
        {
           return empleadoManager.Get();
        }

        [HttpGet]
       // [Route("empleados/{dni}")]
        public EmpleadoViewModel Get(int id)
        {
            return empleadoManager.Get(id);
        }


        [HttpPost]
        public HttpResponseMessage Post([FromBody]EmpleadoViewModel item)
        {
            var response = new HttpResponseMessage();

            try
            {
                empleadoManager.Post(item);
                var message = String.Format("the user {0} was successfully registered", item.Nombre);
                var httpRes = new HttpError(message);
                return Request.CreateErrorResponse(HttpStatusCode.OK, httpRes);
            }
            catch
            {
                var message = String.Format("error registering user");
                var httpError = new HttpError(message);
                return Request.CreateErrorResponse(HttpStatusCode.Unauthorized, httpError);
            }
             
        }

    }
}
