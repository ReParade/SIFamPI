package java.hospital.sifampi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Spinner opciones;

    EditText Ecorreo, Econtra;
    Button Mbutton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ecorreo = findViewById(R.id.correo);
        Econtra = findViewById(R.id.contra);
        progressBar = findViewById(R.id.progressBar);
        Mbutton = findViewById(R.id.button);
        fAuth = FirebaseAuth.getInstance();

        // Rellenar el Spinner
        opciones = (Spinner) findViewById(R.id.inicio);

        ArrayAdapter < CharSequence > adapter = ArrayAdapter.createFromResource(this, R.array.opciones, R.layout.spinner);
        opciones.setAdapter(adapter);


        //Darle la funcionalidad al boton para que incie sesion
        Mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Tomar los valores de los EditText y guardar en variables
                String email = Ecorreo.getText().toString().trim();
                String password = Econtra.getText().toString().trim();
                final String select = opciones.getSelectedItem().toString();

                //Validar que los EditText este llenos o mandar un error
                if (TextUtils.isEmpty(email)) {
                    Ecorreo.setError("Correo Requerido");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Ecorreo.setError("Contraseña Requerida");
                    return;
                }
                if ( select.equals("Eliga una opcion") ) {
                    Toast.makeText(MainActivity.this, "Favor de seleccionar una opcion", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                // Verificar usuario
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ( task.isSuccessful() ){
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                            //Saber si es Admin o Familiar
                            if ( select.equals("Administrador") ) {
                                startActivity(new Intent(getApplicationContext(), InfoAdmin.class));
                            }else if ( select.equals("Familiar") ){
                                startActivity(new Intent(getApplicationContext(), Familia.class));
                            }

                        }else{
                            Toast.makeText(MainActivity.this, "Contraseña o correo incorrecto", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }//Fin del onCreate

}//MainActivity