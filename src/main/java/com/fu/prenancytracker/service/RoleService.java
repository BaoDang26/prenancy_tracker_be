package com.fu.prenancytracker.service;

import com.fu.prenancytracker.model.Role;

import java.util.Optional;

public interface RoleService extends GeneralService<Role> {
    Optional<Role> findByRoleName(String roleName);
}
