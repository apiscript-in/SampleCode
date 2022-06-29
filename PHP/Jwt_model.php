<?php
//CI Code
require_once 'php-jwt-master/vendor/autoload.php';
use \Firebase\JWT\JWT;
class Jwt_model {
    public function get_encode_token($token, $secret_key) {
        return JWT::encode($token, $secret_key);
    }
    public function get_decode_token($token, $secret_key) {
        return JWT::decode($token, $secret_key, array('HS256'));
    }
    public function get_jwt_token() {
        $secret_key = '2509-5b9602a1bc774-946180';//Pass Secret Key Here -> Share on Client Register Email Id
        $email_id = 'info@apiscript.in';
        $ObjDate = new DateTime();
        $current_time_stamp = $ObjDate->getTimestamp();
        $token = array("TimeStamp"=>(string)$current_time_stamp,"EmailID"=>$email_id);
        try {
           return $this->get_encode_token($token, $secret_key);
        } catch (Exception $ex) {
            return $ex->getMessage();
        }
    }
}
