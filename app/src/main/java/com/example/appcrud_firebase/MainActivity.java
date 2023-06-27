package com.example.appcrud_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private EditText txtid, txtnom;
    private Button btnbus, btnmod, btnreg, btneli;
    private ListView lvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid   = (EditText) findViewById(R.id.txtid);
        txtnom  = (EditText) findViewById(R.id.txtnom);
        btnbus  = (Button)   findViewById(R.id.btnbus);
        btnmod  = (Button)   findViewById(R.id.btnmod);
        btnreg  = (Button)   findViewById(R.id.btnreg);
        btneli  = (Button)   findViewById(R.id.btneli);
        lvDatos = (ListView) findViewById(R.id.lvDatos);

        botonBuscar();
        botonModificar();
        botonRegistrar();
        botonEliminar();
    }//CIERRE ON CREATE


    private void botonBuscar(){}

    private void botonModificar(){}

    private void botonRegistrar(){
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtid.getText().toString().trim().isEmpty() || txtnom.getText().toString().trim().isEmpty()){

                    ocultarTeclado();
                    Toast.makeText(MainActivity.this, "Por Favor Complete Los Campos Faltantes",Toast.LENGTH_SHORT);
                }else {
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nombre = txtnom.getText().toString();

                    //Implementacion de firebase para guardar los datos//
                    //Conexion
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    //Referencia al elemento Tarea que coincide con la database.
                    DatabaseReference dbref = db.getReference(Tarea.class.getSimpleName());


                    //Despues de la conexion:
                    //Creacion del evento de firebase para llevar a cabo la tarea en este caso insercion.
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Tarea tarea = new Tarea(id, nombre);
                            //hacer la insercion en la base de datos
                            dbref.push().setValue(tarea);

                            ocultarTeclado();
                            Toast.makeText(MainActivity.this, "Tarea Registrada Correctamente", Toast.LENGTH_SHORT);
                            txtid.setText("");
                            txtnom.setText("");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
    }//Cierro el boton REGISTRAR

    private void botonEliminar(){}

    //Metodo Para ocultat tyeclado
    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el método ocultarTeclado.

}//CIERRE DE CLASE