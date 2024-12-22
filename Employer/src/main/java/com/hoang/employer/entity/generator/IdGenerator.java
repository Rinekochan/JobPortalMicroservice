package com.hoang.employer.entity.generator;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

@Slf4j
public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {
        log.info("I am here!");
        return (new ObjectId()).toString();
    }

}
