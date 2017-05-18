package com.swiftwayz.billing.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by sydney on 2017/05/18.
 */
@Component
public class AccountRepositoryImpl implements AccountRepositoryCostum {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean userExist(Long userId){
        try {
            entityManager.createNativeQuery("SELECT u.id FROM app_user u WHERE u.id = " + userId).getSingleResult();
            return true;
        } catch (NoResultException ex){
            return false;
        }
    }
}
