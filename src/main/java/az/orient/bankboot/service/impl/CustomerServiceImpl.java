package az.orient.bankboot.service.impl;

import az.orient.bankboot.dto.request.ReqCustomer;
import az.orient.bankboot.dto.response.RespCustomer;
import az.orient.bankboot.dto.response.RespStatus;
import az.orient.bankboot.dto.response.Response;
import az.orient.bankboot.entity.Customer;
import az.orient.bankboot.enums.EnumAvailableStatus;
import az.orient.bankboot.exception.BankException;
import az.orient.bankboot.exception.ExceptionConstant;
import az.orient.bankboot.repository.CustomerRepository;
import az.orient.bankboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Override
    public Response<List<RespCustomer>> getCustomerList(){
        Response <List<RespCustomer>> response=new Response <>();
        List<RespCustomer>respCustomerList= new ArrayList<>();
        try {
         List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
          if (customerList.isEmpty()){
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND,"Customer not found");

          }
         for(Customer customer: customerList){
           RespCustomer respCustomer= new RespCustomer();
           respCustomer.setUsername(customer.getUsername());
           respCustomer.setId(customer.getId());
           respCustomer.setName(customer.getName());
           respCustomer.setSurname(customer.getSurname());
           respCustomer.setDob(customer.getDob());
           respCustomer.setEmail(customer.getEmail());
           respCustomer.setCif(customer.getCif());
           respCustomer.setPhone(customer.getPhone());
           respCustomer.setPin(customer.getPin());
           respCustomer.setSeria(customer.getSeria());
           respCustomerList.add(respCustomer);
         }
         response.setT(respCustomerList);
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
    public Response<RespCustomer> getCustomerListById(Long customerId) {
        Response<RespCustomer>response= new Response<>();
        try {
            if(customerId==null)
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA,"Invalid request");
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());

            RespCustomer respCustomer = new RespCustomer();
            respCustomer.setUsername(customer.getUsername());
            respCustomer.setId(customer.getId());
            respCustomer.setName(customer.getName());
            respCustomer.setSurname(customer.getSurname());
            respCustomer.setDob(customer.getDob());
            respCustomer.setEmail(customer.getEmail());
            respCustomer.setCif(customer.getCif());
            respCustomer.setPhone(customer.getPhone());
            respCustomer.setPin(customer.getPin());
            respCustomer.setSeria(customer.getSeria());
            response.setT(respCustomer);
            response.setStatus(RespStatus.getSuccessMessage());

}        catch(BankException ex){
        response.setStatus(new RespStatus(ex.getcode(),ex.getMessage()));

        } catch (Exception e){
        response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION,"Internal Exception"));
        e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response addCustomer(ReqCustomer reqCustomer) {
        Response response= new Response<>();
        try {
            String name=reqCustomer.getName();
            String surname=reqCustomer.getSurname();
            if (name==null|| surname==null)
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data" );
             Customer customer = new Customer();
             customer.setName(name);
             customer.setSurname(surname);
            customer.setCif(reqCustomer.getCif());
            customer.setDob(reqCustomer.getDob());
            customer.setPhone(reqCustomer.getPhone());
            customer.setPin(reqCustomer.getPin());
            customer.setSeria(reqCustomer.getSeria());
            customer.setEmail(reqCustomer.getEmail());
            customer.setPassword(reqCustomer.getPassword());
            customer.setUsername(reqCustomer.getUsername());
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch(BankException ex){
            response.setStatus(new RespStatus(ex.getcode(),ex.getMessage()));

        } catch (Exception e){
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION,"Internal Exception"));
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
         Response response= new Response<>();
         try {
             Long customerId=reqCustomer.getId();
             String name=reqCustomer.getName();
             String surname=reqCustomer.getSurname();
             if (customerId==null|| name==null|| surname==null)
                 throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA,"Invalid request Data");
             Customer customer =customerRepository.findCustomerByIdAndActive(customerId,EnumAvailableStatus.ACTIVE.getValue());
             if (customer==null)
                 throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND,"Customer not found");
             customer.setName(name);
             customer.setSurname(surname);
             customer.setCif(reqCustomer.getCif());
             customer.setDob(reqCustomer.getDob());
             customer.setPhone(reqCustomer.getPhone());
             customer.setPin(reqCustomer.getPin());
             customer.setSeria(reqCustomer.getSeria());
             customer.setEmail(reqCustomer.getEmail());
             customer.setPassword(reqCustomer.getPassword());
             customer.setUsername(reqCustomer.getUsername());
             customerRepository.save(customer);
             response.setStatus(RespStatus.getSuccessMessage());

         } catch(BankException ex){
             response.setStatus(new RespStatus(ex.getcode(),ex.getMessage()));

         } catch (Exception e){
             response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION,"Internal Exception"));
             e.printStackTrace();
         }
        return response;

    }

    @Override
    public Response deleteCustomer(Long customerId) {
        return null;
    }


}
