using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using nuevaPracticaSomee.Models;
using System.Data.Entity;
using nuevaPracticaSomee.ViewModel;
using System.Web.Http.Cors;
using System.Web.Mvc;
using nuevaPracticaSomee.Services;

namespace nuevaPracticaSomee.Controllers
{
    #region LoginController class

    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class LoginController : ApiController
    {
        #region Fields

        IUserService _userService = new UserService();

        #endregion

        #region Public Methods

       
    
        public HttpResponseMessage Post(empleado user)
        {
            var response = new HttpResponseMessage();

            if (_userService.ValidateUserMD5(user))
            {
                byte[] time = BitConverter.GetBytes(DateTime.UtcNow.ToBinary());
                byte[] key = Guid.NewGuid().ToByteArray();
                string token = Convert.ToBase64String(time.Concat(key).ToArray());
                var httpToken = new HttpError(token);
                return Request.CreateErrorResponse(HttpStatusCode.OK, httpToken);
            }
            else
            {
                var message = String.Format("customer with id: {0} was not found", user.dni_empleado);
                var httpError = new HttpError(message);
                return Request.CreateErrorResponse(HttpStatusCode.Unauthorized, httpError);
            }
        }
        #endregion
    }
    #endregion
}