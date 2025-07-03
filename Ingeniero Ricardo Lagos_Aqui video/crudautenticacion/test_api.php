<?php
$opts = [
  "http" => [
    "method" => "GET",
    "header" => "x-api-key:grupo5\r\n"
  ]
];

$context = stream_context_create($opts);

$result = file_get_contents("http://localhost/crudautenticacion/listar.php", false, $context);

echo $result;