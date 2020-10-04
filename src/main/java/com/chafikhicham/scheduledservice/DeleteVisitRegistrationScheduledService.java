package com.chafikhicham.scheduledservice;

import com.chafikhicham.dataaccess.VisitRegistrationRepository;
import com.chafikhicham.dataaccess.entities.VisitRegistration;
import com.chafikhicham.service.VisitRegistrationService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class DeleteVisitRegistrationScheduledService {

    @Autowired
    private VisitRegistrationService visitRegistrationService;

    @Autowired
    private VisitRegistrationRepository visitRegistrationRepository;

    //Fire at 10:15 AM every day
    @Scheduled(cron = "0 15 10 ? * *")
    public void deleteCustomers() {
        Date dateBefore30Days = DateUtils.addDays(new Date(),-30);
        List<VisitRegistration> visitRegistrations = visitRegistrationRepository.findByDate(dateBefore30Days);
        visitRegistrations.forEach(visitRegistration -> visitRegistrationService.deleteVisitRegistration(visitRegistration));
    }
}
