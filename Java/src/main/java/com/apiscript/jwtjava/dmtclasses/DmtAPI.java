/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiscript.jwtjava.dmtclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author Api script
 */
public class DmtAPI {
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */

    public static class SenderData {

        @JsonProperty("sender_profile_id")
        public String getSender_profile_id() {
            return this.sender_profile_id;
        }

        public void setSender_profile_id(String sender_profile_id) {
            this.sender_profile_id = sender_profile_id;
        }
        String sender_profile_id;

        @JsonProperty("sender_fname")
        public String getSender_fname() {
            return this.sender_fname;
        }

        public void setSender_fname(String sender_fname) {
            this.sender_fname = sender_fname;
        }
        String sender_fname;

        @JsonProperty("sender_lname")
        public String getSender_lname() {
            return this.sender_lname;
        }

        public void setSender_lname(String sender_lname) {
            this.sender_lname = sender_lname;
        }
        String sender_lname;

        @JsonProperty("sender_pincode")
        public String getSender_pincode() {
            return this.sender_pincode;
        }

        public void setSender_pincode(String sender_pincode) {
            this.sender_pincode = sender_pincode;
        }
        String sender_pincode;

        @JsonProperty("sender_mobile_no")
        public String getSender_mobile_no() {
            return this.sender_mobile_no;
        }

        public void setSender_mobile_no(String sender_mobile_no) {
            this.sender_mobile_no = sender_mobile_no;
        }
        String sender_mobile_no;

        @JsonProperty("transaction_limit")
        public int getTransaction_limit() {
            return this.transaction_limit;
        }

        public void setTransaction_limit(int transaction_limit) {
            this.transaction_limit = transaction_limit;
        }
        int transaction_limit;

        @JsonProperty("sender_status")
        public String getSender_status() {
            return this.sender_status;
        }

        public void setSender_status(String sender_status) {
            this.sender_status = sender_status;
        }
        String sender_status;
    }

    public static class SenderBeneficiary {

        @JsonProperty("beneficiary_id")
        public String getBeneficiary_id() {
            return this.beneficiary_id;
        }

        public void setBeneficiary_id(String beneficiary_id) {
            this.beneficiary_id = beneficiary_id;
        }
        String beneficiary_id;

        @JsonProperty("beneficiary_name")
        public String getBeneficiary_name() {
            return this.beneficiary_name;
        }

        public void setBeneficiary_name(String beneficiary_name) {
            this.beneficiary_name = beneficiary_name;
        }
        String beneficiary_name;

        @JsonProperty("bank_name")
        public String getBank_name() {
            return this.bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
        String bank_name;

        @JsonProperty("ifsc_code")
        public String getIfsc_code() {
            return this.ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }
        String ifsc_code;

        @JsonProperty("account_no")
        public String getAccount_no() {
            return this.account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }
        String account_no;

        @JsonProperty("imps_status")
        public String getImps_status() {
            return this.imps_status;
        }

        public void setImps_status(String imps_status) {
            this.imps_status = imps_status;
        }
        String imps_status;

        @JsonProperty("neft_status")
        public String getNeft_status() {
            return this.neft_status;
        }

        public void setNeft_status(String neft_status) {
            this.neft_status = neft_status;
        }
        String neft_status;

        @JsonProperty("is_verify")
        public String getIs_verify() {
            return this.is_verify;
        }

        public void setIs_verify(String is_verify) {
            this.is_verify = is_verify;
        }
        String is_verify;

        @JsonProperty("beneficiary_status")
        public String getBeneficiary_status() {
            return this.beneficiary_status;
        }

        public void setBeneficiary_status(String beneficiary_status) {
            this.beneficiary_status = beneficiary_status;
        }
        String beneficiary_status;
    }

    public static class GetCustomerResponse {

        @JsonProperty("message")
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        String message;

        @JsonProperty("sender_data")
        public SenderData getSender_data() {
            return this.sender_data;
        }

        public void setSender_data(SenderData sender_data) {
            this.sender_data = sender_data;
        }
        SenderData sender_data;

        @JsonProperty("sender_beneficiaries")
        public List<SenderBeneficiary> getSender_beneficiaries() {
            return this.sender_beneficiaries;
        }

        public void setSender_beneficiaries(List<SenderBeneficiary> sender_beneficiaries) {
            this.sender_beneficiaries = sender_beneficiaries;
        }
        List<SenderBeneficiary> sender_beneficiaries;

        @JsonProperty("error_code")
        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
        String error_code;
    }

    public static class GetSenderRegistrationResponse {

        @JsonProperty("message")
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        String message;

        @JsonProperty("sender_data")
        public SenderData getSender_data() {
            return this.sender_data;
        }

        public void setSender_data(SenderData sender_data) {
            this.sender_data = sender_data;
        }
        SenderData sender_data;

        @JsonProperty("error_code")
        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
        String error_code;
    }

    public static class GetCommonResponse {

        @JsonProperty("message")
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        String message;

        @JsonProperty("error_code")
        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
        String error_code;
    }

    public static class GetBeneficiaryRegistrationResponse {

        @JsonProperty("message")
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        String message;

        @JsonProperty("sender_data")
        public SenderData getSender_data() {
            return this.sender_data;
        }

        public void setSender_data(SenderData sender_data) {
            this.sender_data = sender_data;
        }
        SenderData sender_data;

        @JsonProperty("sender_beneficiary")
        public List<SenderBeneficiary> getSender_beneficiary() {
            return this.sender_beneficiary;
        }

        public void setSender_beneficiary(List<SenderBeneficiary> sender_beneficiary) {
            this.sender_beneficiary = sender_beneficiary;
        }
        List<SenderBeneficiary> sender_beneficiary;

        @JsonProperty("error_code")
        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
        String error_code;
    }

    public static class GetBeneficiaryAccountValidationResponse {

        @JsonProperty("message")
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        String message;

        @JsonProperty("account_name_as_per_bank")
        public String getAccount_name_as_per_bank() {
            return this.account_name_as_per_bank;
        }

        public void setAccount_name_as_per_bank(String account_name_as_per_bank) {
            this.account_name_as_per_bank = account_name_as_per_bank;
        }
        String account_name_as_per_bank;

        @JsonProperty("bank_ref_no")
        public String getBank_ref_no() {
            return this.bank_ref_no;
        }

        public void setBank_ref_no(String bank_ref_no) {
            this.bank_ref_no = bank_ref_no;
        }
        String bank_ref_no;

        @JsonProperty("error_code")
        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
        String error_code;
    }

    public class TransactionDetails {

        @JsonProperty("transaction_id")
        public String getTransaction_id() {
            return this.transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }
        String transaction_id;

        @JsonProperty("amount")
        public String getAmount() {
            return this.amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
        String amount;

        @JsonProperty("service_charge")
        public String getService_charge() {
            return this.service_charge;
        }

        public void setService_charge(String service_charge) {
            this.service_charge = service_charge;
        }
        String service_charge;

        @JsonProperty("bank_ref_no")
        public String getBank_ref_no() {
            return this.bank_ref_no;
        }

        public void setBank_ref_no(String bank_ref_no) {
            this.bank_ref_no = bank_ref_no;
        }
        String bank_ref_no;

        @JsonProperty("status")
        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        String status;

        @JsonProperty("client_id")
        public String getClient_id() {
            return this.client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }
        String client_id;
    }

    public static class GetTransactionStatusResponse {

        @JsonProperty("message")
        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        String message;

        @JsonProperty("transaction_details")
        public TransactionDetails getTransaction_details() {
            return this.transaction_details;
        }

        public void setTransaction_details(TransactionDetails transaction_details) {
            this.transaction_details = transaction_details;
        }
        TransactionDetails transaction_details;

        @JsonProperty("error_code")
        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
        String error_code;
    }

}
