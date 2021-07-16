package com.vmware.cna.oxygendata.repository;

import com.vmware.cna.oxygendata.model.Device;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceRepositoryCustom {
    public Page <Device> getDeviceByStatus(String status, Pageable page);
    public Integer getTotalDeviceByStatus(String status); 
}
