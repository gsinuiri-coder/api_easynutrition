package com.thenews.userprofile.controller;


import com.thenews.userprofile.domain.model.User;
import com.thenews.userprofile.domain.service.UserService;
import com.thenews.userprofile.resource.SaveUserResource;
import com.thenews.userprofile.resource.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="Users", description = "User API")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable) {

        Page<User> usersPage = userService.getAllUsers(pageable);
        List<UserResource> resources = usersPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{userId}")
    public UserResource getUserById(@PathVariable (value = "userId") Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @PostMapping("/users")
    public UserResource createUser(
            @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));

    }

    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable Long userId,
                                   @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(
                userService.updateUser(userId, user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }


}
