package com.orioninc.training.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EntityService<E> {
    private static final Logger LOG = LoggerFactory.getLogger(EntityService.class);


    private Class<E> entityClass;


}
