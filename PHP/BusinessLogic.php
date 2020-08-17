<?php
require_once 'APICall.php';
class BusinessLogic {
    
    public function _CallGetCustomerAPI($mobile_no) {
        $api = new APICall();
        $api_result = $api->CallGetCustomerAPI($mobile_no);
        if(!$api_result['isError']){

            if($api_result['array_data']['error_code'] == '1'){
                if($api_result['array_data']['message'] == 'Sender mobile number not found'){
                        //Go for Sender Registration Page
                        echo  $api_result['array_data']['message'];
                }else{
                        echo $api_result['array_data']['message'];
                        //display error message
                }
            }

            if($api_result['array_data']['error_code'] == '0'){
                if($api_result['array_data']['sender_data']['sender_status'] == 'pending'){
                    echo 'Sender Status Pending Go For OTP Page. Current Status : '.$api_result['array_data']['sender_data']['sender_status'];
                    //in this case otp is sent to customer mobile number
                    //Go for sender otp page
                }
                if($api_result['array_data']['sender_data']['sender_status'] == 'active'){
                    echo 'Sender and Beneficiary Insert and Update Entry '.$api_result['array_data']['sender_data']['sender_status'];
                   
                    $sender_data = $api_result['array_data']['sender_data'];
                    //Insert or Update this Sender Data Based in sender_mobile_no
                    $sender_profile_id = $sender_data['sender_profile_id'];  //This sender_profile_id is used for fund transfer api
                    $sender_fname = $sender_data['sender_fname'];
                    $sender_lname = $sender_data['sender_lname'];
                    $pincode = $sender_data['sender_pincode'];
                    $sender_mobile_no = $sender_data['sender_mobile_no'];
                    $transaction_limit = $sender_data['transaction_limit'];

                    //fetch multiple beneficiary data and insert or update this beneficiary data
                    foreach($api_result['array_data']['sender_beneficiaries'] as $objBeneficiaryData){
                        $beneficiary_id = $objBeneficiaryData['beneficiary_id'];  //This beneficiary_id is used for fund transfer api
                        $beneficiary_name = $objBeneficiaryData['beneficiary_name'];
                        $bank_name = $objBeneficiaryData['bank_name'];
                        $ifsc_code = $objBeneficiaryData['ifsc_code'];
                        $account_no = $objBeneficiaryData['account_no'];
                    }

                }
            }
        }else{
            echo $api_result['array_data']['message'];
            //display error message
        }
    }

    public function _CallSenderRegistrationAPI($mobile_no,$fname,$lname,$pincode) {
        $api = new APICall();
        $api_result = $api->CallSenderRegistrationAPI($mobile_no,$fname,$lname,$pincode);
        if(!$api_result['isError']){

            if($api_result['array_data']['error_code'] == '1'){
                echo $api_result['array_data']['message'];
                //display error message
            }

            if($api_result['array_data']['error_code'] == '0'){
                echo $api_result['array_data']['message'];
                $sender_profile_id = $api_result['array_data']['sender_data']['sender_profile_id']; //store sender_profile_id in your db it is used for fund transfer api
                //redirect to otp page.
            }
        }else{
            echo $api_result['array_data']['message'];
            //display error message
        }
    }

    public function _CallSenderOTPAPI($mobile_no,$otp) {
        $api = new APICall();
        $api_result = $api->CallSenderOTPAPI($mobile_no,$otp);
        if(!$api_result['isError']){

            if($api_result['array_data']['error_code'] == '1'){
                //display error message
                echo $api_result['array_data']['message'];
            }

            if($api_result['array_data']['error_code'] == '0'){
                //sender otp register success
                echo $api_result['array_data']['message'];
            }
        }else{
            echo $api_result['array_data']['message'];
            //display error message
        }
    }

    public function _CallBeneficiaryRegistrationAPI($mobile_no,$fname,$lname,$account_no,$ifsc_code,$sender_profile_id) {
        $api = new APICall();
        $api_result = $api->CallBeneficiaryRegistrationAPI($mobile_no,$fname,$lname,$account_no,$ifsc_code,$sender_profile_id);
        if(!$api_result['isError']){

            if($api_result['array_data']['error_code'] == '1'){
                echo $api_result['array_data']['message'];
                //display error message
            }

            if($api_result['array_data']['error_code'] == '0'){
                $beneficiary_id = $api_result['array_data']['sender_beneficiary']['beneficiary_id']; //store beneficiary_id in your db it is used for fund transfer api
                //Beneficiary add successfull
                echo $api_result['array_data']['message'];
            }
        }else{
            echo $api_result['array_data']['message'];
            //display error message
        }
    }

    public function _CallBeneficiaryDeleteAPI($mobile_no,$sender_profile_id,$beneficiary_id) {
        $api = new APICall();
        $api_result = $api->CallBeneficiaryDeleteAPI($mobile_no,$sender_profile_id,$beneficiary_id);
        if(!$api_result['isError']){

            if($api_result['array_data']['error_code'] == '1'){
                //display error message
                echo $api_result['array_data']['message'];
            }

            if($api_result['array_data']['error_code'] == '0'){
                //Redirect to otp page
                echo $api_result['array_data']['message'];
            }
        }else{
            //display error message
            echo $api_result['array_data']['message'];
        }
    }

    public function _CallBeneficiaryDeleteValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$otp) {
        $api = new APICall();
        $api_result = $api->CallBeneficiaryDeleteValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$otp);
        if(!$api_result['isError']){
            if($api_result['array_data']['error_code'] == '1'){
                //display error message
                echo $api_result['array_data']['message'];
            }

            if($api_result['array_data']['error_code'] == '0'){
                //remove Beneficiary data in your db
                //Beneficiary Delete Successful
                echo $api_result['array_data']['message'];
            }
        }else{
            //display error message
            echo $api_result['array_data']['message'];
        }
    }

    public function _CallBeneficiaryAccountValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$account_no,$ifsc_code,$client_id) {
        $api = new APICall();
        $api_result = $api->CallBeneficiaryAccountValidationAPI($mobile_no,$sender_profile_id,$beneficiary_id,$account_no,$ifsc_code,$client_id);
        if(!$api_result['isError']){
            if($api_result['array_data']['error_code'] == '1'){
                //display error message
                echo $api_result['array_data']['message'];
            }

            if($api_result['array_data']['error_code'] == '0'){
                $account_name_as_per_bank = $api_result['array_data']['account_name_as_per_bank'];
                //display bank account name and store name in your db
                echo $api_result['array_data']['message'];
            }
        }else{
            //display error message
            echo $api_result['array_data']['message'];
        }
    }

    public function _CallTransactionStatusAPI($transaction_id) {
        $api = new APICall();
        $api_result = $api->CallTransactionStatusAPI($transaction_id);
        if(!$api_result['isError']){
            if($api_result['array_data']['error_code'] == '1'){
                //display error message
                echo $api_result['array_data']['message'];
            }

            if($api_result['array_data']['error_code'] == '0'){
                if($api_result['array_data']['transaction_details']['status'] == 'Success'){
                    //successful transaction
                    echo $api_result['array_data']['message'];
                }
                if($api_result['array_data']['transaction_details']['status'] == 'Pending'){
                    //wait for bank response transaction is hold at bank side due to server or network issue.
                    //call transaction status api after 30 minute to check latest status
                    echo $api_result['array_data']['message'];
                }
                if($api_result['array_data']['transaction_details']['status'] == 'Failure'){
                    //Transaction is refund to wallet
                    echo $api_result['array_data']['message'];
                }
            }
        }else{
            //display error message
            echo $api_result['array_data']['message'];
        }
    }

    public function _CallFundTransferAPI($mobile_no,$sender_profile_id,$beneficiary_id,$amount,$transfer_mode='IMPS',$client_id) {
        $api = new APICall();
        $api_result = $api->CallFundTransferAPI($$mobile_no,$sender_profile_id,$beneficiary_id,$amount,$client_id);
        if(!$api_result['isError']){
            if($api_result['array_data']['error_code'] == '1'){
                //Transaction is failure
                echo $api_result['array_data']['message'];
            }

            if($api_result['array_data']['error_code'] == '0'){
                $transaction_id = $api_result['array_data']['transaction_details']['transaction_id']; // Store transaction id in your db to know the status of transaction
                if($api_result['array_data']['transaction_details']['status'] == 'Success'){
                    //successful transaction
                    echo $api_result['array_data']['message'];
                }
                if($api_result['array_data']['transaction_details']['status'] == 'Pending'){
                    //wait for bank response transaction is hold at bank side due to server or network issue.
                    //call transaction status api after 30 minute to check latest status
                    echo $api_result['array_data']['message'];
                }
                if($api_result['array_data']['transaction_details']['status'] == 'Failure'){
                    //Transaction is refund to wallet
                    echo $api_result['array_data']['message'];
                }
            }
        }else{
            echo $api_result['array_data']['message'];
            //display error message
        }
    }

}


//Call Get Customer API Example
$objBusiness=new BusinessLogic();
$objBusiness->_CallGetCustomerAPI('EnterSenderMobileNo');