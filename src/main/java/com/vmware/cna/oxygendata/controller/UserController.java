package com.vmware.cna.oxygendata.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vmware.cna.oxygendata.repository.UserRepository;
import com.vmware.cna.oxygendata.model.User;
import com.vmware.cna.oxygendata.exception.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }
    
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }    

    @GetMapping("/user/username/{userName}")
    public User getUserByUserName(@PathVariable String userName) {

        User user = userRepository.getUserByUserName(userName);

        if (user != null ){
            return user;
        } else {
            throw new ResourceNotFoundException("User not found with userName " + userName);
        }

    }  

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id,
                                   @Valid @RequestBody User userRequest) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(userRequest.getFirstName());
                    user.setLastName(userRequest.getLastName());
                    user.setGender(userRequest.getGender());
                    user.setType(userRequest.getType());
                    user.setAddress(userRequest.getAddress());
                    user.setPhone(userRequest.getPhone());
                    user.setStatus(userRequest.getStatus());
                    user.setPasswd(userRequest.getPasswd());
                    user.setStatusDate(userRequest.getStatusDate());
                    user.setLocation(userRequest.getLocation());
                    user.setServiceRequestDate(userRequest.getServiceRequestDate());
                    user.setServiceDate(userRequest.getServiceDate());
                    user.setSeverity(userRequest.getSeverity());
                    user.setUserName(userRequest.getUserName());

                    return userRepository.save(user);
                    
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }
}
