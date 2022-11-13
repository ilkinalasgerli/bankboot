package az.orient.bankboot.service;

import az.orient.bankboot.dto.response.RespTransaction;
import az.orient.bankboot.dto.response.Response;

import java.util.List;

public interface TransactionService {
     Response<List<RespTransaction>> getTransactionList();

}
