package com.chafikhicham.dataaccess;

import com.chafikhicham.dataaccess.entities.Customer;
import com.chafikhicham.dataaccess.entities.VisitRegistration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface VisitRegistrationRepository extends CrudRepository<VisitRegistration, Long> {

    @Query("SELECT vr FROM VisitRegistration vr  WHERE vr.restaurant.id=(:restaurantId)")
    List<VisitRegistration> findByRestaurantId(@Param("restaurantId") Long restaurantId);

    @Query("SELECT vr FROM VisitRegistration vr  WHERE vr.customer.id=(:customerId)")
    List<VisitRegistration> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT vr FROM VisitRegistration vr  WHERE vr.date <=(:date)")
    List<VisitRegistration> findByDate(@Param("date")Date date);
}
