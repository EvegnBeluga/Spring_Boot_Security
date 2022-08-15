package ru.kata.spring.boot_security.demo.servicies;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;

public interface RoleService {

    Role findByRoleName(String roleName);

    void addRole(Role role);

}
