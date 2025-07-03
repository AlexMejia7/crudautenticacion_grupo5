<?php
require_once 'config.php';
require_once 'auth_apikey.php'; // ✅ validar por header

// ✅ Verificamos que sea método PUT
if ($_SERVER['REQUEST_METHOD'] !== 'PUT') {
    http_response_code(405);
    echo json_encode(['error' => 'Método no permitido']);
    exit;
}

// ✅ Leemos y validamos el JSON recibido
$data = json_decode(file_get_contents('php://input'), true);

if (empty($data['id']) || empty($data['nombre']) || !isset($data['precio'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Faltan campos obligatorios (id, nombre, precio)']);
    exit;
}

try {
    $stmt = $pdo->prepare("UPDATE productos SET nombre = ?, descripcion = ?, precio = ? WHERE id = ?");
    $stmt->execute([
        $data['nombre'],
        $data['descripcion'] ?? null,
        $data['precio'],
        $data['id']
    ]);

    if ($stmt->rowCount()) {
        echo json_encode(['message' => 'Producto actualizado correctamente']);
    } else {
        http_response_code(404);
        echo json_encode(['error' => 'Producto no encontrado o sin cambios']);
    }

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode([
        'error' => 'Error al actualizar producto',
        'message' => $e->getMessage()
    ]);
}