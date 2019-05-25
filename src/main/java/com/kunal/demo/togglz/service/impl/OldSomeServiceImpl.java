package com.kunal.demo.togglz.service.impl;

import com.kunal.demo.togglz.service.SomeService;

public class OldSomeServiceImpl implements SomeService {

    @Override
    public String getSomeValue() {
        return "Value from old service implementation";
    }
}