using nuevaPracticaSomee.Models;
using nuevaPracticaSomee.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Repositories
{
    public interface IUserRepository : IRepository<empleado>
    {
        empleado GetByUserName(string userName);
    }
}