package com.example.demo.testcontainers.dao;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.testcontainers.entity.Customer;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerDao {

    private SessionFactory sessionFactory;

    @Transactional
    public List<Customer> findByLastName(final String lastName) {

        Query<Customer> query = sessionFactory.getCurrentSession()
                                               .createQuery("From Customer Where lastName= :lastName")
                                               .setParameter("lastName", lastName);

        return query.list();
    }

    @Transactional
    public Optional<Customer> findById(Long customerId){

        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Customer.class, customerId));
    }

    @Transactional
    public Long save(Customer customer){

        return (Long)sessionFactory.getCurrentSession().save(customer);
    }

}