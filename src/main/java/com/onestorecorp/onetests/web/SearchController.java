package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.SearchCategory;
import com.onestorecorp.onetests.domain.SearchResult;
import com.onestorecorp.onetests.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private SearchService svc;

    @GetMapping("/api/search")
    public Map<String, SearchCategory> search(@RequestParam(defaultValue = "") String q) {
        return svc.search(q);
    }


}
