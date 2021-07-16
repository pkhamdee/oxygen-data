package com.vmware.cna.oxygendata.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import com.vmware.cna.oxygendata.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class DeviceRepositoryCustomImpl implements DeviceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public Page <Device> findDeviceByStatus(String status, Pageable page){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Device> query = cb.createQuery(Device.class);
        Root<Device> device = query.from(Device.class);

        Path<String> statusPath = device.get("status");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(statusPath, status));

        query.select(device)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<Device> queryEntity = entityManager.createQuery(query);    
        int totalRows = queryEntity.getResultList().size();

        queryEntity.setFirstResult(page.getPageNumber() * page.getPageSize());
        queryEntity.setMaxResults(page.getPageSize());

        Page<Device> result = new PageImpl<Device>(queryEntity.getResultList(), page, totalRows);

        return result;
    }

    public Integer getTotalDeviceByStatus(String status){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Device> query = cb.createQuery(Device.class);
        Root<Device> device = query.from(Device.class);

        Path<String> statusPath = device.get("status");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(statusPath, status));

        query.select(device)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList().size();
    }

}
