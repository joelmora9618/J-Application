using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using nuevaPracticaSomee.Models;

namespace nuevaPracticaSomee.Controllers
{
    public class grupo_empleadoController : ApiController
    {
        private Jem96DbEntities db = new Jem96DbEntities();

        // GET: api/grupo_empleado
        public IQueryable<grupo_empleado> Getgrupo_empleado()
        {
            return db.grupo_empleado;
        }

        // GET: api/grupo_empleado/5
        [ResponseType(typeof(grupo_empleado))]
        public IHttpActionResult Getgrupo_empleado(int id)
        {
            grupo_empleado grupo_empleado = db.grupo_empleado.Find(id);
            if (grupo_empleado == null)
            {
                return NotFound();
            }

            return Ok(grupo_empleado);
        }

        // PUT: api/grupo_empleado/5
        [ResponseType(typeof(void))]
        public IHttpActionResult Putgrupo_empleado(int id, grupo_empleado grupo_empleado)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != grupo_empleado.id_grupo_empleado)
            {
                return BadRequest();
            }

            db.Entry(grupo_empleado).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!grupo_empleadoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/grupo_empleado
        [ResponseType(typeof(grupo_empleado))]
        public IHttpActionResult Postgrupo_empleado(grupo_empleado grupo_empleado)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.grupo_empleado.Add(grupo_empleado);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = grupo_empleado.id_grupo_empleado }, grupo_empleado);
        }

        // DELETE: api/grupo_empleado/5
        [ResponseType(typeof(grupo_empleado))]
        public IHttpActionResult Deletegrupo_empleado(int id)
        {
            grupo_empleado grupo_empleado = db.grupo_empleado.Find(id);
            if (grupo_empleado == null)
            {
                return NotFound();
            }

            db.grupo_empleado.Remove(grupo_empleado);
            db.SaveChanges();

            return Ok(grupo_empleado);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool grupo_empleadoExists(int id)
        {
            return db.grupo_empleado.Count(e => e.id_grupo_empleado == id) > 0;
        }
    }
}