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
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }
    
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Long userId) {

        Optional <User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        }

    }    

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable Long userId,
                                   @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId)
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

                    return userRepository.save(user);

                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }
}
