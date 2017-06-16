package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class CaseResult extends Domain {

    boolean statusPassed, headersPassed, bodyPassed;

    public boolean isPassed () {
        return this.statusPassed && headersPassed && bodyPassed;
    }

    String caseId;

    String callId;

    int elapsedTime;

}
