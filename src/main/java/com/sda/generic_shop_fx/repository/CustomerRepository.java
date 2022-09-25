package com.sda.generic_shop_fx.repository;


import com.sda.generic_shop_fx.dto.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private static final SessionFactory factory = SessionManager.getSessionFactory();


    public Customer createCustomer(Customer customer) {
        Transaction transaction = null;

        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        return customer;
    }

    public Customer findCustomerById(Long id){
        Transaction transaction = null;
        Customer customer = null;

        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            customer = session.find(Customer.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        return customer;
    }

    public List<Customer> showAllCustomer(){
        Transaction transaction = null;
        List<Customer> customers = new ArrayList<>();

        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            customers = session.createQuery("FROM Customer", Customer.class).getResultList();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
        return customers;
    }

    public String removeCustomer(Customer customer){
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        return "Customer removed.";
    }

    public String updateCustomer(Customer customer){
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return "Customer updated successfully.";
    }



}
