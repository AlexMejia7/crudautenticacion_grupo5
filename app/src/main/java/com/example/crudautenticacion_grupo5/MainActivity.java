package com.example.crudautenticacion_grupo5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etDescripcion, etPrecio, etId, etIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etId = findViewById(R.id.etId);
        etIp = findViewById(R.id.etIp);

        Button btnListar = findViewById(R.id.btnListar);
        Button btnCrear = findViewById(R.id.btnCrear);
        Button btnActualizar = findViewById(R.id.btnActualizar);
        Button btnEliminar = findViewById(R.id.btnEliminar);
        Button btnConectar = findViewById(R.id.btnConectar);
        Button btnBuscar = findViewById(R.id.btnBuscar);

        btnConectar.setOnClickListener(v -> {
            String ip = etIp.getText().toString().trim();
            if (!ip.isEmpty()) {
                ApiClient.setIpBase(ip);
                Toast.makeText(this, "IP actualizada a: " + ip, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ingresa una IP válida", Toast.LENGTH_SHORT).show();
            }
        });

        btnListar.setOnClickListener(v -> ApiClient.listarProductos(getCallback()));

        btnCrear.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String precioStr = etPrecio.getText().toString().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "Nombre, descripción y precio son requeridos", Toast.LENGTH_SHORT).show();
                return;
            }

            String json = "{\"nombre\":\"" + nombre + "\", \"descripcion\":\"" + descripcion + "\", \"precio\":" + precioStr + "}";
            ApiClient.crearProducto(json, getCallback());
        });

        btnActualizar.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();
            String nombre = etNombre.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String precioStr = etPrecio.getText().toString().trim();

            if (idStr.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "ID, nombre, descripción y precio son requeridos", Toast.LENGTH_SHORT).show();
                return;
            }

            String json = "{\"id\":" + idStr + ", \"nombre\":\"" + nombre + "\", \"descripcion\":\"" + descripcion + "\", \"precio\":" + precioStr + "}";
            ApiClient.actualizarProducto(json, getCallback());
        });

        btnEliminar.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();

            if (idStr.isEmpty()) {
                Toast.makeText(this, "ID es requerido", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idStr);
            ApiClient.eliminarProducto(id, getCallback());
        });

        // Buscar producto por ID
        btnBuscar.setOnClickListener(v -> {
            String idStr = etId.getText().toString().trim();
            if (idStr.isEmpty()) {
                Toast.makeText(this, "Ingresa un ID para buscar", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = ApiClient.getBaseUrl() + "buscar.php?id=" + idStr;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("x-api-key", ApiClient.getApiKey())
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() ->
                            Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        runOnUiThread(() ->
                                Toast.makeText(MainActivity.this, "No se encontró el producto", Toast.LENGTH_SHORT).show());
                        return;
                    }

                    String res = response.body().string();

                    try {
                        JSONObject json = new JSONObject(res);
                        String nombre = json.getString("nombre");
                        String descripcion = json.getString("descripcion");
                        double precio = json.getDouble("precio");

                        runOnUiThread(() -> {
                            etNombre.setText(nombre);
                            etDescripcion.setText(descripcion);
                            etPrecio.setText(String.valueOf(precio));
                        });
                    } catch (Exception e) {
                        runOnUiThread(() ->
                                Toast.makeText(MainActivity.this, "Error al analizar datos", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        });
    }

    private Callback getCallback() {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() ->
                        Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body() != null ? response.body().string() : "Respuesta vacía";

                runOnUiThread(() -> {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Respuesta del servidor")
                            .setMessage(res)
                            .setPositiveButton("OK", null)
                            .show();
                });
            }
        };
    }
}