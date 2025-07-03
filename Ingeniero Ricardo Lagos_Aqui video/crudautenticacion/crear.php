<?php
require_once __DIR__ . '/config.php';
require_once __DIR__ . '/auth_apikey.php';

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(['error' => 'Método no permitido']);
    exit;
}

$data = json_decode(file_get_contents('php://input'), true);

if (empty($data['nombre']) || !isset($data['precio'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Faltan campos obligatorios']);
    exit;
}

try {
    $stmt = $pdo->prepare("INSERT INTO productos (nombre, descripcion, precio) VALUES (?, ?, ?)");
    $stmt->execute([
        $data['nombre'],
        $data['descripcion'] ?? null,
        $data['precio']
    ]);

    echo json_encode(['message' => 'Producto creado', 'id' => $pdo->lastInsertId()]);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => 'Error al crear producto', 'message' => $e->getMessage()]);
}