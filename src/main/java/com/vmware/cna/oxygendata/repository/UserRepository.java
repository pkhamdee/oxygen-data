package com.vmware.cna.oxygendata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vmware.cna.oxygendata.model.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    
}
