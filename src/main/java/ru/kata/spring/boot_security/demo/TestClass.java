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
        if (
                (roleService.findAll().size() != 2) &&
                        (adminService.findByEmail("Evgeny@mail.ru") == null) &&
                        (adminService.findByEmail("Igor@mail.ru") == null) &&
                        (adminService.findByEmail("German@mail.ru") == null)
        ) {

            Role adminRole = new Role("ROLE_ADMIN"); // создаём роль АДМИН
            roleService.addRole(adminRole); // добавляем роль в базу
            Role userRole = new Role("ROLE_USER"); // создаём роль ЮЗЕР
            roleService.addRole(userRole); // добавляем роль в базу


            User admin = new User("Evgeny", "Kozhevnikov", "Evgeny@mail.ru", 43); // создаём Админа, логин - почта
            admin.setPassword("100"); //пароль админа - "100"
            admin.addRole(adminRole); // назначем роль Админу
            adminService.addUser(admin); // добваляем админа в базу


            User user1 = new User("Igor", "Entaev", "Igor@mail.ru", 35); // создаём Юзера, логин - почта
            user1.setPassword("100"); //пароль юзера - "100"
            user1.addRole(userRole); // назначаем роль юзеру
            adminService.addUser(user1); // добавляем юзера в базу


            User comboUser = new User("German", "Sevostyanov", "German@mail.ru", 35); // создаём юзера с двумя ролями, логин - почта
            comboUser.setPassword("100"); // пароль юзера - "100"
            comboUser.addRole(adminRole); // назначем роль админа
            comboUser.addRole(userRole); // назначем роль юзера
            adminService.addUser(comboUser); // добавляем юзера в базу
            adminService.updateUser(comboUser); // сохраняем изменения в юзере
        }

    }

}
