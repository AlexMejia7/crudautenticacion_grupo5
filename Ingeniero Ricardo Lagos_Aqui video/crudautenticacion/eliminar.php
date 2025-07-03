<?php
require_once 'config.php';
require_once 'auth_apikey.php'; // ✅ validar por header

// ✅ Solo método DELETE
if ($_SERVER['REQUEST_METHOD'] !== 'DELETE') {
    http_response_code(405);
    echo json_encode(['error' => 'Método no permitido']);
    exit;
}

// ✅ Obtener el ID desde la URL: eliminar.php?id=#
$id = isset($_GET['id']) ? intval($_GET['id']) : 0;

if ($id <= 0) {
    http_response_code(400);
    echo json_encode(['error' => 'ID inválido o no proporcionado']);
    exit;
}

try {
    $stmt = $pdo->prepare("DELETE FROM productos WHERE id = ?");
    $stmt->execute([$id]);

    if ($stmt->rowCount()) {
        echo json_encode(['message' => 'Producto eliminado']);
    } else {
        http_response_code(404);
        echo json_encode(['error' => 'Producto no encontrado']);
    }

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => 'Error al eliminar producto', 'message' => $e->getMessage()]);
}