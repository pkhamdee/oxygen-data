package com.vmware.cna.oxygendata.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vmware.cna.oxygendata.repository.DeviceRepository;
import com.vmware.cna.oxygendata.service.DeviceService;
import com.vmware.cna.oxygendata.model.Device;
import com.vmware.cna.oxygendata.exception.*;

@CrossOrigin
@RestController
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public Page<Device> getDevice(Pageable pageable){
        return deviceRepository.findAll(pageable);
    }

    @GetMapping("/device/status/{status}")
    public Page <Device> getDeviceByStatus(Pageable pageable, @PathVariable String status){
        return deviceRepository.findDeviceByStatus(status, pageable);
    }

    @GetMapping("/device/status/{status}/total")
    public Integer getTotalDeviceByStatus(@PathVariable String status){
        Integer total = deviceRepository.getTotalDeviceByStatus(status);
        if(total != null) {
          return total;
        } else {
            return 0;
        }
    }
    
    @GetMapping("/device/{id}")
    public Device getDeviceById(@PathVariable Long id) {
            return deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found with id " + id));
    }    

    @PostMapping("/device")
    public Device createDevice(@Valid @RequestBody Device device) {

        return deviceService.createDevice(device);

    }

    @PutMapping("/device/{id}")
    public Device updateDevice(@PathVariable Long id,
                                   @Valid @RequestBody Device deviceRequest) {

        return deviceService.updateDevice(id, deviceRequest);
    }

    @DeleteMapping("/device/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        return deviceRepository.findById(id)
                .map(device -> {
                    deviceRepository.delete(device);
                    return ResponseEntity.ok("Successfully deleted the specified organization");
                }).orElseThrow(() -> new ResourceNotFoundException("Device not found with id " + id));
    }
    
}
