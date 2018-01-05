using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Repositories
{
        #region IJemApplicationContextFactory interface

        public interface IDbContextFactory
        {
            DbContext GetContext();
        }

        #endregion

        #region JemApplicationContextFactory class

        public class AppDbContextFactory
        {
            private readonly DbContext context;

            public AppDbContextFactory()
            {
                context = new AppDbContext();
                //context.Configuration.LazyLoadingEnabled = false;
                //context.Configuration.ProxyCreationEnabled = false;
            }

            public DbContext GetContext()
            {
                return context;
            }
        }

        #endregion
    }
