package az.orient.bankboot.service.impl;

import az.orient.bankboot.dto.response.RespAccount;
import az.orient.bankboot.dto.response.RespCustomer;
import az.orient.bankboot.dto.response.RespStatus;
import az.orient.bankboot.dto.response.Response;
import az.orient.bankboot.entity.Account;
import az.orient.bankboot.enums.EnumAvailableStatus;
import az.orient.bankboot.exception.BankException;
import az.orient.bankboot.exception.ExceptionConstant;
import az.orient.bankboot.repository.AccountRepository;
import az.orient.bankboot.repository.CustomerRepository;
import az.orient.bankboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    @Override
    public Response<List<RespAccount>> getAccountList() {
        Response<List<RespAccount>> response = new Response<>();
        List<RespAccount> respAccountList=new ArrayList<>();
        try {
            List<Account>accountList=accountRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (accountList.isEmpty()) {
                throw new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND, "Account not found");
            }
            for(Account account:accountList){
               RespAccount respAccount=convert(account);
               respAccountList.add(respAccount);
            }
             response.setT(respAccountList);
             response.setStatus(RespStatus.getSuccessMessage());
        }catch(BankException ex){
            response.setStatus(new RespStatus(ex.getcode(),ex.getMessage()));

        } catch (Exception e){
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION,"Internal Exception"));
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<List<RespAccount>> getAccountByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Response<RespAccount> getAccountListByAccountId(Long accountId) {
        return null;
    }

    private RespAccount convert(Account account){
          RespAccount respAccount=new RespAccount();
          respAccount.setId(account.getId());
          respAccount.setAccountNo(account.getAccountNo());
          respAccount.setIban(account.getIban());
          respAccount.setCurrency(account.getCurrency());
        RespCustomer respCustomer=new RespCustomer();
        respCustomer.setId(account.getCustomer().getId());
        respCustomer.setName(account.getCustomer().getName());
        respCustomer.setSurname(account.getCustomer().getSurname());
        respAccount.setRespCustomer(respCustomer);
        return respAccount;


    }
}
