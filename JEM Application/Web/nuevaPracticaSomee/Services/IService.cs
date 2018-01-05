using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace nuevaPracticaSomee.Services
{
    public interface IService<T>
    {
        IQueryable<T> GetAll();

        IQueryable<T> GetAllReadOnly();

        T GetById(int id);

        void Insert(T entity);

        void Update(T entity);

        void Delete(T entity);

        void Delete(int id);

        void SaveOrUpdate(T entity);

        void InsertWithTransaction(T entity);

        void UpdateWithTransaction(T entity);

        void BeginTransaction();

        void Rollback();

        void Commit();
    }
}
