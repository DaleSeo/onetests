package com.onestorecorp.onetests.domain;

import lombok.Data;

@Data
public class SearchResult {

    String title, url, price, description;

    public SearchResult() {
    }

}
