<?php
require_once 'Jwt_model.php';
class APICall {
    //For Staging Credential
    const DMT_URL = 'http://staging.apiscript.in/dmt/';
    const API_USERNAME = 'YourStagingAPIUserName';
    const API_PASSWORD = 'YourStagingAPIPassword';

    //For Live Credential
    //const DMT_URL = 'https://services.apiscript.in/dmt/';
    //const self::API_USERNAME = 'YourLiveAPIUserName';
    //const self::API_PASSWORD = 'YourLiveAPIPassword';

    public function CallGetCustomerAPI($mobile_no) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/get_customer';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&gateway=GW1&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallSenderRegistrationAPI($mobile_no,$fname,$lname,$pincode) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/sender_registration';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&fname='.$fname.'&lname='.$lname.'&pin='.$pincode.'&gateway=GW1&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallSenderOTPAPI($mobile_no,$otp) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/sender_otp';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&gateway=GW1&otp='.$otp.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryRegistrationAPI($mobile_no,$fname,$lname,$account_no,$ifsc_code,$sender_profile_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/beneficiary_registration';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&gateway=GW1&fname='.$fname.'&lname='.$lname.'&account_no='.$account_no.'&ifsc_code='.$ifsc_code.'&sender_profile_id='.$sender_profile_id.'&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryDeleteAPI($mobile_no,$sender_profile_id,$beneficiary_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/beneficiary_delete';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&gateway=GW1&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryDeleteValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$otp) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/beneficiary_delete_validation';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&otp='.$otp.'&gateway=GW1&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallBeneficiaryAccountValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$account_no,$ifsc_code,$client_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/beneficiary_account_validation';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&amount=1&account_no='.$account_no.'&ifsc_code='.$ifsc_code.'&client_id='.$client_id.'&gateway=GW1&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallTransactionStatusAPI($transaction_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/get_transaction_status';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&transaction_id='.$transaction_id.'&gateway=GW1&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    public function CallFundTransferAPI($mobile_no,$sender_profile_id,$beneficiary_id,$amount,$transfer_mode='IMPS',$client_id) {
        $jwt = new Jwt_model();
        $token = $jwt->get_jwt_token();
        $url = self::DMT_URL.'/fund_transfer';
        $bodyParam = 'username='.self::API_USERNAME.'&pwd='.self::API_PASSWORD.'&mobile_no='.$mobile_no.'&sender_profile_id='.$sender_profile_id.'&beneficiary_id='.$beneficiary_id.'&amount='.$amount.'&transfer_mode='.$transfer_mode.'&client_id='.$client_id.'&gateway=GW1&token='.$token;
        return $this->ExecuteDMRAPIScriptApi($url, $bodyParam);
    }

    private function ExecuteDMRAPIScriptApi($url, $bodyParam) {
        $curl = curl_init();
        curl_setopt_array($curl, array(
            CURLOPT_URL => $url,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_HEADER => false,
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
