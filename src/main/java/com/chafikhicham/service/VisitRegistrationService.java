package com.chafikhicham.service;

import com.chafikhicham.dataaccess.VisitRegistrationRepository;
import com.chafikhicham.dataaccess.entities.VisitRegistration;
import com.chafikhicham.rest.dto.VisitRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class VisitRegistrationService {

    @Autowired
    private VisitRegistrationRepository visitRegistrationRepository;

    public VisitRegistration createVisitRegistration() {
        VisitRegistration visitRegistration = new VisitRegistration();
        visitRegistration.setDate(new Date());
        visitRegistrationRepository.save(visitRegistration);
        return visitRegistration;
    }

    public void updateVisitRegistration(VisitRegistration visitRegistration) {
        visitRegistrationRepository.save(visitRegistration);
    }

    public void deleteVisitRegistration(VisitRegistration visitRegistration) {
        visitRegistrationRepository.delete(visitRegistration);
    }

    public List<VisitRegistration> getByRestaurantId(Long restaurantId) {
        return visitRegistrationRepository.findByRestaurantId(restaurantId);
    }

    public List<VisitRegistration> getByCustomerId(Long restaurantId) {
        return visitRegistrationRepository.findByCustomerId(restaurantId);
    }

    public void deleteVisitRegistrations(List<VisitRegistration> visitRegistrations) {
        for (VisitRegistration visitRegistration: visitRegistrations) {
            deleteVisitRegistration(visitRegistration);
        }
    }

    public VisitRegistrationRepository getVisitRegistrationRepository() {
        return visitRegistrationRepository;
    }

    public void setVisitRegistrationRepository(VisitRegistrationRepository visitRegistrationRepository) {
        this.visitRegistrationRepository = visitRegistrationRepository;
    }

    public VisitRegistrationDTO mapToVisitRegistrationDTO(VisitRegistration visitRegistration) {
        return new VisitRegistrationDTO().setDate(visitRegistration.getDate());
    }
}
