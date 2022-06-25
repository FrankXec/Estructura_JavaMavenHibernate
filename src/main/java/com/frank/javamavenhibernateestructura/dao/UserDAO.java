package com.frank.javamavenhibernateestructura.dao;

import com.frank.javamavenhibernateestructura.config.HibernateConnection;
import com.frank.javamavenhibernateestructura.dto.UserDTO;
import com.frank.javamavenhibernateestructura.interfaces.ICRUD;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;

//Query query = session.createQuery("") //crear query
//entitys = (cast) query.uniqueResult(); // Query query = session.createQuery("from UserDTO WHERE id = 1", UserDTO.class);
//Object[] object = (Object[]) query.uniqueResult(); // Query query = session.createQuery("select nickName, email, password from UserDTO", UserDTO.class);
//SQLQuery slqQuery = session.createSQLQuery("") //sql normal
//sqlQuery.addEntity(Entity.class)
//transform if not need all columns
//sqlQuery.setResultTransformer(Criteria.Alias_to_entity_map)
//List entity = query.list();
//in for -> Map map = (Map)item; -> use map;
public class UserDAO implements ICRUD<UserDTO>{
    private UserDTO entity = null;
    private Session session = null;
    private boolean status = false;
    private List<UserDTO> entitys = null;

    @Override
    public List<UserDTO> getAll() {
        try{
            session = HibernateConnection.getSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserDTO", UserDTO.class);
            entitys = query.list();
            session.getTransaction().commit();
        }catch(Exception ex){
            System.out.println("Error.GetAll.User: "+ex);
        }finally{
            session.close();
        }
        return entitys;
    }

    @Override
    public UserDTO getOneById(String id) {
        try{
            session = HibernateConnection.getSession();
            entity = (UserDTO) session.get(UserDTO.class, Long.parseLong(id));
        }catch(Exception ex){
            System.out.println("Error.GetOneById.User: "+ex);
        }finally{
            session.close();
        }
        return entity;
    }

    @Override
    public boolean create(UserDTO entity) {
        try{
            session = HibernateConnection.getSession();
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            //session.detach(object); -> no deja  modificar despues de la insercion
            transaction.commit();
            status = true;
        }catch(Exception ex){
            System.out.println("Error.Create.User: "+ex);
        }finally{
            session.close();
        }
        return status;
    }

    @Override
    public boolean update(UserDTO entity) {
        try{
            session = HibernateConnection.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            //session.detach(object); -> no deja  modificar despues de la insercion
            transaction.commit();
            status = true;
        }catch(Exception ex){
            System.out.println("Error.Update.User: "+ex);
        }finally{
            session.close();
        }
        return status;
    }

    @Override
    public boolean delete(String id) {
        try{
            session = HibernateConnection.getSession();
            Transaction transaction = session.beginTransaction();
            UserDTO user = session.get(UserDTO.class, Long.parseLong(id));
            session.remove(user);
            //session.detach(object); -> no deja  modificar despues de la insercion
            transaction.commit();
            status = true;
        }catch(Exception ex){
            System.out.println("Error.Delete.User: "+ex);
        }finally{
            session.close();
        }
        return status;
    }
   
}
