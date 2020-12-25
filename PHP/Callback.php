<?php

//Recharge Callback
if(isset($_POST['data'])){
    $data = urldecode($_POST['data']);
    $callback_result = json_decode($data,true);
    $operator_code= isset($callback_result['operator_code']) ? $callback_result['operator_code'] : 'NA';
    $recharge_id=isset($callback_result['recharge_id']) ? $callback_result['recharge_id'] : '0';
    $client_id=isset($callback_result['client_id']) ? $callback_result['client_id'] : '0';
    $recharge_status=isset($callback_result['recharge_status']) ? $callback_result['recharge_status'] : 'Pending';
    if($recharge_status == 'Success'){ //Update Success Recharge
        

    }
    if($recharge_status == 'Failure'){ //Update Failure Recharge
        
    }
}

//DMT Callback
if(isset($_POST['dmt_data'])){
    $dmt_data = urldecode($_POST['dmt_data']);
    $callback_result = json_decode($dmt_data,true);
    $bank_ref_no= isset($callback_result['bank_ref_no']) ? $callback_result['bank_ref_no'] : 'NA';
    $transaction_id=isset($callback_result['transaction_id']) ? $callback_result['transaction_id'] : '0';
    $client_id=isset($callback_result['client_id']) ? $callback_result['client_id'] : '0';
    $status=isset($callback_result['status']) ? $callback_result['status'] : 'Pending';
    if($status == 'Success'){ //Update Success Money Transfer
        

    }
    if($status == 'Failure'){ //Update Failure Money Transfer
        
    }
}
