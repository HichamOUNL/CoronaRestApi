package com.chafikhicham.dataaccess;

import com.chafikhicham.dataaccess.entities.Customer;
import com.chafikhicham.dataaccess.entities.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Restaurant findByName(String name);

    @Query("SELECT res FROM Restaurant res  WHERE res.name=(:paramName) and res.address=(:paramAddress) and res.email=(:paramEmail)")
    Restaurant findByNameAndAddressAndEmail(@Param("paramName") String paramName, @Param("paramAddress") String paramAddress, @Param("paramEmail") String paramEmail);
}
