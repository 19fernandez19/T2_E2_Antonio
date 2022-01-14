package com.example.t2_e2_antonio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteHelper helper;
    EditText textoNombre, textoPeso;
    Spinner sabor;
    CheckBox rotten;
    TextView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNombre = findViewById(R.id.textoNombre);
        textoPeso = findViewById(R.id.textoWeight);
        sabor = findViewById(R.id.spinner);
        rotten = findViewById(R.id.checkboxRotten);
        lista = findViewById(R.id.listaRegistros);

        //crear objeto de la clase SQLiteHelper
        helper = new SQLiteHelper(this);

    }

    public void insertar(View view){
        if (textoNombre.getText().toString().isEmpty() || textoPeso.getText().toString().isEmpty() || sabor.getSelectedItem().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT);
            toast.show();
            lista.setText("");
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Datos Ingresados", Toast.LENGTH_SHORT);
            toast.show();
            db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", textoNombre.getText().toString());
            values.put("peso", textoPeso.getText().toString());
            values.put("sabor", sabor.getSelectedItem().toString());
            values.put("rotten", rotten.isSelected());
            db.insert("Fruitis", null, values);



        }
    }



    public void buscar(Cursor cursor){
        //Ir al primer registro
        cursor.moveToFirst();
        int fila=cursor.getCount();
        int columna=cursor.getColumnCount();
        String filas="\n";

        for(int i=0; i<fila; i++){
            filas="\n";
            for(int j=0; j<columna; j++){
                filas=filas+cursor.getString(j) + " ";
            }
            lista.append(filas);
            cursor.moveToNext();
        }
    }


    public void mostrar(View view) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("Fruitis", null, null, null, null, null, null);
        buscar(cursor);

        textoNombre.setText("");
        textoPeso.setText("");
        rotten.setSelected(false);
    }
}