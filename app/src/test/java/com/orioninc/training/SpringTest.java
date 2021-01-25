package com.orioninc.training;

import com.orioninc.training.app.config.AppConfig;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class SpringTest {
    @Test
    public void whenSpringContextIsInstantiated_thenNoExceptions(){
    }
}
