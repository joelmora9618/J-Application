using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace nuevaPracticaSomee.Repositories
{
    public interface IRepository<T>
    {
        T GetById(int Id);
        IQueryable<T> GetAll();
        IQueryable<T> GetAllReadOnly();
        IQueryable<T> Where(Expression<Func<T, bool>> predicate);
        void Insert(T o);
        void Update(T o);
        void SaveOrUpdate(T o);
        void Save();
        void Delete(T o);
        void Delete(int id);
        void Update(T o, HashSet<Type> childTypes);
        void BeginTransaction();
        void Rollback();
        void Commit();
    }

}
