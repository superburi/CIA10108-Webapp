package com.howard.rentalmytrack.vo;

import com.howard.util.HibernateUtil;
import com.sun.xml.bind.api.impl.NameConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import java.sql.Date;
import java.sql.Timestamp;

public class Test {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            RentalMyTrackVo_ORM rentalMyTrackVoOrm = new RentalMyTrackVo_ORM();
            rentalMyTrackVoOrm.setrNo(5001);
            rentalMyTrackVoOrm.setMemNo(6);

            Timestamp now = new Timestamp(System.currentTimeMillis());
            rentalMyTrackVoOrm.setrTrackTime(now);
            Date date = new Date(System.currentTimeMillis());
            rentalMyTrackVoOrm.setExpRentalDate(date);

            session.save(rentalMyTrackVoOrm);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (tx == null) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.shutdown();
        }

    }

}
