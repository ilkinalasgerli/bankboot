package az.orient.bankboot.controller;

import az.orient.bankboot.dto.request.ReqCustomer;
import az.orient.bankboot.dto.response.RespCustomer;
import az.orient.bankboot.dto.response.Response;
import az.orient.bankboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/GetCustomerList")
    public Response<List<RespCustomer>> getCustomerList(){

    return customerService.getCustomerList();

    }
    @GetMapping(value = "/GetCustomerListById")
    public Response<RespCustomer> getCustomerListById(@RequestParam("custId") Long customerId){

        return customerService.getCustomerListById(customerId);
    }

    @PostMapping("/AddCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.addCustomer(reqCustomer);
    }

    @PutMapping("/UpdateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PutMapping("/DeleteCustomer")
    public Response deleteCustomer(@RequestParam("custId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
