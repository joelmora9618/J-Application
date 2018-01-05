using nuevaPracticaSomee.Helpers;
using nuevaPracticaSomee.Models;
using nuevaPracticaSomee.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.Services
{
    public class UserService : BaseService<empleado>, IUserService
    {
        #region Fields

        IUserRepository _userRepository;

        #endregion

        #region Constructors

        public UserService()
        {
            base._repository = _userRepository = new UserRepository();
        }

        #endregion

        #region Public Methods

        /// <summary>
        /// Return a user by user name
        /// </summary>
        public empleado GetByUserName(string userName)
        {
            return _userRepository.GetByUserName(userName);
        }

        /// <summary>
        /// Validate user and password
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        public bool ValidateUser(ref empleado user)
        {
            var password = EncryptHelper.Encrypt(user.password);
            var userName = user.nombre;
            user = _userRepository.Where(u => u.nombre == userName && u.password == password && u.deleted!=1).FirstOrDefault();
            return user != null;
        }

        /// <summary>
        /// Validate user and password with MD5 algorithm
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        public bool ValidateUserMD5(empleado user)
        {
            string hashMD5 = user.password;
            var user_DB = _userRepository.Where(u=>u.dni_empleado == user.dni_empleado).FirstOrDefault();
            
            if (user_DB != null)
            {
               // string pwdRSA = EncryptHelper.Decrypt(user.password);
                return EncryptHelper.VerifyMd5Hash(user.password, user_DB.password);
            }
            else
                return false;
        }

        #endregion
    }
}