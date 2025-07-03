package com.example.crudautenticacion_grupo5;

import okhttp3.*;

public class ApiClient {

    private static String baseUrl = "http://192.168.212.202/crudautenticacion/";
    private static final String API_KEY = "grupo5";

    private static final OkHttpClient client = new OkHttpClient();

    // Método para cambiar la IP base en tiempo de ejecución
    public static void setIpBase(String ip) {
        baseUrl = "http://" + ip + "/crudautenticacion/";
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static void listarProductos(Callback callback) {
        Request request = new Request.Builder()
                .url(baseUrl + "listar.php")
                .addHeader("x-api-key", API_KEY)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void crearProducto(String jsonBody, Callback callback) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "crear.php")
                .post(body)
                .addHeader("x-api-key", API_KEY)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void actualizarProducto(String jsonBody, Callback callback) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "actualizar.php")
                .put(body)
                .addHeader("x-api-key", API_KEY)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void eliminarProducto(int idProducto, Callback callback) {
        Request request = new Request.Builder()
                .url(baseUrl + "eliminar.php?id=" + idProducto)
                .delete()
                .addHeader("x-api-key", API_KEY)
                .build();
        client.newCall(request).enqueue(callback);
    }
}