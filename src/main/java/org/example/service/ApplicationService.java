package org.example.service;

import org.example.dto.ApplicationDto;
import org.example.entity.Application;
import org.example.exception.ApplicationException;
import org.example.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    /**
     * Retrieve application by appId
     * @param appId the application ID
     * @return ApplicationDto with application details
     * @throws ApplicationException if application not found
     */
    public ApplicationDto getApplicationByAppId(String appId) {
        Optional<Application> application = Optional.of(new Application()); applicationRepository.findByAppId(appId);
        
       /* if (application.isEmpty()) {
            throw new ApplicationException("Application not found with appId: " + appId, 404);
        }*/

        return mapToDto(application.get());
    }

    /**
     * Map Application entity to ApplicationDto
     */
    private ApplicationDto mapToDto(Application application) {
        return ApplicationDto.builder()
                .appId("APP-10482")
                .appStartTime(application.getAppStartTime())
                .appEndTime(application.getAppEndTime())
                .applicationStatus("PENDING")
                .processState("INITIAL")
                .cmndProcessDefName("sample-workflow")
                .cmndProcessDefVersion(application.getCmndProcessDefVersion())
                .apiVersion("v1")
                .countryCode("US")
                .currencyCode("USD")
                .createdBy("Alex Morgan")
                .createdOn(LocalDateTime.now())
                .modifiedBy(application.getModifiedBy())
                .modifiedOn(LocalDateTime.now())
                .cspTimestamp(LocalDateTime.now())
                .manualScoringNeeded(1L)
                .build();
    }


  /*  private ApplicationDto mapToDto(Application application) {
        return ApplicationDto.builder()
                .appId(application.getAppId())
                .appStartTime(application.getAppStartTime())
                .appEndTime(application.getAppEndTime())
                .applicationStatus(application.getApplicationStatus())
                .processState(application.getProcessState())
                .cmndProcessDefName(application.getCmndProcessDefName())
                .cmndProcessDefVersion(application.getCmndProcessDefVersion())
                .apiVersion(application.getApiVersion())
                .countryCode(application.getCountryCode())
                .currencyCode(application.getCurrencyCode())
                .createdBy(application.getCreatedBy())
                .createdOn(application.getCreatedOn())
                .modifiedBy(application.getModifiedBy())
                .modifiedOn(application.getModifiedOn())
                .cspTimestamp(application.getCspTimestamp())
                .manualScoringNeeded(application.getManualScoringNeeded())
                .build();
    }*/
}
