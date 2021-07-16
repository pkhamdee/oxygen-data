package com.vmware.cna.oxygendata.service;

import com.vmware.cna.oxygendata.exception.ResourceNotFoundException;
import com.vmware.cna.oxygendata.model.Device;
import com.vmware.cna.oxygendata.model.User;
import com.vmware.cna.oxygendata.repository.DeviceRepository;
import com.vmware.cna.oxygendata.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DeviceService {

    private DeviceRepository deviceRepository;
    private UserRepository userRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Device createDevice(Device device) {
        Device d = new Device();
        d.setBarcode(device.getBarcode());
        d.setId(device.getId());
        d.setName(device.getName());
        d.setSerialNo(device.getSerialNo());
        d.setStatus(device.getStatus());
        d.setStatusDate(device.getStatusDate());
        d.setType(device.getType());
        d.setUser(device.getUser());

        return deviceRepository.save(d);

    }

    @Transactional
    public Device updateDevice(Long id, Device device) {

        if (deviceRepository.findById(id).isPresent()) {

            Device findDevice = deviceRepository.findById(id).get();
            findDevice.setBarcode(device.getBarcode());
            findDevice.setName(device.getName());
            findDevice.setSerialNo(device.getSerialNo());
            findDevice.setStatus(device.getStatus());
            findDevice.setStatusDate(device.getStatusDate());
            findDevice.setType(device.getType());

            User saveUser = null;

            if (device.getUser() != null && device.getUser().getId() != null) { // found user, request is to map device with user

                if (userRepository.findById(device.getUser().getId()).isPresent()) {

                    User existUser = userRepository.findById(device.getUser().getId()).get();

                    User newUser = new User();
                    newUser.setId(existUser.getId());
                    newUser.setAddress(existUser.getAddress());
                    newUser.setFirstName(existUser.getFirstName());
                    newUser.setGender(existUser.getGender());
                    newUser.setLastName(existUser.getLastName());
                    newUser.setLocation(existUser.getLocation());
                    newUser.setPasswd(existUser.getPasswd());
                    newUser.setPhone(existUser.getPhone());
                    newUser.setServiceDate(existUser.getServiceDate());
                    newUser.setSeverity(existUser.getSeverity());
                    newUser.setServiceRequestDate(existUser.getServiceRequestDate());
                    newUser.setStatusDate(existUser.getStatusDate());
                    newUser.setStatus(existUser.getStatus());
                    newUser.setType(existUser.getType());
                    newUser.setUserName(existUser.getUserName());

                    saveUser = userRepository.save(newUser);
                    findDevice.setUser(saveUser);

                } else { // notfound user
                    throw new ResourceNotFoundException("The specified User id does not found");
                }

            } else { // get from database

                if (findDevice.getUser() != null && userRepository.findById(findDevice.getUser().getId()).isPresent()) {

                    User findUser = userRepository.findById(findDevice.getUser().getId()).get();

                    findUser.setAddress(findDevice.getUser().getAddress());
                    findUser.setFirstName(findDevice.getUser().getFirstName());
                    findUser.setGender(findDevice.getUser().getGender());
                    findUser.setLastName(findDevice.getUser().getLastName());
                    findUser.setLocation(findDevice.getUser().getLocation());
                    findUser.setPasswd(findDevice.getUser().getPasswd());
                    findUser.setPhone(findDevice.getUser().getPhone());
                    findUser.setServiceDate(findDevice.getUser().getServiceDate());
                    findUser.setSeverity(findDevice.getUser().getSeverity());
                    findUser.setServiceRequestDate(findDevice.getUser().getServiceRequestDate());
                    findUser.setStatusDate(findDevice.getUser().getStatusDate());
                    findUser.setStatus(findDevice.getUser().getStatus());
                    findUser.setType(findDevice.getUser().getType());
                    findUser.setUserName(findDevice.getUser().getUserName());

                    saveUser = userRepository.save(findUser);
                    findDevice.setUser(saveUser);
                }
            }

            return deviceRepository.save(findDevice);

        } else {
            throw new ResourceNotFoundException("The specified Device is not found ");
        }

    }
}
