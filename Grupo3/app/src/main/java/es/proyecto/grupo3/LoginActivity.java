package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import es.proyecto.grupo3.db.DBManager;

public class LoginActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPassword;
    Button btn_Login;

    String name;
    String password;
    private DBManager dbHelper = new DBManager(LoginActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    editTextName =  findViewById(R.id.editTextName);
    editTextPassword = findViewById(R.id.editTextPassword);
    btn_Login = findViewById(R.id.btn_Login);

    name = editTextName.toString();
    password = editTextPassword.toString();


    btn_Login.setOnClickListener(v -> {
        dbHelper.Login(name, password);
        if (dbHelper.Login(name, password) == true){

            int idTendero = dbHelper.SelectIdTendero();
            Intent i = new Intent();
            i.putExtra(name, idTendero);
            finish();


        }
    });
    }

}