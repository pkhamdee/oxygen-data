package com.vmware.cna.oxygendata.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import com.vmware.cna.oxygendata.model.User;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    
    @PersistenceContext
    private EntityManager entityManager;

    public User getUserByUserName(String userName){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        Path<String> userNamePath = user.get("userName");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(userNamePath, userName));

        query.select(user)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getSingleResult();
    }
}
