package com.kunal.demo.togglz.web;

import com.kunal.demo.togglz.config.FeatureToggles;
import com.kunal.demo.togglz.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DemoResource {

    @Autowired
    private SomeService someService;

    @GetMapping(value = "/demo-es/{id}")
    public String getDemoEs(@PathVariable("id") String id) {
        if (FeatureToggles.TEXT_BASED_SEARCH_VIA_ELASTIC_SEARCH.isActive()) {
            return "New ElasticSearch Backend is active";
        }
        return "Still relying in existing / legacy RDBMS backend implementation";
    }

    @GetMapping(value = "/demo-cms/{id}")
    public String getDemoCms(@PathVariable("id") String id) {
        if (FeatureToggles.CONTENT_RETRIEVAL_VIA_CMS.isActive()) {
            return "New CMS Backend is active";
        }
        return "Still relying in existing / legacy cms backend implementation";
    }

    @GetMapping(value = "/demo-someservice/{id}")
    public String getDemoSomeService(@PathVariable("id") String id) {
        return this.someService.getSomeValue();
    }
}