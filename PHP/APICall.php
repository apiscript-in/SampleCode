<?php
require_once 'Jwt_model.php';
class APICall {
    public $API_GATEWAY = 'GW2';
    //For Staging Credential
//    public $DMT_URL = 'http://staging.apiscript.in/dmt';
//    public $RECHARGE_URL = 'http://staging.apiscript.in/recharge';
//    public $API_USERNAME = 'YourStagingAPIUserName';
//    public $API_PASSWORD = 'YourStagingAPIPassword';
    
    //For Live Credential
    public $DMT_URL = 'https://dmt.apiscript.in';
    //public $RECHARGE_URL = 'https://services.apiscript.in/recharge';
    public $API_USERNAME = 'PWIGU138805';
    public $API_PASSWORD = '123456';

    public function CallRechargeAPI($operatorcode,$number,$amount,$client_id,$account='',$auth='',$bill_verify_id='') {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->RECHARGE_URL.'/api';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&operatorcode='.$operatorcode.'&number='.$number.'&amount='.$amount.'&client_id='.$client_id.'&account='.$account.'&auth='.$auth.'&bill_verify_id='.$bill_verify_id.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallCheckBillAPI($operatorcode,$number,$amount,$account='',$auth='') {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->RECHARGE_URL.'/check_bill';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&operatorcode='.$operatorcode.'&number='.$number.'&amount='.$amount.'&account='.$account.'&auth='.$auth.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallGetCustomerAPI($mobile_no) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/get_customer';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&gateway='.$this->API_GATEWAY.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallSenderRegistrationAPI($mobile_no,$fname,$lname,$pincode) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/sender_registration';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&fname='.$fname.'&lname='.$lname.'&pin='.$pincode.'&gateway='.$this->API_GATEWAY.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallSenderOTPAPI($mobile_no,$otp) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/sender_otp';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&gateway='.$this->API_GATEWAY.'&otp='.$otp.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryRegistrationAPI($mobile_no,$fname,$lname,$account_no,$ifsc_code,$sender_profile_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/beneficiary_registration';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&gateway='.$this->API_GATEWAY.'&fname='.$fname.'&lname='.$lname.'&account_no='.$account_no.'&ifsc_code='.$ifsc_code.'&sender_profile_id='.$sender_profile_id.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryDeleteAPI($mobile_no,$sender_profile_id,$beneficiary_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/beneficiary_delete';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&gateway='.$this->API_GATEWAY.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryDeleteValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$otp) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/beneficiary_delete_validation';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&otp='.$otp.'&gateway='.$this->API_GATEWAY.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryAccountValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$account_no,$ifsc_code,$client_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/beneficiary_account_validation';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&amount=1&account_no='.$account_no.'&ifsc_code='.$ifsc_code.'&client_id='.$client_id.'&gateway='.$this->API_GATEWAY.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallTransactionStatusAPI($transaction_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/get_transaction_status';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&transaction_id='.$transaction_id.'&gateway='.$this->API_GATEWAY.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallFundTransferAPI($mobile_no,$sender_profile_id,$beneficiary_id,$amount,$client_id,$account_no,$transfer_mode='IMPS') {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = $this->DMT_URL.'/fund_transfer';
        $bodyParam = 'username='.$this->API_USERNAME.'&pwd='.$this->API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&amount='.$amount.'&transfer_mode='.$transfer_mode.'&account_no='.$account_no.'&client_id='.$client_id.'&gateway='.$this->API_GATEWAY.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    private function ExecuteDMRAPIScriptApi($url, $bodyParam) {
        $curl = curl_init();
        curl_setopt_array($curl, array(
            CURLOPT_URL => $url,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_HEADER => false,
            CURLOPT_HTTPHEADER, array('Content-Type: application/x-www-form-urlencoded;charset=UTF-8'),
            CURLOPT_SSL_VERIFYPEER => false,
            CURLOPT_TIMEOUT => 90,
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_POSTFIELDS => $bodyParam,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'POST'
        ));
        $response = curl_exec($curl);
        $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
        if ($httpCode != 200) {
            $error_message = curl_error($curl);
            curl_close($curl);
            return array('isError' => true, 'response' => $error_message);
        } else {
            curl_close($curl);
            return array('isError' => false, 'response' => $response,'array_data'=>json_decode($response, true));
        }
    }
}
