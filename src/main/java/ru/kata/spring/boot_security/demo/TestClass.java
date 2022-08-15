package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servicies.AdminService;
import ru.kata.spring.boot_security.demo.servicies.RoleService;

import javax.annotation.PostConstruct;

@Component
public class TestClass {

    private final AdminService adminService;
    private final RoleService roleService;


    @Autowired
    public TestClass(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void init() {
        User admin = new User("Admin", "admin@admin", 1); // создаём Админа, логин - admin@admin
        Role adminRole = new Role("ROLE_ADMIN"); // создаём роль АДМИН

        adminService.addUser(admin); // добваляем админа в базу
        roleService.addRole(adminRole); // добавляем роль в базу
        admin.setPassword("$2a$12$jvKjqReuS/wAO6vTomIwR.jAD6LTVPgVzAM/lA5jA0KtQfFJdLpuu"); //пароль админа - "100"
        admin.addRole(adminRole); // назначем роль Админу
        adminService.updateUser(admin); // сохраняем изменения в админе

        User user1 = new User("User1", "user@user", 1); // создаём Юзера, логин - user@user
        Role userRole = new Role("ROLE_USER"); // создаём роль ЮЗЕР

        adminService.addUser(user1); // добавляем юзера в базу
        roleService.addRole(userRole); // добавляем роль в базу
        user1.setPassword("$2a$12$jvKjqReuS/wAO6vTomIwR.jAD6LTVPgVzAM/lA5jA0KtQfFJdLpuu"); //пароль юзера - "100"
        user1.addRole(userRole); // назначаем роль юзеру
        adminService.updateUser(user1); // сохраняем изменения в юзере


        User comboUser = new User("ComboUser", "combo@combo", 1); // создаём юзера с двумя ролями, логин - combo@combo
        adminService.addUser(comboUser); // добавляем юзера в базу
        comboUser.addRole(adminRole); // назначем роль админа
        comboUser.addRole(userRole); // назначем роль юзера
        comboUser.setPassword("$2a$12$jvKjqReuS/wAO6vTomIwR.jAD6LTVPgVzAM/lA5jA0KtQfFJdLpuu"); // пароль юзера - "100"
        adminService.updateUser(comboUser); // сохраняем изменения в юзере

    }

}
