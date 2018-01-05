package com.example.jmora.webservicesoap.ui.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jmora.webservicesoap.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class AltaAlumnoActivity extends Activity implements View.OnClickListener {

    int dniAux,
        edadAux;
    String nombreAux,
           apellidoAux,
           sexoAux,
           divicionAux;
    EditText etDni,
             etNombre,
             etApellido,
             etEdad,
             etSexo;
    Spinner etSector;
    Button   btnSign;
    JSONObject atributo;
    StringEntity stringEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumno);

        atributo = new JSONObject();
        etDni = (EditText)findViewById(R.id.etDni);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText)findViewById(R.id.etApellido);
        etEdad = (EditText)findViewById(R.id.etEdad);
        etSexo = (EditText)findViewById(R.id.etSexo);
        etSector = (Spinner) findViewById(R.id.etSector);
        btnSign = (Button)findViewById(R.id.btnSign);

        btnSign.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        PostService postService = new PostService();
        postService.execute(
                etDni.getText().toString(),
                etNombre.getText().toString(),
                etApellido.getText().toString(),
                etEdad.getText().toString(),
                etSexo.getText().toString());

    }

    private class PostService extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL("http://jemwebapi.somee.com/api/Empleados");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                //conn.setRequestProperty("Host","http://jemwebapi.somee.com/api/Alumnos");
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.setRequestProperty("Accept","application/json");

                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();


                atributo.put("dni_alumno", Integer.parseInt(params[0]));
                atributo.put("nombre", params[1]);
                atributo.put("apellido", params[2]);
                atributo.put("edad", Integer.parseInt(params[3]));
                atributo.put("sexo", params[4]);


                DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
                writer.writeBytes(getPostDataString(atributo));

                writer.flush();
                writer.close();

                int responseCode = conn.getResponseCode();

                if(responseCode==204)
                {
                    return new String("Usuario registrado exitosamente");
                }

                else {
                    BufferedReader readError=new BufferedReader(
                            new InputStreamReader(
                                    conn.getErrorStream()));
                    return new String("false : "+readError.readLine().toString());
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());

            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }

    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    }


