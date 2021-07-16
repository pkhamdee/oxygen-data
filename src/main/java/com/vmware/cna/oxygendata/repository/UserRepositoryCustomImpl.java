package com.vmware.cna.oxygendata.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import com.vmware.cna.oxygendata.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    public Page <User> findUserByFirstName(String firstName,Pageable page){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        Path<String> firstNamePath = user.get("firstName");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(firstNamePath, "%" + firstName + "%"));

        query.select(user)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<User> queryEntity = entityManager.createQuery(query);    
        int totalRows = queryEntity.getResultList().size();
    
        queryEntity.setFirstResult(page.getPageNumber() * page.getPageSize());
        queryEntity.setMaxResults(page.getPageSize());
    
        Page<User> result = new PageImpl<User>(queryEntity.getResultList(), page, totalRows);    

        return result;
    }

    public Page <User> findUserByType(String type, Pageable page){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        Path<String> typePath = user.get("type");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(typePath, type));

        query.select(user)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<User> queryEntity = entityManager.createQuery(query);    
        int totalRows = queryEntity.getResultList().size();
    
        queryEntity.setFirstResult(page.getPageNumber() * page.getPageSize());
        queryEntity.setMaxResults(page.getPageSize());
    
        Page<User> result = new PageImpl<User>(queryEntity.getResultList(), page, totalRows);    

        return result;
    }

    public Integer getTotalUserByType(String type){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> device = query.from(User.class);

        Path<String> typePath = device.get("type");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(typePath, type));

        query.select(device)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList().size();
    }
}
