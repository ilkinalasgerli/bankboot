package az.orient.bankboot.controller;

import az.orient.bankboot.dto.response.RespTransaction;
import az.orient.bankboot.dto.response.Response;
import az.orient.bankboot.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/getTransactionList")
    public Response<List<RespTransaction>> getTransactionList (){
        return transactionService.getTransactionList();
    }

}
