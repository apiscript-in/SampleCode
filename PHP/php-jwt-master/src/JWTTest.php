<?php
require_once '../vendor/autoload.php';
use \Firebase\JWT\JWT;

/*$token = array (
  'sub' => '1234567890',
  'name' => 'John Doe',
  'admin' => true,
  'jti' => 'ea150242-7c05-437e-971c-b3fd9a088da4',
  'iat' => 1521286730,
  'exp' => 1521290330,
);*/

$token = array('actionName'=>'getBalance',
    'parameters'=>[array('merchantUserId'=>"71")]);

$jwt = JWT::encode($token, "wr2lomUOZ3");

//print_r($jwt);

$t = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdmFpbEJhbGFuY2UiOiIzNTAwMi4wNDc3ODAwMDUzMiJ9.0xaSC3v-zb7n8GhEDg_50v_LAxE9zBX1Tz-Qh5Ef5Jo';

$decoded = JWT::decode($t, 'wr2lomUOZ3', array('HS256'));

print_r($decoded->availBalance);

//echo $decoded;