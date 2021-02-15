package com.orioninc.training.service.impl;

import com.orioninc.training.repo.api.SensorTypeRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class SensorTypeServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(SensorTypeServiceImpl.class);
    private SensorTypeRepo sensorTypeRepo;

    public void SensorTypeInit() {
        LOG.debug("init SensorType");
    }
}
