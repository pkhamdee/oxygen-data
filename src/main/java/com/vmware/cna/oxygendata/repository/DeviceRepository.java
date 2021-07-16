package com.vmware.cna.oxygendata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmware.cna.oxygendata.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>, DeviceRepositoryCustom {
    
}
