package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEmailRequest {
    private String appId;
    private String ssn;
    private String appStatus;
    private String emailId;
    private String subject;
    private String content;
    private String createdBy;
    private String languageCode;
}
