<?php

require 'src/routes.php';
require 'src/controller.php';

// get the HTTP method, path and body of the request
$method = $_SERVER['REQUEST_METHOD'];

// the requested URL
$request = str_replace(str_replace($_SERVER['DOCUMENT_ROOT'], "", substr($_SERVER['SCRIPT_FILENAME'], 0, (strrpos($_SERVER['SCRIPT_FILENAME'], '/') + 1))), "", $_SERVER['REQUEST_URI']);
if (strrpos($request, '?')) {
    $request = substr($request, 0, strrpos($request, '?'));
}

// connect to the mysql database
$link = @mysqli_connect('localhost', 'root', 'dsilva', 'lpoo') or die(mysqli_connect_error());
;
mysqli_set_charset($link, 'utf8');

if (!isset($routes)) {
    throw new Exception('A $routes variable is required!');
}

foreach ($routes as $route) {
    if ($route[0] === $method && $route[1] === $request) {
        $func = $route[2];
        $c    = new Controller($link);
        if (!method_exists($c, $func)) {
            continue;
        }
        
        $method = new ReflectionMethod('Controller', $func);
        $parN   = $method->getNumberOfParameters(); // number of parameters
        
        if (empty($_GET) && empty($_POST) && $parN == 1) {
            http_response_code($c->$func($request));
            
            die();
        }
        
        if (empty($_GET) && !empty($_POST) && $parN == 2) {
            http_response_code($c->$func($request, $_POST));
            die();
        }
        
        if (!empty($_GET) && !empty($_POST) && $parN == 3) {
            http_response_code($c->$func($request, $_GET, $_POST));
            die();
        }
        
        if (!empty($_GET) && empty($_POST) && $parN == 2) {
            http_response_code($c->$func($request, $_GET));
            die();
        }
        
    }
}

http_response_code(404);
echo 'not found.';

// close mysql connection
mysqli_close($link); 