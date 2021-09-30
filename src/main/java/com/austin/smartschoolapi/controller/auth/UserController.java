package com.austin.smartschoolapi.controller.auth;

import com.austin.smartschoolapi.Dto.LoginRequestDto;
import com.austin.smartschoolapi.model.auth.Role;
import com.austin.smartschoolapi.model.auth.User;
import com.austin.smartschoolapi.service.auth.OauthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/v1/credentials")
//@Validated
public class UserController {

    @Autowired
    OauthService oauthService;

    @ApiOperation(value = "Authenticate and Grant User Login Access")
    @PostMapping("/authenticateAndGetUserRoles")
    public ResponseEntity authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        return oauthService.authenticatingUser(loginRequest);

    }


    @ApiOperation(value = "Create a New User")
    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        return oauthService.createUser(user);

    }

    @ApiOperation(value = "Retrieve all Users")
    @GetMapping("/getUsers")
    public List<User> getAllUsers() {
        return oauthService.getUsers();
    }

    @ApiOperation(value = "Get a Unique User by ID")
    @GetMapping("/getUser/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        return oauthService.getUserById(id);
    }

    @ApiOperation(value = "Edit a user by ID")
    @PostMapping("/editUser/{id}")
    public ResponseEntity editUser(@RequestBody User user, @PathVariable("id") Long id) {
        return oauthService.editUser(user, id);
    }

    @ApiOperation(value = "Delete a User Account")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        return oauthService.deleteUser(id);
    }

    @ApiOperation(value = "To change a user Password")
    @PutMapping("/changePassword")
    public ResponseEntity updatePassword(@RequestParam(value = "userId") Long userId, @RequestParam(value = "newPassword") String newPassword) {
        return oauthService.updateUser(userId, newPassword);
    }


//    ROLE ROLE ROLE-------------------------------------ROLE------------------------------------------ROLE ROLE ROLE

    @ApiOperation(value = "Add a new Role")
    @PostMapping("/role/add")
    public ResponseEntity addRole(@RequestBody Role role) {
        return oauthService.addRole(role);
    }

    @ApiOperation(value = "Retrieve all Roles")
    @GetMapping("/role/allRoles")
    public List<Role> getAllRoles() {
        return oauthService.getRoles();
    }

    @ApiOperation(value = "Get a Unique role by ID")
    @GetMapping("/role/{id}")
    public ResponseEntity getRoleById(@PathVariable("id") Long id) {
        return oauthService.getRoleById(id);
    }

    @ApiOperation(value = "Edit a Role by ID")
    @PostMapping("/role/edit/{id}")
    public ResponseEntity editRole(@RequestBody Role role, @PathVariable("id") Long id) {
        return oauthService.editRole(role, id);
    }

    @ApiOperation(value = "Delete a Role ")
    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") Long id) {
        return oauthService.deleteRole(id);
    }


    @ApiOperation(value = "Assign Role(s) to User")
    @PostMapping("/assignRoleToUser")
    public ResponseEntity addRoleToUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        return oauthService.addRoleToUser(userId, roleId);
    }

        @ApiOperation("To delete a Role assigned to a user")
    @DeleteMapping("/deleteAssignedRole/{userId}/{roleId}")
    public ResponseEntity deleteAssignedRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        return oauthService.deleteAssignedRole(userId, roleId);
    }

}
