package com.example.onlineresumecreator;

import com.example.onlineresumecreator.api.UserResource;
import com.example.onlineresumecreator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OnlineResumeCreatorApplication implements CommandLineRunner {

    private UserResource userResource;

    @Autowired
    public OnlineResumeCreatorApplication(UserResource userResource) {
        this.userResource = userResource;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineResumeCreatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User user=new User("Yahia","Elakeed",30,"01552827703","Developer","America","Chicago","yahia.elakeed@gmail.com","yahia","skilled");
//        this.userResource.saveUser(user);
    }
}
