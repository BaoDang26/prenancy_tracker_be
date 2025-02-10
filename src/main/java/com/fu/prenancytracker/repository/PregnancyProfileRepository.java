package com.fu.prenancytracker.repository;

import com.fu.prenancytracker.model.PregnancyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PregnancyProfileRepository extends JpaRepository<PregnancyProfile, Integer> {
    Optional<PregnancyProfile> findAllById(Integer id);

    Iterable<PregnancyProfile> findAllByUser_Id(Integer userId);
}