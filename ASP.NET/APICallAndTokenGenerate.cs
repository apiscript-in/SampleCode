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

public class APICallAndTokenGenerate{
    
public string getToken(string email_id, string secret_key)
        {
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
        public string CallAPI()
        {
            try
            {
                string token = getToken("your_register_email", "your_secret_key");
                var request = (HttpWebRequest)WebRequest.Create("http://staging.apiscript.in/account/balance");

                var postData = "username=YourUsername&pwd=YourPwd&token=" + token;
                var data = Encoding.ASCII.GetBytes(postData);

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
            catch (Exception)
            {

                return "";
            }
        }
}