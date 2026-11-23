package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto {

    private String appId;
    private LocalDateTime appStartTime;
    private LocalDateTime appEndTime;
    private String applicationStatus;
    private String processState;
    private String cmndProcessDefName;
    private String cmndProcessDefVersion;
    private String apiVersion;
    private String countryCode;
    private String currencyCode;
    private String createdBy;
    private LocalDateTime createdOn;
    private String modifiedBy;
    private LocalDateTime modifiedOn;
    private LocalDateTime cspTimestamp;
    private Long manualScoringNeeded;
}
