using nuevaPracticaSomee.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Services
{
    public class BaseService<T> : IService<T>
    {
        #region Fields

        protected IRepository<T> _repository;

        #endregion

        #region Public methods

        public T GetById(int id)
        {
            return _repository.GetById(id);
        }

        public IQueryable<T> GetAll()
        {
            return _repository.GetAll();
        }

        public IQueryable<T> GetAllReadOnly()
        {
            return _repository.GetAllReadOnly();
        }

        public void Insert(T entity)
        {
            _repository.Insert(entity);
            _repository.Save();
        }

        public void Update(T entity)
        {
            _repository.Update(entity);
            _repository.Save();
        }

        public void SaveOrUpdate(T entity)
        {
            _repository.SaveOrUpdate(entity);
            _repository.Save();
        }

        public void Delete(T entity)
        {
            _repository.Delete(entity);
            _repository.Save();
        }

        public void Delete(int id)
        {
            _repository.Delete(id);
            _repository.Save();
        }

        public void InsertWithTransaction(T entity)
        {
            _repository.Insert(entity);
        }

        public void UpdateWithTransaction(T entity)
        {
            _repository.Update(entity);
        }

        public void BeginTransaction()
        {
            _repository.BeginTransaction();
        }

        public void Rollback()
        {
            _repository.Rollback();
        }

        public void Commit()
        {
            _repository.Commit();
        }

        #endregion
    }
}