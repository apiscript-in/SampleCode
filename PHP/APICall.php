<?php

class APICall {

    public function CallGetCustomerAPI() {
        $url = 'http://staging.apiscript.in/get_customer';
        $bodyParam = 'username=YourUsername&pwd=YourPwd&mobile_no=CustomerMobile&gateway=GW1&token=Token';
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
            return array('isError' => false, 'response' => $response);
        }
    }

}
