using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using JWT;
using JWT.Algorithms;
using JWT.Serializers;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
namespace apiscript.dtm.api{
public class APICallAndTokenGenerate
{
    public const string API_GATEWAY = "GW2";
    //For Staging Credential
    public const string DMT_URL = "http://staging.apiscript.in/dmt";
    public const string API_USERNAME = "YourStagingAPIUserName";
    public const string API_PASSWORD = "YourStagingAPIPassword";
    //For Live Credential
    //public const string DMT_URL = "https://dmt.apiscript.in";
    //public const string API_USERNAME = "YourLiveAPIUserName";
    //public const string API_PASSWORD = "YourLiveAPIPassword";

    public string getToken()
    {
        string email_id = "Your Register EmailID";
        string secret_key = "Your Secret Key";
        try
        {
            DateTimeOffset now = (DateTimeOffset)DateTime.UtcNow;
            var my_jsondata = new Dictionary<string, string>
            {
                { "TimeStamp", now.ToUnixTimeSeconds().ToString() },
                { "EmailID",  email_id }
            }; 

            //Tranform it to Json object
                string json_data = JsonConvert.SerializeObject(my_jsondata);
            JObject json_object = JObject.Parse(json_data);

            var algorithm = new HMACSHA256Algorithm();
            var urlEncoder = new JWT.JwtBase64UrlEncoder();
            var serializer = new JsonNetSerializer();
            var encoder = new JwtEncoder(algorithm, serializer, urlEncoder);

            string token = encoder.Encode(json_object, secret_key);
            return token;
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    }

    #region Classes for DMT Response
    public class GetCustomerResponse
    {
        public string message { get; set; }
        public Sender_Data sender_data { get; set; }
        public Sender_Beneficiaries[] sender_beneficiaries { get; set; }
        public string error_code { get; set; }
    }

    public class Sender_Data
    {
        public string sender_profile_id { get; set; }
        public string sender_fname { get; set; }
        public string sender_lname { get; set; }
        public string sender_pincode { get; set; }
        public string sender_mobile_no { get; set; }
        public int transaction_limit { get; set; }
        public string sender_status { get; set; }
    }

    public class Sender_Beneficiaries
    {
        public string beneficiary_id { get; set; }
        public string beneficiary_name { get; set; }
        public string bank_name { get; set; }
        public string ifsc_code { get; set; }
        public string account_no { get; set; }
        public string imps_status { get; set; }
        public string neft_status { get; set; }
        public string is_verify { get; set; }
        public string beneficiary_status { get; set; }
    }

    public class GetSenderRegistrationResponse
    {
        public string message { get; set; }
        public Sender_Data sender_data { get; set; }
        public string error_code { get; set; }
    }

    public class GetBeneficiaryRegistrationResponse
    {
        public string message { get; set; }
        public Sender_Data sender_data { get; set; }
        public Sender_Beneficiaries[] sender_beneficiary { get; set; }
        public string error_code { get; set; }
    }

    public class GetBeneficiaryAccountValidationResponse
    {
        public string message { get; set; }
        public string account_name_as_per_bank { get; set; }
        public string bank_ref_no { get; set; }
        public string error_code { get; set; }
    }

    public class GetTransactionStatusResponse
    {
        public string message { get; set; }
        public Transaction_Details transaction_details { get; set; }
        public string error_code { get; set; }
    }

    public class Transaction_Details
    {
        public string transaction_id { get; set; }
        public string amount { get; set; }
        public string service_charge { get; set; }
        public string bank_ref_no { get; set; }
        public string status { get; set; }
        public string client_id { get; set; }
    }

    public class GetCommonResponse
    {
        public string message { get; set; }
        public string error_code { get; set; }
    }

    #endregion

    #region Calling DMT API

    public GetCustomerResponse CallGetCustomerAPI(string mobile_no)
    {
        string token = getToken();
        string url = DMT_URL+"/get_customer";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&gateway="+API_GATEWAY+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetCustomerResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetCustomerResponse>(API_response);
        return objResponse;
    }

    public GetSenderRegistrationResponse CallSenderRegistrationAPI(string mobile_no, string fname, string lname, string pincode)
    {
        string token = getToken();
        string url = DMT_URL+"/sender_registration";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&fname=" + fname + "&lname=" + lname + "&pin=" + pincode + "&gateway="+API_GATEWAY+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetSenderRegistrationResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetSenderRegistrationResponse>(API_response);
        return objResponse;
    }

    public GetCommonResponse CallSenderOTPAPI(string mobile_no, string otp)
    {
        string token = getToken();
        string url = DMT_URL+"/sender_otp";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&otp=" + otp + "&gateway="+API_GATEWAY+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetCommonResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetCommonResponse>(API_response);
        return objResponse;
    }

    public GetBeneficiaryRegistrationResponse CallBeneficiaryRegistrationAPI(string mobile_no, string fname, string lname, string account_no, string ifsc_code, string sender_profile_id)
    {
        string token = getToken();
        string url = DMT_URL+"/beneficiary_registration";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&gateway="+API_GATEWAY+"&fname=" + fname + "&lname=" + lname + "&account_no=" + account_no + "&ifsc_code=" + ifsc_code + "&sender_profile_id=" + sender_profile_id + "&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetBeneficiaryRegistrationResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetBeneficiaryRegistrationResponse>(API_response);
        return objResponse;
    }

    public GetCommonResponse CallBeneficiaryDeleteAPI(string mobile_no, string sender_profile_id, string beneficiary_id)
    {
        string token = getToken();
        string url = DMT_URL+"/beneficiary_delete";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&gateway="+API_GATEWAY+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetCommonResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetCommonResponse>(API_response);
        return objResponse;
    }

    public GetCommonResponse CallBeneficiaryDeleteValidationAPI(string mobile_no, string sender_profile_id, string beneficiary_id, string otp)
    {
        string token = getToken();
        string url = DMT_URL+"/beneficiary_delete_validation";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&otp=" + otp + "&gateway="+API_GATEWAY+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetCommonResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetCommonResponse>(API_response);
        return objResponse;
    }

    public GetBeneficiaryAccountValidationResponse CallBeneficiaryAccountValidationAPI(string mobile_no, string sender_profile_id, string beneficiary_id, string account_no, string ifsc_code, string client_id)
    {
        string token = getToken();
        string url = DMT_URL+"/beneficiary_account_validation";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&amount=1&account_no=" + account_no + "&ifsc_code=" + ifsc_code + "&client_id=" + client_id + "&gateway="+API_GATEWAY+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetBeneficiaryAccountValidationResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetBeneficiaryAccountValidationResponse>(API_response);
        return objResponse;
    }

    public GetTransactionStatusResponse CallTransactionStatusAPI(string transaction_id)
    {
        string token = getToken();
        string url = DMT_URL+"/get_transaction_status";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&transaction_id=" + transaction_id + "&gateway="+API_GATEWAY+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetTransactionStatusResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetTransactionStatusResponse>(API_response);
        return objResponse;
    }

    public GetTransactionStatusResponse CallFundTransferAPI(string mobile_no, string sender_profile_id, string beneficiary_id, int amount, long client_id,string account_no,string transfer_mode = "IMPS")
    {
        string token = getToken();
        string url = DMT_URL+"/fund_transfer";
        string bodyParam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&amount=" + amount + "&account_no=" + account_no + "&transfer_mode=" + transfer_mode + "&gateway="+API_GATEWAY+"&client_id="+client_id+"&token=" + token;
        string API_response = ExecuteDMRAPIScriptApi(url, bodyParam);
        GetTransactionStatusResponse objResponse = Newtonsoft.Json.JsonConvert.DeserializeObject<GetTransactionStatusResponse>(API_response);
        return objResponse;
    }

    #endregion

    public string ExecuteDMRAPIScriptApi(string url, string bodyParam)
    {
        try
        {
            string token = getToken();
            var request = (HttpWebRequest)WebRequest.Create(url);
            var data = Encoding.ASCII.GetBytes(bodyParam);

            request.Method = "POST";
            request.ContentType = "application/x-www-form-urlencoded";
            request.ContentLength = data.Length;

            using (var stream = request.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }

            var response = (HttpWebResponse)request.GetResponse();

            var responseString = new StreamReader(response.GetResponseStream()).ReadToEnd();
            return responseString.ToString();

        }
        catch (Exception ex)
        {
            return System.Text.Json.JsonSerializer.Serialize(new GetCommonResponse() { error_code = "1", message = ex.Message });
        }
    }
}
}
