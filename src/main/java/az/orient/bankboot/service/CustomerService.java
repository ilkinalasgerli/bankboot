package az.orient.bankboot.service;

import az.orient.bankboot.dto.request.ReqCustomer;
import az.orient.bankboot.dto.response.RespCustomer;
import az.orient.bankboot.dto.response.Response;

import java.util.List;

public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerListById(Long customerId);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(Long customerId);
}
