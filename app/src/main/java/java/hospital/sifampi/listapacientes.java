package java.hospital.sifampi;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class listapacientes extends FirestoreRecyclerAdapter<contenido_lista, listapacientes.ViewHolder> {

    private Activity activity;
    private FirebaseFirestore mFirestore;
    private FirebaseAuth fAuth;
    String userID;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public listapacientes(@NonNull FirestoreRecyclerOptions<contenido_lista> options, Activity activity) {
        super(options);
        this.activity = activity;
        mFirestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
    }

    //Se establecen los valores que tendra la lista
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull contenido_lista articulo) {
        DocumentSnapshot PacienteListas = getSnapshots().getSnapshot(holder.getAdapterPosition());

        final String id = PacienteListas.getId();

        holder.txtNombre.setText(articulo.getNombre());
        holder.txtEstado.setText(articulo.getEstado());

        //Ver Paciente
        holder.buttonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esto pasa el id de los internados
                Intent intent = new Intent(activity, Familia.class);
                intent.putExtra("pacientesid", id);

                activity.startActivity(intent);
            }
        });


    }

    //Metodo que creara cada una de las vistas de la lista
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pacientes, parent, false);
        return new ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtEstado;

        Button buttonVer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtEstado = itemView.findViewById(R.id.txtEstado);
            txtNombre = itemView.findViewById(R.id.txtNombre);

            buttonVer = itemView.findViewById(R.id.btnVer);

        }

    }//Fin  public class ViewHolder

}//Fin public class listapacientes
