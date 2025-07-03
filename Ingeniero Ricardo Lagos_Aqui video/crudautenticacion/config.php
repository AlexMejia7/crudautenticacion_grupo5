<?php
// config.php
header("Content-Type: application/json; charset=UTF-8");

define('DB_HOST', 'localhost');
define('DB_NAME', 'crudautenticacion_grupo5');  // Base de datos correcta
define('DB_USER', 'root');
define('DB_PASS', ''); // Cambia si tienes contraseña

try {
    $pdo = new PDO(
        "mysql:host=" . DB_HOST . ";dbname=" . DB_NAME . ";charset=utf8mb4",
        DB_USER,
        DB_PASS,
        [
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC
        ]
    );
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => 'Conexión fallida: ' . $e->getMessage()]);
    exit;
}