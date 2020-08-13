using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using JWT;
using JWT.Algorithms;
using JWT.Serializers;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using apiscript.dmt.api;
public class BusinessLogic
{
    public void _CallGetCustomerAPI(string mobile_no)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetCustomerResponse objResponse = objAPI.CallGetCustomerAPI(mobile_no);
        if (objResponse.error_code.Equals("1"))
        {
            if (objResponse.message.Equals("Sender mobile number not found"))
            {
                //Go for Sender Registration Page
                //print objResponse.message;
            }
            else
            {
                //printobjResponse.message;
                //display error message
            }
        }
        if (objResponse.error_code.Equals("0"))
        {
            if (objResponse.sender_data.sender_status.Equals("pending"))
            {
                //in this case otp is sent to customer mobile number
                //Go for sender otp page
                //print "Sender Status Pending Go For OTP Page. Current Status : " + objResponse.sender_data.sender_status;
            }
            if (objResponse.sender_data.sender_status.Equals("active"))
            {
                //Sender and Beneficiary Insert and Update Entry 
                string sender_profile_id = objResponse.sender_data.sender_profile_id; //This sender_profile_id is used for fund transfer api
                string sender_fname = objResponse.sender_data.sender_fname;
                string sender_lname = objResponse.sender_data.sender_lname;
                string sender_mobile_no = objResponse.sender_data.sender_mobile_no;
                string sender_pincode = objResponse.sender_data.sender_pincode;
                int transaction_limit = objResponse.sender_data.transaction_limit;

                //fetch multiple beneficiary data and insert or update this beneficiary data
                for (int i = 0; i < objResponse.sender_beneficiaries.Length; i++)
                {
                    string beneficiary_id = objResponse.sender_beneficiaries[i].beneficiary_id;  //This beneficiary_id is used for fund transfer api
                    string beneficiary_name = objResponse.sender_beneficiaries[i].beneficiary_name;
                    string bank_name = objResponse.sender_beneficiaries[i].bank_name;
                    string ifsc_code = objResponse.sender_beneficiaries[i].ifsc_code;
                    string account_no = objResponse.sender_beneficiaries[i].account_no;
                }
            }
        }
    }

    public void _CallSenderRegistrationAPI(string mobile_no, string fname, string lname, string pincode)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetSenderRegistrationResponse objResponse = objAPI.CallSenderRegistrationAPI(mobile_no, fname, lname, pincode);
        if (objResponse.error_code.Equals("1"))
        {
            //print objResponse.message;
            //display error message
        }
        if (objResponse.error_code.Equals("0"))
        {
            //print objResponse.message;
            string sender_profile_id = objResponse.sender_data.sender_profile_id;//store sender_profile_id in your db it is used for fund transfer api and beneficiary registration api
            //redirect to otp page.
        }
    }

    public void _CallSenderOTPAPI(string mobile_no, string otp)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetCommonResponse objResponse = objAPI.CallSenderOTPAPI(mobile_no, otp);
        if (objResponse.error_code.Equals("1"))
        {
            //print objResponse.message;
            //display error message
        }
        if (objResponse.error_code.Equals("0"))
        {
            //print objResponse.message;
            //sender otp register success
        }
    }

    public void _CallBeneficiaryRegistrationAPI(string mobile_no, string fname, string lname, string account_no, string ifsc_code, string sender_profile_id)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetBeneficiaryRegistrationResponse objResponse = objAPI.CallBeneficiaryRegistrationAPI(mobile_no, fname, lname, account_no, ifsc_code, sender_profile_id);
        if (objResponse.error_code.Equals("1"))
        {
            //print objResponse.message;
            //display error message
        }
        if (objResponse.error_code.Equals("0"))
        {
            string beneficiary_id = objResponse.sender_beneficiary[0].beneficiary_id; //store beneficiary_id in your db it is used for fund transfer api
            //sender otp register success
        }
    }

    public void _CallBeneficiaryDeleteAPI(string mobile_no, string sender_profile_id, string beneficiary_id)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetCommonResponse objResponse = objAPI.CallBeneficiaryDeleteAPI(mobile_no, sender_profile_id, beneficiary_id);
        if (objResponse.error_code.Equals("1"))
        {
            //print objResponse.message;
            //display error message
        }
        if (objResponse.error_code.Equals("0"))
        {
            //print objResponse.message;
            //Redirect to otp page
        }
    }

    public void _CallBeneficiaryDeleteValidationAPI(string mobile_no, string sender_profile_id, string beneficiary_id, string otp)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetCommonResponse objResponse = objAPI.CallBeneficiaryDeleteValidationAPI(mobile_no, sender_profile_id, beneficiary_id, otp);
        if (objResponse.error_code.Equals("1"))
        {
            //print objResponse.message;
            //display error message
        }
        if (objResponse.error_code.Equals("0"))
        {
            //print objResponse.message;
            //remove Beneficiary data in your db
        }
    }

    public void _CallBeneficiaryAccountValidationAPI(string mobile_no, string sender_profile_id, string beneficiary_id, string account_no, string ifsc_code, string client_id)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetBeneficiaryAccountValidationResponse objResponse = objAPI.CallBeneficiaryAccountValidationAPI(mobile_no, sender_profile_id, beneficiary_id, account_no, ifsc_code, client_id);
        if (objResponse.error_code.Equals("1"))
        {
            //print objResponse.message;
            //display error message
        }
        if (objResponse.error_code.Equals("0"))
        {
            //print objResponse.message;
            //display bank account name and store name in your db
            string account_name_as_per_bank = objResponse.account_name_as_per_bank;
        }
    }
    public void _CallTransactionStatusAPI(string transaction_id)
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetTransactionStatusResponse objResponse = objAPI.CallTransactionStatusAPI(transaction_id);
        if (objResponse.error_code.Equals("1"))
        {
            //print objResponse.message;
            //display error message
        }
        if (objResponse.error_code.Equals("0"))
        {
            if (objResponse.transaction_details.status.Equals("Success"))
            {
                //successful transaction
            }
            if (objResponse.transaction_details.status.Equals("Pending"))
            {
                //wait for bank response transaction is hold at bank side due to server or network issue.
                //call transaction status api after 30 minute to check latest status
            }
            if (objResponse.transaction_details.status.Equals("Failure"))
            {
                //Transaction is refund to wallet
            }

        }
    }
    public void _CallFundTransferAPI(string mobile_no, string sender_profile_id, string beneficiary_id, string account_no, int amount, string transfer_mode="IMPS")
    {
        APICallAndTokenGenerate objAPI = new APICallAndTokenGenerate();
        APICallAndTokenGenerate.GetTransactionStatusResponse objResponse = objAPI.CallFundTransferAPI(mobile_no,sender_profile_id,beneficiary_id,amount,transfer_mode);
        if (objResponse.error_code.Equals("1"))
        {
            //Transaction is failure
            //print objResponse.message;
        }
        if (objResponse.error_code.Equals("0"))
        {
            string transaction_id = objResponse.transaction_details.transaction_id; // Store transaction id in your db to know the status of transaction
            if (objResponse.transaction_details.status.Equals("Success"))
            {
                //successful transaction
            }
            if (objResponse.transaction_details.status.Equals("Pending"))
            {
                //wait for bank response transaction is hold at bank side due to server or network issue.
                //call transaction status api after 30 minute to check latest status
            }
            if (objResponse.transaction_details.status.Equals("Failure"))
            {
                //Transaction is refund to wallet
            }

        }
    }
}