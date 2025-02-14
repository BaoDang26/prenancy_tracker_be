package com.fu.prenancytracker.repository;

import com.fu.prenancytracker.model.FetalGrowthMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FetalGrowthMeasurementRepository extends JpaRepository<FetalGrowthMeasurement, Integer> {
    Iterable<FetalGrowthMeasurement> findAllByPregnancyProfile_Id(Integer pregnancyProfileId);
}