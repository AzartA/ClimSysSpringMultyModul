package com.orioninc.training.app.shell;

import com.orioninc.training.app.RestApplication;
import com.orioninc.training.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellComands {
    private final UserService userService;
    private ConfigurableApplicationContext context;

    @Autowired
    public ShellComands(UserService userService, ConfigurableApplicationContext context) {
        this.userService = userService;
        this.context = context;
    }

    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

    @ShellMethod("Init demo users in DB.")
    public String initUsers(){
        userService.initTwoDemoUsers();
        return userService.showUsers();
    }

    @ShellMethod("Show users in DB.")
    public String showUsers(){
        return userService.showUsers();
    }

    @ShellMethod("Close the Spring application.")
    public void closeApp(){
        int exitCode = SpringApplication.exit(context, () -> 0);
        System.out.println("RestApplication closed");
        System.exit(exitCode);
    }

    @ShellMethod("Restart the Spring application.")
    public void restart() {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);
        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(RestApplication.class, args.getSourceArgs());
        });
        thread.setDaemon(false);
        thread.start();
    }

}
