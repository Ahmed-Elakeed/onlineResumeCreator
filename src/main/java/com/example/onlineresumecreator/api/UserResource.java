package com.example.onlineresumecreator.api;

import com.example.onlineresumecreator.model.*;
import com.example.onlineresumecreator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(path = "/skills")
    public ResponseEntity<List<Skill>> getUserSkills() {
        return ResponseEntity.ok(userService.getCurrentUser().getSkills().stream().collect(Collectors.toList()));
    }

    @GetMapping(path = "/projects")
    public ResponseEntity<List<Project>> getUserProjects() {
        return ResponseEntity.ok(userService.getCurrentUser().getProjects().stream().collect(Collectors.toList()));
    }

    @GetMapping(path = "/education")
    public ResponseEntity<List<Education>> getUserEducation() {
        return ResponseEntity.ok(userService.getCurrentUser().getEducations().stream().collect(Collectors.toList()));
    }

    @GetMapping(path = "/experience")
    public ResponseEntity<List<Experience>> getUserExperience() {
        return ResponseEntity.ok(userService.getCurrentUser().getExperiences().stream().collect(Collectors.toList()));
    }

    @GetMapping(path = "/courses")
    public ResponseEntity<List<Course>> getUserCourses() {
        return ResponseEntity.ok(userService.getCurrentUser().getCourses().stream().collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(this.userService.save(user).getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
