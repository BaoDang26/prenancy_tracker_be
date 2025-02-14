package com.fu.prenancytracker.service;

import com.fu.prenancytracker.model.PregnancyProfile;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PregnancyProfileService extends GeneralService<PregnancyProfile> {
    PregnancyProfile createPregnancyProfile(PregnancyProfile profile);

    Optional<PregnancyProfile> getPregnancyProfileById(Integer pregnancyID);

    Iterable<PregnancyProfile> getAllPregnancyProfilesOfUser(Integer userID);

    PregnancyProfile updatePregnancyProfile(PregnancyProfile profile);

    Optional<PregnancyProfile> findByID(Integer pregnancyProfileId);
}
