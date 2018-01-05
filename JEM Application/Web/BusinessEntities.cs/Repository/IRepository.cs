using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace BusinessEntities.cs.Repository
{
    public interface IRepository<T> : IDisposable where T : class
    {
        /// <summary>
        /// Encuentra un objeto por la expresión indicada
        /// </summary>
        /// <param name="predicate"></param>
        T Find(Expression<Func<T, bool>> predicate);

        T Get(object id);

        /// <summary>
        /// Crea un nuevo objeto en la base de datos
        /// </summary>
        /// <param name="t">Nuevo objeto a crear</param>
        T Create(T t);

        /// <summary>
        /// Borra objetos de la base de datos en base a una expresión de filtrado
        /// </summary>
        /// <param name="predicate"></param>
        int Delete(Expression<Func<T, bool>> predicate);

        /// <summary>
        /// Actualiza los cambios del objeto en la base de datos
        /// </summary>
        /// <param name="t">Objeto a actualizar</param>
        int Update(T t);
    }
}
