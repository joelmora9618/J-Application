using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Data.Entity.Core.Objects;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Linq.Expressions;
using System.Web;

namespace nuevaPracticaSomee.Repositories
{
    public class BaseRepository<T> : IRepository<T> where T : class
    {
        #region Fields

        protected readonly DbContext context;
        protected DbContextTransaction contextTransaction;

        #endregion

        #region Constructors

        public BaseRepository()
        {
            context = new AppDbContextFactory().GetContext();
        }

        #endregion

        #region Methods

        #region Public methods

        public DbContext GetContext()
        {
            return context;
        }

        /// <summary>
        /// Get the entity for Id
        /// </summary>
        public T GetById(int Id)
        {
            return context.Set<T>().Find(Id);
        }

        /// <summary>
        /// Get all entities
        /// </summary>
        public virtual IQueryable<T> GetAll()
        {
            return context.Set<T>();
        }

        /// <summary>
        /// Get all entities in mode read only
        /// </summary>
        /// <returns></returns>
        public virtual IQueryable<T> GetAllReadOnly()
        {
            return context.Set<T>().AsNoTracking();
        }

        /// <summary>
        /// Get entities that satisfy condition
        /// </summary>
        public IQueryable<T> Where(Expression<Func<T, bool>> predicate)
        {
            return context.Set<T>().Where(predicate);
        }

        /// <summary>
        /// Insert a entity.
        /// </summary>
        public void Insert(T o)
        {
            context.Set<T>().Add(o);
        }

        /// <summary>
        /// Update a entity
        /// </summary>
        public void Update(T o)
        {
            var entry = this.context.Entry(o);
            var key = this.GetPrimaryKey(entry);
            if (entry.State == EntityState.Detached)
            {
                var currentEntry = this.context.Set<T>().Find(key);
                if (currentEntry != null)
                {
                    var attachedEntry = this.context.Entry(currentEntry);
                    attachedEntry.CurrentValues.SetValues(o);
                }
                else
                {
                    this.context.Set<T>().Attach(o);
                    entry.State = EntityState.Modified;
                }
            }
        }

        /// <summary>
        /// Get the primary key for a entry
        /// </summary>
        private int GetPrimaryKey(DbEntityEntry entry)
        {
            var myObject = entry.Entity;
            var property =
                myObject.GetType()
                     .GetProperties().FirstOrDefault(prop => Attribute.IsDefined(prop, typeof(KeyAttribute)));
            return (int)property.GetValue(myObject, null);
        }

        /// <summary>
        /// Update a entity with his children
        /// </summary>
        public void Update(T o, HashSet<Type> childTypes)
        {
            AttachWithChilds(o, childTypes);
        }

        /// <summary>
        /// Insert multiple entities
        /// </summary>
        public void Insert(IEnumerable<T> oo)
        {
            foreach (T o in oo)
                Insert(o);
        }

        /// <summary>
        /// Save all changes in the context
        /// </summary>
        public void Save()
        {
            context.SaveChanges();
        }

        /// <summary>
        /// Save or Update the entity if exists
        /// </summary>
        /// <param name="o"></param>
        public void SaveOrUpdate(T o)
        {
            var entry = this.context.Entry(o);
            var key = GetPrimaryKey(entry);
            if (entry.State == EntityState.Detached)
            {
                var currentEntry = this.context.Set<T>().Find(key);
                if (currentEntry != null)
                {
                    var attachedEntry = this.context.Entry(currentEntry);
                    attachedEntry.CurrentValues.SetValues(o);
                }
                else
                {
                    this.context.Set<T>().Attach(o);
                    entry.State = EntityState.Added;
                }
            }
        }

        /// <summary>
        /// Delete a entity
        /// </summary>
        public void Delete(T o)
        {
            context.Set<T>().Remove(o);
        }

        /// <summary>
        /// Delete a entity by Id
        /// </summary>
        public void Delete(int id)
        {
            T o = GetById(id);
            Delete(o);
        }

        /// <summary>
        /// Begin a transaction
        /// </summary>
        public void BeginTransaction()
        {
            contextTransaction = context.Database.BeginTransaction();
        }

        /// <summary>
        /// Does a rollback
        /// </summary>
        public void Rollback()
        {
            if (contextTransaction != null)
                contextTransaction.Rollback();
        }

        /// <summary>
        /// Does a commit
        /// </summary>
        public void Commit()
        {
            Save();
            if (contextTransaction != null)
            {
                contextTransaction.Commit();
            }
        }

        #endregion

        #region Private methods

        /// <summary>
        /// Attach a entity with the children
        /// </summary>
        /// <param name="rootEntity"></param>
        /// <param name="childTypes"></param>
        public void AttachWithChilds(T rootEntity, HashSet<Type> childTypes)
        {
            context.Set<T>().Attach(rootEntity);
            context.Entry(rootEntity).State = EntityState.Modified;
            foreach (var entry in context.ChangeTracker.Entries())
            {
                if (entry.State == EntityState.Unchanged && entry.Entity != rootEntity)
                {
                    Type entityType = ObjectContext.GetObjectType(entry.Entity.GetType());
                    if (!childTypes.Contains(entityType))
                    {
                        entry.State = EntityState.Unchanged;
                    }
                    else
                    {
                        entry.State = EntityState.Modified;
                    }
                }
            }
        }

        #endregion

        #endregion
    }
}