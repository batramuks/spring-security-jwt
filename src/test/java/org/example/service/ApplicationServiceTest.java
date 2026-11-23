package org.example.service;

import org.example.dto.ApplicationDto;
import org.example.entity.Application;
import org.example.repository.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        applicationService = new ApplicationService(applicationRepository);
    }

    @Test
    void returnsSampleResponseAfterLookingUpRequestedId() {
        String appId = "APP-001";
        Application storedApplication = Application.builder()
                .appId(appId)
                .applicationStatus("ACTIVE")
                .countryCode("GB")
                .build();
        when(applicationRepository.findByAppId(appId)).thenReturn(Optional.of(storedApplication));

        ApplicationDto result = applicationService.getApplicationByAppId(appId);

        assertSampleResponse(result);
        verify(applicationRepository).findByAppId(appId);
    }

    @Test
    void returnsSampleResponseWhenRepositoryHasNoMatch() {
        String appId = "NONEXISTENT";
        when(applicationRepository.findByAppId(appId)).thenReturn(Optional.empty());

        ApplicationDto result = applicationService.getApplicationByAppId(appId);

        assertSampleResponse(result);
        verify(applicationRepository).findByAppId(appId);
    }

    @Test
    void propagatesRepositoryFailure() {
        String appId = "APP-001";
        var failure = new DataAccessResourceFailureException("Database unavailable");
        when(applicationRepository.findByAppId(appId)).thenThrow(failure);

        assertSame(failure, assertThrows(DataAccessResourceFailureException.class,
                () -> applicationService.getApplicationByAppId(appId)));
    }

    private static void assertSampleResponse(ApplicationDto result) {
        assertNotNull(result);
        assertEquals("APP-10482", result.getAppId());
        assertEquals("PENDING", result.getApplicationStatus());
        assertEquals("INITIAL", result.getProcessState());
        assertEquals("sample-workflow", result.getCmndProcessDefName());
        assertEquals("US", result.getCountryCode());
        assertEquals("USD", result.getCurrencyCode());
    }
}
