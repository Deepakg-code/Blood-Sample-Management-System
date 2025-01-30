package com.jsp.bsm.service;

import com.jsp.bsm.requestdto.TransactionRequest;
import com.jsp.bsm.responsedto.TransactionResponse;

public interface TransactionService {

    TransactionResponse checkTransaction(TransactionRequest transactionRequest, int hospitalId, int userId) throws Exception;

//    TransactionResponse addUserTransaction(TransactionRequest transactionRequest);

//    TransactionResponse addHospitalTransaction(TransactionRequest transactionRequest, int hospitalId);

}
