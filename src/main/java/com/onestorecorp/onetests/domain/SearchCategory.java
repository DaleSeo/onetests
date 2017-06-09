package com.onestorecorp.onetests.domain;

import lombok.Data;

import java.util.List;

@Data
public class SearchCategory {

    String name;
    List<SearchResult> results;

}
