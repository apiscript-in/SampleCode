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
/*
    Our simple static class that demonstrates how to create JWTs Token and API Calls.
 */
class jwtandapidemo {

    //Sample method to construct a JWT
    public static String getToken() {
        String SECRET_KEY = "YouSecretKey"; //Provide By APIScript
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //Payload 
        Map<String, Object> objMap = new HashMap<>();
        objMap.put("TimeStamp", Instant.now().getEpochSecond());
        objMap.put("EmailID", "EnterYourRegisterEmail");//Put Register Email ID Here

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setClaims(objMap)
                .signWith(signatureAlgorithm, SECRET_KEY.getBytes());
        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static String CallGetCustomerAPI() {
        try{
            String Token = getToken();
        final String POST_PARAMS = "username=YourUsername&pwd=YourPassword&mobile_no=CustomerMobile&gateway=GW1&token="+Token;
        System.out.println(POST_PARAMS);
        URL obj = new URL("http://staging.apiscript.in/dmt/get_customer");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            return response.toString();
        } else {
           return "{'message':'Try again.','error_code':'1'}";
        }
        }catch(Exception ex){
             return "{'message':'"+ex.getMessage()+"','error_code':'1'}";
        }     
        
    }

    public static void main(String[] args) {
        System.out.println(getToken());
        System.out.println(CallGetCustomerAPI());
    }

}
