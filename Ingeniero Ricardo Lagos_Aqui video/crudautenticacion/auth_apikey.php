<?php
// auth_apikey.php

$headers = getallheaders();

// Pasamos todas las llaves a minúsculas para evitar problemas
$headers_lower = array_change_key_case($headers, CASE_LOWER);

if (
    !isset($headers_lower['x-api-key']) ||
    $headers_lower['x-api-key'] !== 'grupo5'  // La clave debe ser exactamente "grupo5"
) {
    http_response_code(401);
    echo json_encode(['error' => 'API Key inválida']);
    exit;
}