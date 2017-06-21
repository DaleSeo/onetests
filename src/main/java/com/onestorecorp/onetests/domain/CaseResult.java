package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class CaseResult extends Domain {

    boolean statusPassed, headersPassed, bodyPassed;

    public boolean isPassed () {
        return this.statusPassed && headersPassed && bodyPassed;
    }

    String caseId;

    String callId;

    String error;

    Request request;

	Response expected;

	Response actual;

    long elapsedTime;

}
