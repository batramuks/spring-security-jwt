package org.example.controller;

import org.example.dto.ApplicationDto;
import org.example.exception.ApplicationException;
import org.example.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * GET /applications/{applicationId} - Retrieve application by ID
     * @param applicationId the application ID (appId)
     * @return ApplicationDto with application details
     */
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDto> getApplicationById(
            @PathVariable String applicationId) {
        try {
            ApplicationDto application = applicationService.getApplicationByAppId(applicationId);
            return new ResponseEntity<>(application, HttpStatus.OK);
        } catch (ApplicationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
