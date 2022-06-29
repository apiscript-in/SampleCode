/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiscript.jwtjava;

import com.apiscript.jwtjava.dmtclasses.DmtAPI;
import com.apiscript.jwtjava.jwtandapidemo;

/**
 *
 * @author Api script
 */
public class BusinessLogic {

    public void _CallGetCustomerAPI(String mobile_no) {
        DmtAPI.GetCustomerResponse objResponse = jwtandapidemo.CallGetCustomerAPI(mobile_no);
        if (objResponse.getError_code().equals("1")) {
            if (objResponse.getMessage().equals("Sender mobile number not found")) {
                //Go for Sender Registration Page
                //print objResponse.getMessage();
            } else {
                //print objResponse.getMessage();
                //display error message
            }
        }

        if (objResponse.getError_code().equals("0")) {
            if (objResponse.getSender_data().getSender_status().equals("pending")) {
                //in this case otp is sent to customer mobile number
                //Go for sender otp page
                //print "Sender Status Pending Go For OTP Page. Current Status : " + objResponse.getSender_data().getSender_status();
            }
            if (objResponse.getSender_data().getSender_status().equals("active")) {
                //Sender and Beneficiary Insert and Update Entry 
                String sender_profile_id = objResponse.getSender_data().getSender_profile_id(); //This sender_profile_id is used for fund transfer api
                String sender_fname = objResponse.getSender_data().getSender_fname();
                String sender_lname = objResponse.getSender_data().getSender_lname();
                String sender_mobile_no = objResponse.getSender_data().getSender_mobile_no();
                String sender_pincode = objResponse.getSender_data().getSender_pincode();
                int transaction_limit = objResponse.getSender_data().getTransaction_limit();

                //fetch multiple beneficiary data and insert or update this beneficiary data
                for (int i = 0; i < objResponse.getSender_beneficiaries().size(); i++) {
                    String beneficiary_id = objResponse.getSender_beneficiaries().get(i).getBeneficiary_id();  //This beneficiary_id is used for fund transfer api
                    String beneficiary_name = objResponse.getSender_beneficiaries().get(i).getBeneficiary_name();
                    String bank_name = objResponse.getSender_beneficiaries().get(i).getBank_name();
                    String ifsc_code = objResponse.getSender_beneficiaries().get(i).getIfsc_code();
                    String account_no = objResponse.getSender_beneficiaries().get(i).getAccount_no();
                }
            }
        }

    }

    public void _CallSenderRegistrationAPI(String mobile_no, String fname, String lname, String pincode) {
        DmtAPI.GetSenderRegistrationResponse objResponse = jwtandapidemo.CallSenderRegistrationAPI(mobile_no, fname, lname, pincode);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            //print objResponse.message;
            String sender_profile_id = objResponse.getSender_data().getSender_profile_id();//store sender_profile_id in your db it is used for fund transfer api and beneficiary registration api
            //redirect to otp page.
        }
    }

    public void _CallSenderOTPAPI(String mobile_no, String otp) {
        DmtAPI.GetCommonResponse objResponse = jwtandapidemo.CallSenderOTPAPI(mobile_no, otp);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            //print objResponse.getMessage();
            //sender otp register success
        }
    }

    public void _CallBeneficiaryRegistrationAPI(String mobile_no, String fname, String lname, String account_no, String ifsc_code, String sender_profile_id) {
        DmtAPI.GetBeneficiaryRegistrationResponse objResponse = jwtandapidemo.CallBeneficiaryRegistrationAPI(mobile_no, fname, lname, account_no, ifsc_code, sender_profile_id);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            String beneficiary_id = objResponse.getSender_beneficiary().get(0).getBeneficiary_id(); //store beneficiary_id in your db it is used for fund transfer api
            //sender otp register success
        }
    }

    public void _CallBeneficiaryDeleteAPI(String mobile_no, String sender_profile_id, String beneficiary_id) {
        DmtAPI.GetCommonResponse objResponse = jwtandapidemo.CallBeneficiaryDeleteAPI(mobile_no, sender_profile_id, beneficiary_id);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            //print objResponse.getMessage();
            //Redirect to otp page
        }
    }
    
    public void _CallBeneficiaryDeleteValidationAPI(String mobile_no, String sender_profile_id, String beneficiary_id, String otp) {
        DmtAPI.GetCommonResponse objResponse = jwtandapidemo.CallBeneficiaryDeleteValidationAPI(mobile_no, sender_profile_id, beneficiary_id, otp);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            //print objResponse.getMessage();
            //remove Beneficiary data in your db
        }
    }
    public void _CallBeneficiaryAccountValidationAPI(String mobile_no, String sender_profile_id, String beneficiary_id, String account_no, String ifsc_code, String client_id)
    {
        DmtAPI.GetBeneficiaryAccountValidationResponse objResponse = jwtandapidemo.CallBeneficiaryAccountValidationAPI(mobile_no, sender_profile_id, beneficiary_id, account_no, ifsc_code, client_id);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            //print objResponse.getMessage();
             //display bank account name and store name in your db
            String account_name_as_per_bank = objResponse.getAccount_name_as_per_bank();
        }
    }
    public void _CallTransactionStatusAPI(String transaction_id)
    {
        DmtAPI.GetTransactionStatusResponse objResponse = jwtandapidemo.CallTransactionStatusAPI(transaction_id);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            if (objResponse.getTransaction_details().getStatus().equals("Success"))
            {
                //successful transaction
            }
            if (objResponse.getTransaction_details().getStatus().equals("Pending"))
            {
                //wait for bank response transaction is hold at bank side due to server or network issue.
                //call transaction status api after 30 minute to check latest status
            }
            if (objResponse.getTransaction_details().getStatus().equals("Failure"))
            {
                //Transaction is refund to wallet
            }
        }
    }
     public void _CallTransactionStatusAPI(String mobile_no, String sender_profile_id, String beneficiary_id, String account_no, int amount, String transfer_mode,long client_id)
    {
        DmtAPI.GetTransactionStatusResponse objResponse = jwtandapidemo.CallFundTransferAPI(mobile_no,sender_profile_id,beneficiary_id,amount,client_id,account_no,transfer_mode);
        if (objResponse.getError_code().equals("1")) {
            //print objResponse.getMessage();
            //display error message
        }
        if (objResponse.getError_code().equals("0")) {
            String transaction_id = objResponse.getTransaction_details().getTransaction_id(); // Store transaction id in your db to know the status of transaction
            if (objResponse.getTransaction_details().getStatus().equals("Success"))
            {
                //successful transaction
            }
            if (objResponse.getTransaction_details().getStatus().equals("Pending"))
            {
                //wait for bank response transaction is hold at bank side due to server or network issue.
                //call transaction status api after 30 minute to check latest status
            }
            if (objResponse.getTransaction_details().getStatus().equals("Failure"))
            {
                //Transaction is refund to wallet
            }
        }
    }
}
