package com.orioninc.training.app.shell;

import com.orioninc.training.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellComands {
    private final UserService userService;


    @Autowired
    public ShellComands(UserService userService) {
        this.userService = userService;
    }

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

    @ShellMethod("Init demo users in DB.")
    public String initUsers(){
        userService.initDB();
        return userService.showUsers();
    }

    @ShellMethod("Show users in DB.")
    public String showUsers(){
        return userService.showUsers();
    }

}
