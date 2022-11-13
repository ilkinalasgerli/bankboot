package az.orient.bankboot.controller;

import az.orient.bankboot.dto.response.RespAccount;
import az.orient.bankboot.dto.response.Response;
import az.orient.bankboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/GetAccountList")
    public Response<List<RespAccount>> getAccountList(){

        return accountService.getAccountList();
    }

    @GetMapping("/GetAccountListByCustomerID/{custId}")
    public Response<List<RespAccount>> getAccountListByCustomerId(@PathVariable("custId")Long customerId){
      return accountService.getAccountByCustomerId(customerId);
    }

    @GetMapping("/GetAccountById/{accountId}")
    public Response<RespAccount> getAccountListByAccountId(@PathVariable("account")Long accountId){
        return accountService.getAccountListByAccountId(accountId);
    }
}
