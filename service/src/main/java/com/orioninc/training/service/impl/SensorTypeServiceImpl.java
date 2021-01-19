package com.orioninc.training.service.impl;

import com.orioninc.training.app.api.SensorTypeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SensorTypeServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(SensorTypeServiceImpl.class);
    private SensorTypeRepo sensorTypeRepo;

    @Autowired
    public SensorTypeServiceImpl(SensorTypeRepo sensorTypeRepo) {
        this.sensorTypeRepo = sensorTypeRepo;
    }

    public void SensorTypeInit(){
        LOG.debug("init SensorType");


    }
}
