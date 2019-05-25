package com.kunal.demo.togglz.service.impl;

import com.kunal.demo.togglz.service.SomeService;

public class NewSomeServiceImpl implements SomeService {

    @Override
    public String getSomeValue() {
        return "Value from new service implementation";
    }
}