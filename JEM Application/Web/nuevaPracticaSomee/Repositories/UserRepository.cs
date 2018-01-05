using nuevaPracticaSomee.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Repositories
{
    public class UserRepository : BaseRepository<empleado>, IUserRepository
    {
        /// <summary>
        /// Get a user by user name
        /// </summary>
        public empleado GetByUserName(string userName)
        {
            var query = from u in GetContext().Set<empleado>()
                        where u.nombre == userName
                       
                        select u;
            return query.FirstOrDefault();
        }

    }
}