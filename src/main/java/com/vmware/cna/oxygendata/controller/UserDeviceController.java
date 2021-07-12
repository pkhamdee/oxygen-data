package com.vmware.cna.oxygendata.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vmware.cna.oxygendata.repository.UserDeviceRepository;
import com.vmware.cna.oxygendata.model.UserDevice;
import com.vmware.cna.oxygendata.exception.*;
import java.util.Optional;

@RestController
public class UserDeviceController {

    @Autowired
    private UserDeviceRepository userDeviceRepository;

    @GetMapping("/userdevices")
    public Page<UserDevice> getUserDevices(Pageable pageable){
        return userDeviceRepository.findAll(pageable);
    }
    
    @GetMapping("/userdevice/{userDeviceId}")
    public UserDevice getUserDeviceById(@PathVariable Long userDeviceId) {

        Optional <UserDevice> userDeviceOptional = userDeviceRepository.findById(userDeviceId);

        if (userDeviceOptional.isPresent()) {
            return userDeviceOptional.get();
        } else {
            return userDeviceOptional.orElseThrow(() -> new ResourceNotFoundException("UserDevice not found with id " + userDeviceId));
        }

    }    

    @PostMapping("/userdevice")
    public UserDevice createUserDevice(@Valid @RequestBody UserDevice userDevice) {
        return userDeviceRepository.save(userDevice);
    }

    @PutMapping("/userdevice/{userDeviceId}")
    public UserDevice updateUserDevice(@PathVariable Long userDeviceId,
                                   @Valid @RequestBody UserDevice userDeviceRequest) {
        return userDeviceRepository.findById(userDeviceId)
                .map(userDevice -> {
                    userDevice.setDeviceAddress(userDeviceRequest.getDeviceAddress());
                    userDevice.setDeviceLatLong(userDeviceRequest.getDeviceLatLong());
                    userDevice.setDeviceLocation(userDeviceRequest.getDeviceLocation());
                    userDevice.setEndDate(userDeviceRequest.getEndDate());
                    userDevice.setRemark(userDeviceRequest.getRemark());
                    userDevice.setStartDate(userDeviceRequest.getStartDate());
                    userDevice.setUserId(userDeviceRequest.getUserId());
                    userDevice.setDeviceId(userDeviceRequest.getDeviceId());

                    return userDeviceRepository.save(userDevice);

                }).orElseThrow(() -> new ResourceNotFoundException("UserDevice not found with id " + userDeviceId));
    }

    @DeleteMapping("/userdevice/{userDeviceId}")
    public ResponseEntity<?> deleteUserDevice(@PathVariable Long userDeviceId) {
        return userDeviceRepository.findById(userDeviceId)
                .map(userdevice -> {
                    userDeviceRepository.delete(userdevice);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("UserDevice not found with id " + userDeviceId));
    }
    
}
