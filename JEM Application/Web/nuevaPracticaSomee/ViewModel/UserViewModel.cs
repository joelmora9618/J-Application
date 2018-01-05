using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace nuevaPracticaSomee.ViewModel
{
    public class UserViewModel
    {
        private string nombre;
        private string password;

        [Required(ErrorMessage = "El Nombre es rquerido")]
        [Display(Name = "Primer nombre")]
        public string Nombre
        {
            get
            {
                return nombre;
            }

            set
            {
                nombre = value;
            }
        }

        [Required(ErrorMessage = "El Password es rquerido")]
        [Display(Name = "Password")]
        public string Password
        {
            get
            {
                return password;
            }

            set
            {
                password = value;
            }
        }
    }
}