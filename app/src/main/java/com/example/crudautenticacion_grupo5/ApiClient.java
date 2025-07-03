package com.example.crudautenticacion_grupo5;

import okhttp3.*;

import java.io.IOException;

public class ApiClient {

    private static final String BASE_URL = "http://10.0.2.2/crudautenticacion/";
    private static final String API_KEY = "grupo5";

    private static final OkHttpClient client = new OkHttpClient();

    // Listar productos (GET)
    public static void listarProductos(Callback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL + "listar.php")
                .addHeader("x-api-key", API_KEY)
                .build();

        client.newCall(request).enqueue(callback);
    }

    // Crear producto (POST)
    public static void crearProducto(String jsonBody, Callback callback) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonBody);

        Request request = new Request.Builder()
                .url(BASE_URL + "crear.php")
                .post(body)
                .addHeader("x-api-key", API_KEY)
                .build();

        client.newCall(request).enqueue(callback);
    }

    // Actualizar producto (PUT)
    public static void actualizarProducto(String jsonBody, Callback callback) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonBody);

        Request request = new Request.Builder()
                .url(BASE_URL + "actualizar.php")
                .put(body)
                .addHeader("x-api-key", API_KEY)
                .build();

        client.newCall(request).enqueue(callback);
    }

    // Eliminar producto (DELETE)
    public static void eliminarProducto(int idProducto, Callback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL + "eliminar.php?id=" + idProducto)
                .delete()
                .addHeader("x-api-key", API_KEY)
                .build();

        client.newCall(request).enqueue(callback);
    }
}