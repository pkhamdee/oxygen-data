package com.vmware.cna.oxygendata.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vmware.cna.oxygendata.repository.DeviceRepository;
import com.vmware.cna.oxygendata.model.Device;
import com.vmware.cna.oxygendata.exception.*;
import java.util.Optional;

@RestController
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/devices")
    public Page<Device> getDevice(Pageable pageable){
        return deviceRepository.findAll(pageable);
    }
    
    @GetMapping("/device/{deviceId}")
    public Device getDeviceById(@PathVariable Long deviceId) {

        Optional <Device> deviceOptional = deviceRepository.findById(deviceId);

        if (deviceOptional.isPresent()) {
            return deviceOptional.get();
        } else {
            return deviceOptional.orElseThrow(() -> new ResourceNotFoundException("Device not found with id " + deviceId));
        }

    }    

    @PostMapping("/device")
    public Device createDevice(@Valid @RequestBody Device device) {
        return deviceRepository.save(device);
    }

    @PutMapping("/device/{userId}")
    public Device updateDevice(@PathVariable Long deviceId,
                                   @Valid @RequestBody Device deviceRequest) {
        return deviceRepository.findById(deviceId)
                .map(device -> {
                    device.setName(deviceRequest.getName());
                    device.setType(deviceRequest.getType());
                    device.setStatus(deviceRequest.getStatus());
                    device.setStatusDate(deviceRequest.getStatusDate());
                    device.setBarcode(deviceRequest.getBarcode());

                    return deviceRepository.save(device);

                }).orElseThrow(() -> new ResourceNotFoundException("Device not found with id " + deviceId));
    }

    @DeleteMapping("/device/{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long deviceId) {
        return deviceRepository.findById(deviceId)
                .map(device -> {
                    deviceRepository.delete(device);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Device not found with id " + deviceId));
    }
    
}
