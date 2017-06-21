package com.onestorecorp.onetests.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class SuiteResult extends Domain {

    @JsonIgnore
    private final AtomicInteger counter = new AtomicInteger();

    int total;
    int success;
    long elapsedTime;

    @Indexed
    String suiteId;

    @DBRef(lazy = true)
    List<CaseResult> caseResults;

    public SuiteResult() {
    }

    public SuiteResult(String suiteId) {
        this.suiteId = suiteId;
    }

    public void incrementSuccess() {
        success = counter.incrementAndGet();
    }

    public int getFailure() {
        return total - success;
    }

}