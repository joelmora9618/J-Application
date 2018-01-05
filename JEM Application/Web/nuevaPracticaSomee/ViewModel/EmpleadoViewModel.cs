using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace nuevaPracticaSomee.ViewModel
{
    public class EmpleadoViewModel
    {
        private int dni;
        private string nombre;
        private string apellido;
        private DateTime fecha_de_nacimiento;
        private string sexo;
        private string password;

        [Required(ErrorMessage = "El DNI es rquerido")]
        [Display(Name = "DNI")]
        public int Dni
        {
            get
            {
                return dni;
            }

            set
            {
                dni = value;
            }
        }

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

        [Required(ErrorMessage = "El Apellido es rquerido")]
        [Display(Name = "Apellido")]
        public string Apellido
        {
            get
            {
                return apellido;
            }

            set
            {
                apellido = value;
            }
        }

        [Required(ErrorMessage = "La fecha de nacimiento es rquerida")]
        [Display(Name = "Fecha de nacimiento")]
        public DateTime Fecha_de_nacimiento
        {
            get
            {
                return fecha_de_nacimiento;
            }

            set
            {
                fecha_de_nacimiento = value;
            }
        }

        [Required(ErrorMessage = "El Sexo es rquerido")]
        [Display(Name = "Sexo")]
        public string Sexo
        {
            get
            {
                return sexo;
            }

            set
            {
                sexo = value;
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