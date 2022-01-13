package com.example.t2_e2_antonio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

        }
    }


}