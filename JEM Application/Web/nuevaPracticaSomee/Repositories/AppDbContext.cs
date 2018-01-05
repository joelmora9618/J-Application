using nuevaPracticaSomee.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace nuevaPracticaSomee.Repositories
{
    #region nuevaPracticaSomeeContext

    public class AppDbContext : DbContext
    {
        #region Properties

        public DbSet<empleado> Empleado { get; set; }


        #endregion

        #region Constructors

        public AppDbContext()
            : base()
        {

        }

        #endregion
    }
    
    #endregion
}