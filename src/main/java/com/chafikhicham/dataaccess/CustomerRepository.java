package com.chafikhicham.dataaccess;

import com.chafikhicham.dataaccess.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT cus FROM Customer cus  WHERE cus.name=(:paramName)")
    List<Customer> findByName(@Param("paramName") String paramName);

    @Query("SELECT cus FROM Customer cus  WHERE cus.name=(:paramName) and cus.lastName=(:paramLastName) and cus.phoneNumber=(:paramPhoneNumber)")
    Customer findByNameAndLastNameAndPhoneNumber(@Param("paramName") String paramName, @Param("paramLastName") String paramLastName, @Param("paramPhoneNumber") String paramPhoneNumber);

}
