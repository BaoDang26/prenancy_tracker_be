package com.fu.prenancytracker.service.impl;

import com.fu.prenancytracker.exception.EntityNotFoundException;
import com.fu.prenancytracker.model.FetalGrowthMeasurement;
import com.fu.prenancytracker.repository.FetalGrowthMeasurementRepository;
import com.fu.prenancytracker.service.FetalGrowthMeasurementService;
import org.springframework.stereotype.Service;

@Service
public class FetalGrowthMeasurementServiceImpl implements FetalGrowthMeasurementService {
    private final FetalGrowthMeasurementRepository fetalRepository;

    public FetalGrowthMeasurementServiceImpl(FetalGrowthMeasurementRepository fetalRepository) {
        this.fetalRepository = fetalRepository;
    }

    @Override
    public FetalGrowthMeasurement save(FetalGrowthMeasurement fetalGrowthMeasurement) {
        return fetalRepository.save(fetalGrowthMeasurement);
    }

    @Override
    public Iterable<FetalGrowthMeasurement> findAll() {
        return fetalRepository.findAll();
    }

    @Override
    public FetalGrowthMeasurement getMeasurementById(Integer measurementId) {
        return fetalRepository.findById(measurementId).orElseThrow(() -> new EntityNotFoundException("Measurement id {" + measurementId + "} not found"));
    }

    @Override
    public Iterable<FetalGrowthMeasurement> findByPregnancyID(Integer pregnancyID) {
        return fetalRepository.findAllByPregnancyProfile_Id(pregnancyID);
    }
}
