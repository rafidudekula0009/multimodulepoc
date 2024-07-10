package org.seller.dao;
 
import org.seller.entity.Customer;
import org.springframework.data.repository.CrudRepository;
 
public interface CustomerRepository extends CrudRepository<Customer, Long> {
     
}