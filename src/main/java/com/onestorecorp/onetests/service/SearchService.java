package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.SearchCategory;
import com.onestorecorp.onetests.domain.SearchResult;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.repository.CaseRepository;
import com.onestorecorp.onetests.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class SearchService {

    @Autowired
    private ServiceRepository serviceRepo;

    @Autowired
    private CaseRepository caseRepo;

    public Map<String, SearchCategory> search(String path) {
        Map<String, SearchCategory> categories = new HashMap<>();
        for (Service service : serviceRepo.findAll()) {
            List<SearchResult> results =
                    caseRepo.findByServiceIdAndRequestPathContaining(service.getId(), path)
                            .parallelStream()
                            .map(SearchService::convert)
                            .collect(Collectors.toList());

            if (results.isEmpty()) continue;

            SearchCategory category = new SearchCategory();
            category.setName(service.getCode());
            category.setResults(results);

            categories.put(service.getCode(), category);
        }
        return categories;
    }

    private static SearchResult convert(Case cas) {
        SearchResult result = new SearchResult();
        result.setTitle(cas.getRequest().getMethod() + " " + cas.getRequest().getPath());
        result.setUrl("/cases/" + cas.getId());
        result.setPrice("");
        result.setDescription(cas.getTitle());
        return result;
    }

}
