package java.hospital.sifampi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InfoAdmin extends AppCompatActivity {

    Spinner genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_admin);

        // Rellenar el Spinner
        genero = (Spinner) findViewById(R.id.Genero);

        ArrayAdapter< CharSequence > adapter = ArrayAdapter.createFromResource(this, R.array.genero, R.layout.spinner);
        genero.setAdapter(adapter);

    }
}