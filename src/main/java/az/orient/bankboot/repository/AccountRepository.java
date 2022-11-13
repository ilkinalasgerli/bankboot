package az.orient.bankboot.repository;

import az.orient.bankboot.entity.Account;
import az.orient.bankboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAllByActive(Integer active);

    List<Account> findAllByCustomerAndActive(Customer customer, Integer active);

    Account findAccountByIdAndActive(Long id,Integer active);
}
