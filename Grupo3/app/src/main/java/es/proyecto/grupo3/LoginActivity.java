package es.proyecto.grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import es.proyecto.grupo3.db.DBManager;
import es.proyecto.grupo3.modelo.Tendero;

public class LoginActivity extends AppCompatActivity {

    private DBManager dbHelper = new DBManager(LoginActivity.this);
    private TextInputEditText editName;
    private TextInputEditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         editName =  findViewById(R.id.editTextName);
         editPassword = findViewById(R.id.editTextPassword);
        Button btn_Login = findViewById(R.id.btn_Login);
        Button btn_Volver = findViewById(R.id.BtnAtras);

    btn_Login.setOnClickListener(v -> {

        String name = editName.getText().toString();
        String password = editPassword.getText().toString();

        boolean correcto;
        correcto = dbHelper.Login(name, password);

        if (correcto == true){

            int idTendero = dbHelper.SelectIdTendero(name);

            Intent intent = getIntent();
                setResult(RESULT_OK, intent);
            Bundle extras = new Bundle();
            extras.putString("nombreTendero", name);
            extras.putInt("id", idTendero);
            intent.putExtras(extras);
            finish();

        }
        else{
            Toast toast = Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_LONG);
            toast.show();
        }
    });

        btn_Volver.setOnClickListener(v -> {
            Intent intent = getIntent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

//        btn_Login.setOnClickListener( (v) -> {
//            DBManager db = new DBManager(this);
//            db.getWritableDatabase();
//
//            Tendero tendero = new Tendero();
//
//            tendero.setNombre(name);
//            tendero.setPassword(password);
//
//            if (dbHelper.Login2(tendero)) {
//
//                int idTendero = dbHelper.SelectIdTendero();
//
//                Intent intent = new Intent (LoginActivity.this, PerfilActivity.class);
//                setResult(RESULT_OK, intent);
//                intent.putExtra("nombreTendero", name);
//                intent.putExtra("id", idTendero);
//                finish();
//
//            } else {
//                Toast toast = Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_LONG);
//                toast.show();
//            }
//        } );
    }

}