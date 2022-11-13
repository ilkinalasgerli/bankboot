package az.orient.bankboot.service;

import az.orient.bankboot.dto.response.RespAccount;
import az.orient.bankboot.dto.response.Response;

import java.util.List;

public interface AccountService {


    Response<List<RespAccount>> getAccountList();

    Response<List<RespAccount>> getAccountByCustomerId(Long customerId);

    Response<RespAccount> getAccountListByAccountId(Long accountId);
}


