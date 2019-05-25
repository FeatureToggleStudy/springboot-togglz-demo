package com.kunal.demo.togglz.config;

import com.kunal.demo.togglz.service.SomeService;
import com.kunal.demo.togglz.service.impl.NewSomeServiceImpl;
import com.kunal.demo.togglz.service.impl.OldSomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.togglz.spring.proxy.FeatureProxyFactoryBean;

@Configuration
public class AppConfiguration {

    @Bean
    public SomeService oldSomeService() {
        return new OldSomeServiceImpl();
    }

    @Bean
    public SomeService newSomeService() {
        return new NewSomeServiceImpl();
    }

    @Bean
    public FeatureProxyFactoryBean proxiedSomeService() {
        FeatureProxyFactoryBean proxyFactoryBean = new FeatureProxyFactoryBean();
        proxyFactoryBean.setFeature(FeatureToggles.USE_NEW_SOMESERVICE.name());
        proxyFactoryBean.setProxyType(SomeService.class);
        proxyFactoryBean.setActive(this.newSomeService());
        proxyFactoryBean.setInactive(this.oldSomeService());
        return proxyFactoryBean;
    }

    @Bean
    @Primary
    public SomeService someService(@Autowired FeatureProxyFactoryBean proxiedSomeService) throws Exception {
        return (SomeService) proxiedSomeService.getObject();
    }
}