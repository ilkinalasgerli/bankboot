package az.orient.bankboot.repository;

import az.orient.bankboot.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByActive(Integer active);

}
