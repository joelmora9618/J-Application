package com.example.jmora.webservicesoap.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jmora.webservicesoap.BuildConfig;
import com.example.jmora.webservicesoap.Busines.SyncBusiness;
import com.example.jmora.webservicesoap.Busines.UserBusiness;
import com.example.jmora.webservicesoap.Busines.VersionBusiness;
import com.example.jmora.webservicesoap.Data.Constants;
import com.example.jmora.webservicesoap.Data.SharedPreferences.JemPreferences;
import com.example.jmora.webservicesoap.R;
import com.example.jmora.webservicesoap.Models.Empleado;
import com.example.jmora.webservicesoap.Utils.ProgressUtils;
import com.example.jmora.webservicesoap.validator.Exceptions.CredentialsException;
import com.example.jmora.webservicesoap.validator.Form;
import com.example.jmora.webservicesoap.validator.Validate;
import com.example.jmora.webservicesoap.validator.validator.NotEmptyValidator;

import java.util.ArrayList;

public class LoginActivity extends SyncActivity{

    ArrayList<Empleado> listaAlumnos;
    TextView tvValidation,
             tvUserValidation,
             tvPassValidation;
    Activity _activity;
    public static String DNI_USER = "DNI_USER";
    private Empleado usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _activity = this;

        tvValidation = (TextView)findViewById(R.id.tvValidation);
        tvUserValidation = (TextView)findViewById(R.id.tvUserValidation);
        tvPassValidation = (TextView)findViewById(R.id.tvPassValidation);

        setVersion();
        initControls();

    }

    @Override
    protected void onResume() {
        super.onResume();

        initText();

        LinearLayout layProgress = (LinearLayout)findViewById(R.id.layProgress);
        layProgress.setVisibility(View.GONE);
    }

    private void setVersion(){
        TextView lblVersion = (TextView)findViewById(R.id.tvVersion);
        lblVersion.setText(String.format("Version: %s", BuildConfig.VERSION_NAME));
    }

    private void initText(){
        final String prfUser = JemPreferences.getString(this, JemPreferences.KEY_USER, "");
        final String prfPwd = JemPreferences.getString(this, JemPreferences.KEY_PASS, "");

        TextView txtUser = (TextView) findViewById(R.id.etDni);
        txtUser.setText(prfUser);
        TextView txtPin = (TextView) findViewById(R.id.etPass);
        txtPin.setText(prfPwd);
    }

    private  void initControls(){

        final String prfUser = JemPreferences.getString(this, JemPreferences.KEY_USER, "");
        final String prfPwd = JemPreferences.getString(this, JemPreferences.KEY_PASS, "");

        initText();

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView txtUser = (TextView) findViewById(R.id.etDni);
                TextView txtPin = (TextView) findViewById(R.id.etPass);

                final String user = txtUser.getText().toString().trim();
                final String pin = txtPin.getText().toString().trim();

                JemPreferences.setString(_activity, JemPreferences.KEY_USER, user);
                JemPreferences.setString(_activity, JemPreferences.KEY_PASS, pin);

                if (UserBusiness.equalsCredentials(prfUser.trim(), prfPwd, user.trim(), pin)) {
                    login(user, pin);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    //TODO regionalizar
                    builder
                            .setMessage("You are about to change of user. You are going to lose the information which is not synchronized. Do you want to continue?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    // Yes-code

                                    login(user, pin);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            }
        });

        Button btnSign = (Button)findViewById(R.id.btnSign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AltaAlumnoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(final String user, final String pin){
        try{
            UserBusiness.validateCredentials(user,pin);
            if(VersionBusiness.isOnline(this)){
                final LinearLayout layProgress = (LinearLayout)findViewById(R.id.layProgress);
                layProgress.setVisibility(View.VISIBLE);
                final ProgressDialog progress = ProgressUtils.getDialog(this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Thread.sleep(300);
                            //valida usuario en el servicio
                            UserBusiness.postUser(user, pin, getBaseContext());
                            syncData(LoginActivity.this,progress);

                                    Handler mainHandler = new Handler(_activity.getMainLooper());

                                    Runnable myRunnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            //main thread
                                            loginAfterVersionValidation(user, pin);
                                            layProgress.setVisibility(View.GONE);
                                        }
                                    };
                                    mainHandler.post(myRunnable);


                        }catch (CredentialsException e) {
                            showCrouton(_activity, e.getMessage());
                            Handler mainHandler = new Handler(getApplicationContext().getMainLooper());

                            Runnable myRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    //main thread

                                    Intent i = new Intent(_activity, LoginActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                }
                            };
                            mainHandler.post(myRunnable);
                        }
                        catch (Exception ex){

                        }finally {
                        }
                    }
                }).start();
            }else{

            }
        }catch (Exception e){

        }
    }

    private void loginAfterVersionValidation(String user,String pin){
        String currentUser = JemPreferences.getString(getApplicationContext(), Constants.currentUser, "");

        if (!currentUser.equalsIgnoreCase("") && !user.equalsIgnoreCase(currentUser)) {

            //Delete previous data user.
            JemPreferences.cleanData(getApplicationContext());
            SyncBusiness.deleteAll(getApplicationContext());
        }

        JemPreferences.setString(getApplicationContext(), Constants.currentUser, user);


        JemPreferences.setString(getApplicationContext(), JemPreferences.KEY_USER, user);
        JemPreferences.setString(getApplicationContext(), JemPreferences.KEY_PASS, pin);



        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public boolean ValidateUser(ArrayList<Empleado>listaAlumnos, EditText user, EditText pass)
    {
        usuario = new Empleado();
        boolean validateUser = false,
                validatePass = false,
                validate = false;

            tvUserValidation.setVisibility(View.GONE);
            tvPassValidation.setVisibility(View.GONE);

        if(validations())
        {
            for (Empleado empleado:listaAlumnos) {

                String stringUser = String.valueOf(empleado.getDni()).toString();
                String stringPass = String.valueOf(empleado.getDni()).toString();

                String userIngresed = user.getText().toString();
                String passIngresed = pass.getText().toString();

                //Validate User
                if(userIngresed.equals(stringUser))
                {
                    validateUser = true;
                    //Validate Password
                    if(passIngresed.equals(stringPass))
                    {
                        validatePass = true;
                        usuario = empleado;
                    }
                }


            }

            //Label
            if(validateUser)
            {
                if(validatePass)
                {
                    validate = true;
                    tvUserValidation.setVisibility(View.GONE);
                    tvPassValidation.setVisibility(View.GONE);

                }else
                {
                    tvPassValidation.setVisibility(View.VISIBLE);
                    tvPassValidation.setText("*la clave ingresada no es valida");
                }
            }else
            {
                tvUserValidation.setVisibility(View.VISIBLE);
                tvUserValidation.setText("*el usuario ingresado no es valido");
            }
        }

        return validate;
    }

    private boolean validations() {
        final EditText  txtUsuario = (EditText)findViewById(R.id.etDni);
        final EditText txtContrasena = (EditText)findViewById(R.id.etPass);

        Validate usuarioField = new Validate(txtUsuario);
        usuarioField.addValidator(new NotEmptyValidator(getBaseContext()));

        Validate contraField = new Validate(txtContrasena);
        contraField.addValidator(new NotEmptyValidator(getBaseContext()));

        Form mForm = new Form();
        mForm.addValidates(usuarioField);
        mForm.addValidates(contraField);

        return mForm.validate();
    }
}
