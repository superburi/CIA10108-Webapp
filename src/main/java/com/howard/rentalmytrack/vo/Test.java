package com.howard.rentalmytrack.vo;

import com.howard.rentalorder.vo.RentalOrderVo_ORM;
import com.howard.util.HibernateUtil;
import com.roger.member.vo.MemberVO;
import com.sun.xml.bind.api.impl.NameConverter;
import com.yu.rental.model.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import java.lang.reflect.Member;
import java.sql.Date;
import java.sql.Timestamp;

public class Test {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

//            Rental rental = session.get(Rental.class, 5001);
//            MemberVO memberVO = session.get(MemberVO.class, 5);
//
//            RentalMyTrackVo_ORM rentalMyTrackVoOrm = session.get(RentalMyTrackVo_ORM.class,
//                    new RentalMyTrackVo_ORM.CompositeTrack(rental, memberVO));
//
//            System.out.println(rentalMyTrackVoOrm.getMemberVO().getmName());

            RentalOrderVo_ORM rentalOrderVoOrm = session.get(RentalOrderVo_ORM.class, 13);
            System.out.println(rentalOrderVoOrm);

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
