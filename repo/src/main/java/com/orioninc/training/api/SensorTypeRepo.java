package com.orioninc.training.api;

import com.orioninc.training.model.dos.SensorTypeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepo extends JpaRepository<SensorTypeDO,Long> {
}
