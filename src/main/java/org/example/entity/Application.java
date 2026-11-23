package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @Column(name = "APP_SEQ_ID")
    private Long appSeqId;

    @Column(name = "APP_ID", length = 40, nullable = false)
    private String appId;

    @Column(name = "APP_START_TIME")
    private LocalDateTime appStartTime;

    @Column(name = "APP_END_TIME")
    private LocalDateTime appEndTime;

    @Column(name = "APP_APPLICATION_STATUS", length = 40, nullable = false)
    private String applicationStatus;

    @Column(name = "APP_PROCESS_STATE", length = 40, nullable = false)
    private String processState;

    @Column(name = "APP_CMND_PROCESS_DEF_NAME", length = 40)
    private String cmndProcessDefName;

    @Column(name = "APP_CMND_PROCESS_DEF_VERSION", length = 40)
    private String cmndProcessDefVersion;

    @Column(name = "APP_API_VERSION", length = 40)
    private String apiVersion;

    @Column(name = "APP_COUNTRY_CODE", length = 2, nullable = false)
    private String countryCode;

    @Column(name = "APP_CURRENCY_CODE", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "APP_CREATED_BY", length = 40, nullable = false)
    private String createdBy;

    @Column(name = "APP_CREATED_ON")
    private LocalDateTime createdOn;

    @Column(name = "APP_MODIFIED_BY", length = 40, nullable = false)
    private String modifiedBy;

    @Column(name = "APP_MODIFIED_ON")
    private LocalDateTime modifiedOn;

    @Column(name = "APP_LOCK", nullable = false)
    private Long appLock;

    @Column(name = "APP_CSP_TIMESTAMP")
    private LocalDateTime cspTimestamp;

    @Column(name = "APP_MANUAL_SCORING_NEEDED")
    private Long manualScoringNeeded;
}
