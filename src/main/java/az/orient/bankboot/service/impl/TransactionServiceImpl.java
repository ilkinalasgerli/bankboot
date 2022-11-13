package az.orient.bankboot.service.impl;

import az.orient.bankboot.dto.response.*;
import az.orient.bankboot.entity.Account;
import az.orient.bankboot.entity.Transaction;
import az.orient.bankboot.enums.EnumAvailableStatus;
import az.orient.bankboot.exception.BankException;
import az.orient.bankboot.exception.ExceptionConstant;
import az.orient.bankboot.repository.AccountRepository;
import az.orient.bankboot.repository.TransactionRepository;
import az.orient.bankboot.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
      private final TransactionRepository transactionRepository;
      private final AccountRepository accountRepository;
    @Override
    public Response<List<RespTransaction>> getTransactionList() {
        Response<List<RespTransaction>>response=new Response<>();
        List<RespTransaction> transactionList=new ArrayList<>();
        try {
            List<Transaction>transactList= transactionRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (transactList.isEmpty())
             throw  new BankException(ExceptionConstant.TRANSACTION_NOT_FOUND,"Transaction did not found");
        for (Transaction transaction: transactList){
            RespTransaction respTransaction =convert(transaction);
            transactionList.add(respTransaction);
        }
        response.setT(transactionList);
        response.setStatus(RespStatus.getSuccessMessage());

        }catch(BankException ex){
            response.setStatus(new RespStatus(ex.getcode(),ex.getMessage()));

        } catch (Exception e){
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION,"Internal Exception"));
            e.printStackTrace();
        }
        return response;

    }

    private RespTransaction convert(Transaction transaction) {
         Account account =transaction.getDtaccount();
         if (account==null){
           throw  new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND,"Debit account not found") ;
         }

        RespAccount respAccount=new RespAccount();
        RespCustomer respCustomer=new RespCustomer();
        respCustomer.setId(account.getCustomer().getId());
        respCustomer.setName(account.getCustomer().getName());
        respCustomer.setSurname(account.getCustomer().getSurname());
        respAccount.setRespCustomer(respCustomer);
        respAccount.setId(account.getId());
        respAccount.setAccountNo(account.getAccountNo());
        respAccount.setIban(account.getIban());
        respAccount.setCurrency(account.getCurrency());
        RespTransaction respTransaction=new RespTransaction();
        respTransaction.setAmount(transaction.getAmount());
        respTransaction.setCurrency(transaction.getCurrency());
        respTransaction.setDtaccount(respAccount);
        respTransaction.setCrAccount(transaction.getCrAccount());
        respTransaction.setTransactionId(transaction.getId());

        return respTransaction;
    }
}
