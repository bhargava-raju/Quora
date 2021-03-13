package com.upgrad.quora.service.dao;


import com.upgrad.quora.service.entity.UserAuthTokenEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity getUser(final String userUuid){
        try {
            return entityManager.createNamedQuery("userByUuid", UserEntity.class)
                    .setParameter("uuid", userUuid)
                    .getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }

    public UserAuthTokenEntity getUserAuthToken(final String accessToken){
        try {
            return entityManager.createNamedQuery("userAuthTokenByAccessToken",UserAuthTokenEntity.class)
                    .setParameter("accessToken", accessToken).getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }

    public void deleteUser(final String userUuid){
        UserEntity userEntity = getUser(userUuid);
        entityManager.remove(userEntity);
    }}