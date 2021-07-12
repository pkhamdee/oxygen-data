package com.vmware.cna.oxygendata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vmware.cna.oxygendata.model.UserDevice;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
    
}
