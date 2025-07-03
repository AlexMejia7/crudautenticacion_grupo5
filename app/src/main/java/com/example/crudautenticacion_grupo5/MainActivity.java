package com.example.crudautenticacion_grupo5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txtResultado);

        Button btnListar = findViewById(R.id.btnListar);
        Button btnCrear = findViewById(R.id.btnCrear);
        Button btnActualizar = findViewById(R.id.btnActualizar);
        Button btnEliminar = findViewById(R.id.btnEliminar);

        btnListar.setOnClickListener(v -> ApiClient.listarProductos(getCallback()));
        btnCrear.setOnClickListener(v -> {
            String json = "{\"nombre\":\"Producto Nuevo\", \"precio\":100}";
            ApiClient.crearProducto(json, getCallback());
        });
        btnActualizar.setOnClickListener(v -> {
            String json = "{\"id\":1, \"nombre\":\"Producto Actualizado\", \"precio\":150}";
            ApiClient.actualizarProducto(json, getCallback());
        });
        btnEliminar.setOnClickListener(v -> {
            int idEliminar = 1; // Cambia el id según lo que quieras eliminar
            ApiClient.eliminarProducto(idEliminar, getCallback());
        });
    }

    // Método para crear callback común para manejar respuestas y errores
    private Callback getCallback() {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> txtResultado.setText("Error: " + e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body() != null ? response.body().string() : "Respuesta vacía";
                runOnUiThread(() -> txtResultado.setText(res));
            }
        };
    }
}