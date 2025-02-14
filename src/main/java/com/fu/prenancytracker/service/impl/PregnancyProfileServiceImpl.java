package com.fu.prenancytracker.service.impl;

import com.fu.prenancytracker.exception.EntityNotFoundException;
import com.fu.prenancytracker.model.PregnancyProfile;
import com.fu.prenancytracker.repository.PregnancyProfileRepository;
import com.fu.prenancytracker.service.PregnancyProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PregnancyProfileServiceImpl implements PregnancyProfileService {
    private final PregnancyProfileRepository pregnancyProfileRepository;

    public PregnancyProfileServiceImpl(PregnancyProfileRepository pregnancyProfileRepository) {
        this.pregnancyProfileRepository = pregnancyProfileRepository;
    }

    @Override
    public PregnancyProfile createPregnancyProfile(PregnancyProfile profile) {
        return pregnancyProfileRepository.save(profile);
    }

    @Override
    public Optional<PregnancyProfile> getPregnancyProfileById(Integer pregnancyID) {
        return pregnancyProfileRepository.findAllById(pregnancyID);
    }

    @Override
    public Iterable<PregnancyProfile> getAllPregnancyProfilesOfUser(Integer userID) {
        return pregnancyProfileRepository.findAllByUser_Id(userID);
    }

    @Override
    public PregnancyProfile updatePregnancyProfile(PregnancyProfile profile) {
        return pregnancyProfileRepository.save(profile);
    }

    @Override
    public Optional<PregnancyProfile> findByID(Integer pregnancyProfileId) {
        return pregnancyProfileRepository.findAllById(pregnancyProfileId);
    }

    @Override
    public PregnancyProfile save(PregnancyProfile pregnancyProfile) {
        return pregnancyProfileRepository.save(pregnancyProfile);
    }

    @Override
    public Iterable<PregnancyProfile> findAll() {
        return pregnancyProfileRepository.findAll();
    }
}
