using nuevaPracticaSomee.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace nuevaPracticaSomee.Services
{
    interface IUserService : IService<empleado>
    {
        empleado GetByUserName(string userName);
        bool ValidateUser(ref empleado user);
        bool ValidateUserMD5(empleado user);
    }
}
