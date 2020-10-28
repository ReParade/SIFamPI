package java.hospital.sifampi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Rellenar el Spinner
        opciones = (Spinner) findViewById(R.id.inicio);

        ArrayAdapter < CharSequence > adapter = ArrayAdapter.createFromResource(this, R.array.opciones, R.layout.spinner);
        opciones.setAdapter(adapter);

    }

    //Prueba solo para pasar a las diferentes pantallas
    //Borrar cuando este ligado a la base
    public void Pasar(View view){

        String select = opciones.getSelectedItem().toString();

        if ( select.equals("Administrador") ) {

            Intent modicar = new Intent(MainActivity.this, InfoAdmin.class);
            startActivity(modicar);

        }else if ( select.equals("Familiar") ){

            Intent modicar = new Intent(MainActivity.this, Familia.class);
            startActivity(modicar);

        }else {
            Toast.makeText(MainActivity.this, "Favor de seleccionar una opcion", Toast.LENGTH_SHORT).show();
        }
        
    }

}