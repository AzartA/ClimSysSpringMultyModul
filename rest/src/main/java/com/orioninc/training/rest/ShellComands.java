package com.orioninc.training.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellComands {
    @Autowired
    UserService userService;

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

    @ShellMethod("Init demo users in DB.")
    public String initUsers(){
        userService.initDB();
        return userService.showUsers();
    }
}
