package java.hospital.sifampi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Familia extends AppCompatActivity {

    TextView NumRegistro;
    TextView NomPaciente;
    TextView edad;
    TextView alergias;
    TextView NomDoc;
    TextView especial;
    TextView NoPiso;
    TextView NoCama;
    TextView estado;
    TextView aviso;
    TextView genero;
    TextView Fechaingreso;

    String userID;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familia);

        //Campos a rellenar
        NumRegistro = findViewById(R.id.ID);
        NomPaciente = findViewById(R.id.Paciente);
        edad = findViewById(R.id.Edad);
        alergias = findViewById(R.id.Alergias);
        NomDoc = findViewById(R.id.Doctor);
        especial = findViewById(R.id.Especialidad);
        NoPiso = findViewById(R.id.Piso);
        NoCama = findViewById(R.id.Cama);
        estado = findViewById(R.id.Estado);
        aviso = findViewById(R.id.Avisos);
        genero = findViewById(R.id.Genero);
        Fechaingreso = findViewById(R.id.Ingreso);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        obtenerdatos();

    }// fin One Create



    public void logout() {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflate = getMenuInflater();
        menuInflate.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.cerrar:
                logout();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }


    private void obtenerdatos(){
        userID = fAuth.getCurrentUser().getUid();

        fStore.collection("Pacientes").document(userID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                String id = documentSnapshot.getString("id");
                String paciente = documentSnapshot.getString("Nombre");
                String Edad = documentSnapshot.getString("Edad");
                String alergia = documentSnapshot.getString("Alergias");
                String doctor = documentSnapshot.getString("Doctor");
                String especialidad = documentSnapshot.getString("Especialidad");
                String piso = documentSnapshot.getString("Piso");
                String cama = documentSnapshot.getString("Cama");
                String Estado = documentSnapshot.getString("Estado");
                String Aviso = documentSnapshot.getString("Aviso");
                String fechaingreso = documentSnapshot.getString("Ingreso");
                String select = documentSnapshot.getString("Genero");

                NumRegistro.setText(id);
                NomPaciente.setText(paciente);
                edad.setText(Edad);
                genero.setText(select);
                alergias.setText(alergia);
                NomDoc.setText(doctor);
                especial.setText(especialidad);
                NoPiso.setText(piso);
                NoCama.setText(cama);
                estado.setText(Estado);
                aviso.setText(Aviso);
                Fechaingreso.setText(fechaingreso);

            }
        });

    }//Fin del obtenerdatos

}// Familia