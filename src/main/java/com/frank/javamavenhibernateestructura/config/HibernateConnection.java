package com.frank.javamavenhibernateestructura.config;

import org.hibernate.*;
import org.hibernate.cfg.*;


public class HibernateConnection {
    private static Configuration conn = new Configuration().configure();
    private static SessionFactory sf;
    private static Session session;
    
    public static Session getSession(){
        try{
            sf = conn.buildSessionFactory();
            return session = sf.openSession();
        }catch(Exception ex){
            System.out.println("Error.getSession: "+ex);
            return null;
        }
    }
}
