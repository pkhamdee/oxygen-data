package com.vmware.cna.oxygendata.repository;

import com.vmware.cna.oxygendata.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    public User getUserByUserName(String userName);
    public Page <User> findUserByFirstName(String firstName,Pageable page);
    public Page <User> findUserByType(String type, Pageable page);
    public Integer getTotalUserByType(String type); 
}
