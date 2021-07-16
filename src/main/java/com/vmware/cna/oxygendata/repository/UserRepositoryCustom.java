package com.vmware.cna.oxygendata.repository;

import com.vmware.cna.oxygendata.model.User;

public interface UserRepositoryCustom {
    public User getUserByUserName(String userName);
}
