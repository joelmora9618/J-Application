using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace BusinessEntities.cs.Repository
{
    public class Repository<TObject> : IRepository<TObject> where TObject : class
    {
        protected MyContext Context = null;

        /// <summary>
        /// Constructor de clase
        /// </summary>
        /// <param name="context">Contexto específico del proyecto</param>
        public Repository(MyContext context)
        {
            Context = context;
        }

        /// <summary>
        /// Propiedad que devuelve una instancia DbSet del tipo TObject dado
        /// </summary>
        protected DbSet<TObject> DbSet
        {
            get
            {
                return Context.Set<TObject>();
            }
        }

        /// <summary>
        /// Devuelve un conjunto de registros filtrado por la expresión indicada en el parámetro
        /// </summary>
        /// <param name="predicate">Expresión de filtrado</param>
        /// <returns></returns>
        protected IQueryable<TObject> Filter(Expression<Func<TObject, bool>> predicate)
        {
            return DbSet.Where(predicate).AsQueryable<TObject>();
            // TODO: Control de excepciones
        }

        /// <summary>
        /// Liberación de recursos
        /// </summary>
        public void Dispose()
        {
            if (Context != null)
                Context.Dispose();
        }

        /// <summary>
        /// Devuelve el primer registro que satisfaga la expresión de búsqueda pasada como parámetro
        /// </summary>
        /// <param name="predicate">Expresión de búsqueda</param>
        /// <returns></returns>
        public virtual TObject Find(Expression<Func<TObject, bool>> predicate)
        {
            return DbSet.FirstOrDefault(predicate);
            // TODO: Control de excepciones
        }


        public virtual TObject GetByDni(int dni)
        {
            return DbSet.Find(dni);
        }

        public object Find()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Añade la entidad pasada como parámetro al contexto, guardando los cambios
        /// </summary>
        /// <param name="TObject">El objeto a añadir al contexto</param>
        /// <returns></returns>
        public virtual TObject Create(TObject TObject)
        {
            var newEntry = DbSet.Add(TObject);
            Context.SaveChanges();
            return newEntry;
            // TODO: Control de excepciones
        }

        /// <summary>
        /// Elimina los objetos del contexto que satisfagan la condición de búsqueda pasada como parámetro, guardando los cambios
        /// </summary>
        /// <param name="predicate">Condición de búsqueda</param>
        /// <returns></returns>
        public virtual int Delete(Expression<Func<TObject, bool>> predicate)
        {
            var objects = Filter(predicate);
            foreach (var obj in objects)
                DbSet.Remove(obj);
            return Context.SaveChanges();
            // TODO: Control de excepciones
        }

        /// <summary>
        /// Actualiza el objeto pasado como parámetro con sus valores en el contexto, guardando los cambios
        /// </summary>
        /// <param name="TObject">El objeto con los nuevos valores</param>
        /// <returns></returns>
        public virtual int Update(TObject TObject)
        {
            var entry = Context.Entry(TObject);
            DbSet.Attach(TObject);
            entry.State = EntityState.Modified;
            return Context.SaveChanges();
            // TODO: Control de excepciones
        }

    }
}
