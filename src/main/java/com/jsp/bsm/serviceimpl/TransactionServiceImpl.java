package com.jsp.bsm.serviceimpl;

import com.jsp.bsm.entity.*;
import com.jsp.bsm.enums.TransactionType;
import com.jsp.bsm.exception.HospitalNotFoundException;
import com.jsp.bsm.exception.InsufficientUnitException;
import com.jsp.bsm.exception.UserNotFoundExceptionById;
import com.jsp.bsm.repository.*;
import com.jsp.bsm.requestdto.TransactionRequest;
import com.jsp.bsm.responsedto.TransactionResponse;
import com.jsp.bsm.security.AuthUtil;
import com.jsp.bsm.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private HospitalRepository hospitalRepository;
    private BloodRepository bloodRepository;
    private SampleRepository sampleRepository;
    private AuthUtil authUtil;

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .date(transaction.getDate())
                .time(transaction.getTime())
                .noOfUnits(transaction.getNoOfUnits())
                .transactionType(transaction.getTransactionType())
                .bloodGroup(transaction.getBloodGroup())
                .build();
    }

    private Transaction mapToTransaction(TransactionRequest transactionRequest, Transaction transaction)  {
        transaction.setDate(transactionRequest.getDate());
        transaction.setTime(transactionRequest.getTime());
        transaction.setNoOfUnits(transactionRequest.getNoOfUnits());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setBloodGroup(transactionRequest.getBloodGroup());
        return transaction;
    }

    @Override
    public TransactionResponse checkTransaction(TransactionRequest transactionRequest, int hospitalId, int userId) throws Exception {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new HospitalNotFoundException("Transaction failed"));
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundExceptionById("Transaction failed"));
        Admin admin = authUtil.getCurrentAdmin();
        BloodBank bloodBank = admin.getBloodBank();
        Transaction transaction = this.mapToTransaction(transactionRequest, new Transaction());
        List<Sample> samples=bloodBank.getSamples();
        Sample sample=null;
        for (Sample sample1:samples){
            if(sample1.getBloodGroup()==transaction.getBloodGroup()){
                sample=sample1;
            }
        }
        if (sample==null){
            throw new InsufficientUnitException("Not Available");
        }

        int i=sample.getAvailableUnits(),j= sample.getAvailableUnits();
        if(transaction.getTransactionType()==TransactionType.NORMAL){
            if(transaction.getNoOfUnits()>i){
                throw new InsufficientUnitException("Not Available");
            }else {
                sample.setAvailableUnits(i-transaction.getNoOfUnits());

            }
        }else {
            if(transaction.getNoOfUnits()>i){
                if (transaction.getNoOfUnits()>i+sample.getEmergencyUnits()){
                    throw new InsufficientUnitException("Not Found");
                }
                else {
                    sample.setAvailableUnits(0);
                    sample.setEmergencyUnits(sample.getEmergencyUnits()-transaction.getNoOfUnits()+j);

                }
            }else {
                sample.setAvailableUnits(i-transaction.getNoOfUnits());

            }
            sample.setQuantity(sample.getEmergencyUnits()+sample.getAvailableUnits());

            transaction.setUser(user);
            transaction.setHospital(hospital);
            transaction.setBloodBank(bloodBank);
            transactionRepository.save(transaction);


        }
        return this.mapToTransactionResponse(transaction);
    }

//    @Override
//    public TransactionResponse addUserTransaction(TransactionRequest transactionRequest) {
//        User user = authUtil.getCurrentUser();
//        Transaction transaction = this.mapToTransaction(transactionRequest, new Transaction());
//        transaction.setUser(user);
//        transaction = transactionRepository.save(transaction);
//        return this.mapToTransactionResponse(transaction);
//    }
//
//    @Override
//    public TransactionResponse addHospitalTransaction(TransactionRequest transactionRequest, int hospitalId) {
//        Hospital hospital = hospitalRepository.findById(hospitalId)
//                .orElseThrow(()-> new HospitalNotFoundException("Failed to sent the transaction"));
//        Transaction transaction = this.mapToTransaction(transactionRequest, new Transaction());
//        transaction.setHospital(hospital);
//        transaction = transactionRepository.save(transaction);
//        return this.mapToTransactionResponse(transaction);
//    }
}
