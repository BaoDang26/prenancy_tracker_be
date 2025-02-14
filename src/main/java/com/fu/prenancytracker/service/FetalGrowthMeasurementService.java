package com.fu.prenancytracker.service;

import com.fu.prenancytracker.model.FetalGrowthMeasurement;

public interface FetalGrowthMeasurementService extends GeneralService<FetalGrowthMeasurement> {

    FetalGrowthMeasurement getMeasurementById(Integer measurementId);

    Iterable<FetalGrowthMeasurement> findByPregnancyID(Integer pregnancyID);
}
