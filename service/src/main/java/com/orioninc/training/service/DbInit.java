package com.orioninc.training.service;

import com.orioninc.training.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Transactional
public class DbInit {
    private static final Logger LOG = LoggerFactory.getLogger(DbInit.class);

    @Autowired
    private UserService userService;

    @PostConstruct
    public void postConstruct() {
        LOG.debug("postConstruct");
       /* if (userService.count() == 0) {
            userService.initDB();
        }*/
    }
}
