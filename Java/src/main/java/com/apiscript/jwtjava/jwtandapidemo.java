package com.apiscript.jwtjava;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.net.HttpURLConnection;
import com.apiscript.jwtjava.dmtclasses.*;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
    Our simple static class that demonstrates how to create JWTs Token and API Calls.
 */
class jwtandapidemo {

    //For Staging Credential
    private static String DMT_URL = "http://staging.apiscript.in/dmt/";
    private static String API_USERNAME = "YourStagingAPIUserName"; //Provide By APIScript Developer
    private static String API_PASSWORD = "YourStagingAPIPassword"; //Provide By APIScript Developer

    //For Live Credential
    //private String DMT_URL="https://services.apiscript.in/dmt/";
    //private String API_USERNAME="YourLiveAPIUserName";
    //private String API_PASSWORD="YourLiveAPIPassword";
    //Sample method to construct a JWT
    public static String getToken() {
        String SECRET_KEY = "Enter Secret Key"; //Provide By APIScript Developer
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //Payload 
        Map<String, Object> objMap = new HashMap<>();
        objMap.put("TimeStamp", Instant.now().getEpochSecond());
        objMap.put("EmailID", "Enter Your Email ID");//Put Register Email ID Here

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setClaims(objMap)
                .signWith(signatureAlgorithm, SECRET_KEY.getBytes());
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static DmtAPI.GetCustomerResponse CallGetCustomerAPI(String mobile_no) {
        DmtAPI.GetCustomerResponse objResponse=new DmtAPI.GetCustomerResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/get_customer";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetCustomerResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetSenderRegistrationResponse CallSenderRegistrationAPI(String mobile_no,String fname,String lname,String pincode) {
        DmtAPI.GetSenderRegistrationResponse objResponse=new DmtAPI.GetSenderRegistrationResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/sender_registration";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&fname="+fname+"&lname="+lname+"&pin="+pincode+"&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetSenderRegistrationResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetCommonResponse CallSenderOTPAPI(String mobile_no,String otp) {
        DmtAPI.GetCommonResponse objResponse=new DmtAPI.GetCommonResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/sender_otp";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&otp=" + otp + "&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetCommonResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetBeneficiaryRegistrationResponse CallBeneficiaryRegistrationAPI(String mobile_no,String fname, String lname, String account_no, String ifsc_code, String sender_profile_id) {
        DmtAPI.GetBeneficiaryRegistrationResponse objResponse=new DmtAPI.GetBeneficiaryRegistrationResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/beneficiary_registration";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&gateway=GW1&fname=" + fname + "&lname=" + lname + "&account_no=" + account_no + "&ifsc_code=" + ifsc_code + "&sender_profile_id=" + sender_profile_id + "&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetBeneficiaryRegistrationResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetCommonResponse CallBeneficiaryDeleteAPI(String mobile_no,String sender_profile_id, String beneficiary_id) {
        DmtAPI.GetCommonResponse objResponse=new DmtAPI.GetCommonResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/beneficiary_delete";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetCommonResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetCommonResponse CallBeneficiaryDeleteValidationAPI(String mobile_no,String sender_profile_id, String beneficiary_id,String otp) {
        DmtAPI.GetCommonResponse objResponse=new DmtAPI.GetCommonResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/beneficiary_delete_validation";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&otp=" + otp + "&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetCommonResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetBeneficiaryAccountValidationResponse CallBeneficiaryAccountValidationAPI(String mobile_no, String sender_profile_id, String beneficiary_id, String account_no, String ifsc_code, String client_id) {
        DmtAPI.GetBeneficiaryAccountValidationResponse objResponse=new DmtAPI.GetBeneficiaryAccountValidationResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/beneficiary_account_validation";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&amount=1&account_no=" + account_no + "&ifsc_code=" + ifsc_code + "&client_id=" + client_id + "&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetBeneficiaryAccountValidationResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetTransactionStatusResponse CallTransactionStatusAPI(String transaction_id) {
        DmtAPI.GetTransactionStatusResponse objResponse=new DmtAPI.GetTransactionStatusResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/get_transaction_status";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&transaction_id=" + transaction_id + "&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetTransactionStatusResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }
    public static DmtAPI.GetTransactionStatusResponse CallFundTransferAPI(String mobile_no,String sender_profile_id, String beneficiary_id, int amount, String transfer_mode,long client_id) {
        DmtAPI.GetTransactionStatusResponse objResponse=new DmtAPI.GetTransactionStatusResponse();
        try {
            String token = getToken();
            String url = DMT_URL + "/fund_transfer";
            String bodyparam = "username=" + API_USERNAME + "&pwd=" + API_PASSWORD + "&mobile_no=" + mobile_no + "&sender_profile_id=" + sender_profile_id + "&beneficiary_id=" + beneficiary_id + "&amount=" + amount + "&transfer_mode=" + transfer_mode + "&client_id="+client_id+"&gateway=GW1&token=" + token;
            String api_result = ExecuteDMRAPIScriptApi(url, bodyparam);
            ObjectMapper om = new ObjectMapper();
            objResponse = om.readValue(api_result, DmtAPI.GetTransactionStatusResponse.class);
            return objResponse;
        } catch (Exception ex) {
            objResponse.setError_code("1");
            objResponse.setMessage(ex.getMessage());
            return objResponse;
        }
    }

    public static String ExecuteDMRAPIScriptApi(String url, String bodyparam) {
        try {
            URL obj = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            os.write(bodyparam.getBytes());
            os.flush();
            os.close();
            int responseCode = postConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "{'message':'Try again.','error_code':'1'}";
            }
        } catch (Exception ex) {
            return "{'message':'" + ex.getMessage() + "','error_code':'1'}";
        }

    }

    public static void main(String[] args) {
        //System.out.println(getToken());
        DmtAPI.GetCustomerResponse objResponse=CallGetCustomerAPI("YourMobileNo");
        System.out.println(objResponse.getMessage());
    }

}
