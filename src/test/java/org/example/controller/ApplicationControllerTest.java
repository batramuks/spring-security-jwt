package org.example.controller;

import org.example.dto.ApplicationDto;
import org.example.exception.ApplicationException;
import org.example.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationControllerTest {

    private ApplicationController applicationController;

    @Mock
    private ApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        applicationController = new ApplicationController(applicationService);
    }

    @Test
    public void testGetApplicationById_Success() {
        // Arrange
        String applicationId = "APP-001";
        ApplicationDto mockApplicationDto = ApplicationDto.builder()
                .appId(applicationId)
                .applicationStatus("ACTIVE")
                .processState("APPROVED")
                .countryCode("US")
                .currencyCode("USD")
                .createdBy("ADMIN")
                .modifiedBy("ADMIN")
                .appStartTime(LocalDateTime.now())
                .manualScoringNeeded(0L)
                .build();

        when(applicationService.getApplicationByAppId(applicationId))
                .thenReturn(mockApplicationDto);

        // Act
        ResponseEntity<ApplicationDto> response = applicationController.getApplicationById(applicationId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(applicationId, response.getBody().getAppId());
        assertEquals("ACTIVE", response.getBody().getApplicationStatus());
        verify(applicationService, times(1)).getApplicationByAppId(applicationId);
    }

    @Test
    public void testGetApplicationById_NotFound() {
        // Arrange
        String applicationId = "INVALID-APP-ID";
        when(applicationService.getApplicationByAppId(applicationId))
                .thenThrow(new ApplicationException("Application not found with appId: " + applicationId, 404));

        // Act
        ResponseEntity<ApplicationDto> response = applicationController.getApplicationById(applicationId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(applicationService, times(1)).getApplicationByAppId(applicationId);
    }

    @Test
    public void testGetApplicationById_MultipleApplications() {
        // Arrange
        String applicationId = "APP-002";
        ApplicationDto mockApplicationDto = ApplicationDto.builder()
                .appId(applicationId)
                .applicationStatus("PENDING")
                .processState("SUBMITTED")
                .countryCode("GB")
                .currencyCode("GBP")
                .createdBy("USER1")
                .modifiedBy("USER1")
                .build();

        when(applicationService.getApplicationByAppId(applicationId))
                .thenReturn(mockApplicationDto);

        // Act
        ResponseEntity<ApplicationDto> response = applicationController.getApplicationById(applicationId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("PENDING", response.getBody().getApplicationStatus());
        assertEquals("GB", response.getBody().getCountryCode());
    }

    @Test
    public void testGetApplicationById_CompleteData() {
        // Arrange
        String applicationId = "APP-COMPLETE";
        LocalDateTime now = LocalDateTime.now();
        ApplicationDto mockApplicationDto = ApplicationDto.builder()
                .appId(applicationId)
                .appStartTime(now)
                .appEndTime(now.plusDays(1))
                .applicationStatus("COMPLETED")
                .processState("CLOSED")
                .cmndProcessDefName("LOAN_PROCESS")
                .cmndProcessDefVersion("1.0")
                .apiVersion("2.0")
                .countryCode("CA")
                .currencyCode("CAD")
                .createdBy("SYSTEM")
                .createdOn(now)
                .modifiedBy("SYSTEM")
                .modifiedOn(now)
                .cspTimestamp(now)
                .manualScoringNeeded(1L)
                .build();

        when(applicationService.getApplicationByAppId(applicationId))
                .thenReturn(mockApplicationDto);

        // Act
        ResponseEntity<ApplicationDto> response = applicationController.getApplicationById(applicationId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApplicationDto result = response.getBody();
        assertNotNull(result);
        assertEquals(applicationId, result.getAppId());
        assertEquals("COMPLETED", result.getApplicationStatus());
        assertEquals("LOAN_PROCESS", result.getCmndProcessDefName());
        assertEquals("1.0", result.getCmndProcessDefVersion());
        assertEquals(1L, result.getManualScoringNeeded());
    }

    @Test
    public void testGetApplicationById_ServiceThrowsException() {
        // Arrange
        String applicationId = "EXCEPTION-APP";
        when(applicationService.getApplicationByAppId(applicationId))
                .thenThrow(new ApplicationException("Application not found with appId: " + applicationId, 404));

        // Act
        ResponseEntity<ApplicationDto> response = applicationController.getApplicationById(applicationId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(applicationService, times(1)).getApplicationByAppId(applicationId);
    }
}
