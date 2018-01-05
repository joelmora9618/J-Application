using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;


namespace nuevaPracticaSomee.Helpers
{
    #region EncryptHelper

    public static class EncryptHelper
    {
        #region static methods

        private static byte[] key = { 123, 217, 19, 11, 24, 26, 85, 45, 114, 184, 27, 162, 37, 112, 222, 209, 241, 24, 175, 144, 173, 53, 196, 29, 24, 26, 17, 218, 131, 236, 53, 209 };
        private static byte[] vector = { 146, 64, 191, 111, 23, 3, 113, 119, 231, 121, 221, 112, 79, 32, 114, 156 };

        public static byte[] Clave = Encoding.ASCII.GetBytes("Tu Clave");
        public static byte[] IV = Encoding.ASCII.GetBytes("Devjoker7.37hAES");

        private static RijndaelManaged rm = new RijndaelManaged();
        private static ICryptoTransform encryptor = rm.CreateEncryptor(key, vector);
        private static ICryptoTransform decryptor = rm.CreateDecryptor(key, vector);
        private static UTF8Encoding encoder = new UTF8Encoding();

        public static string Encrypt(string unencrypted)
        {
            return Convert.ToBase64String(Encrypt(encoder.GetBytes(unencrypted)));
        }

        public static string Decrypt(string encrypted)
        {
            return DesEncriptar(encrypted);
        }

        public static byte[] Encrypt(byte[] buffer)
        {
            using (var encryptStream = new MemoryStream())
            {
                using (var cs = new CryptoStream(encryptStream, encryptor, CryptoStreamMode.Write))
                {
                    cs.Write(buffer, 0, buffer.Length);
                }
                return encryptStream.ToArray();
            }
        }

        public static string Desencriptar(string Cadena)
        {
            byte[] inputBytes = Convert.FromBase64String(Cadena);
            byte[] resultBytes = new byte[inputBytes.Length];
            string textoLimpio = String.Empty;
            RijndaelManaged cripto = new RijndaelManaged();
            using (MemoryStream ms = new MemoryStream(inputBytes))
            {
                using (CryptoStream objCryptoStream = new CryptoStream(ms, cripto.CreateDecryptor(key, vector), CryptoStreamMode.Read))
                {
                    using (StreamReader sr = new StreamReader(objCryptoStream, true))
                    {
                        textoLimpio = sr.ReadToEnd();
                    }
                }
            }
            return textoLimpio;
        }

        public static string DesEncriptar(this string _cadenaAdesencriptar)
        {
            string result = string.Empty;
            byte[] decryted = Convert.FromBase64String(_cadenaAdesencriptar);
            //result = System.Text.Encoding.Unicode.GetString(decryted, 0, decryted.ToArray().Length);
            result = System.Text.Encoding.Unicode.GetString(decryted);
            return result;
        }

        public static byte[] Decrypt(byte[] buffer)
        {
            using (var decryptStream = new MemoryStream())
            {
                using (var cs = new CryptoStream(decryptStream, decryptor, CryptoStreamMode.Write))
                {
                    cs.Write(buffer, 0, buffer.Length);
                }
                return decryptStream.ToArray();
            }
        }

        public static string MD5Hash(string text)
        {
            MD5 md5 = new MD5CryptoServiceProvider();

            //compute hash from the bytes of text
            md5.ComputeHash(ASCIIEncoding.ASCII.GetBytes(text));

            //get hash result after compute it
            byte[] result = md5.Hash;

            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < result.Length; i++)
            {
                //change it into 2 hexadecimal digits
                //for each byte
                strBuilder.Append(result[i].ToString("x2"));
            }

            return strBuilder.ToString();
        }

        // Verify a hash against a string.
        public static bool VerifyMd5Hash(string input, string hash)
        {
            // Hash the input.
         //   string hashOfInput = MD5Hash(input);
            // Create a StringComparer an compare the hashes.
            StringComparer comparer = StringComparer.OrdinalIgnoreCase;
            if (0 == comparer.Compare(input, hash))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        #endregion
    }

    #endregion
}