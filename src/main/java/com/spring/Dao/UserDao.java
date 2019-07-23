package com.spring.Dao;

import com.spring.Model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<UserEntity> getUserList() {
        List<UserEntity> userList= getSessionFactory().createQuery("from UserEntity where active=1").list();
     return userList;
    }

    public void addUser(UserEntity user)
    {
        NativeQuery q= getSessionFactory().createSQLQuery("insert user(name, surname, mail, phone) values(?,?,?,?)");
        q.setParameter(1,user.getName());
        q.setParameter(2,user.getSurname());
        q.setParameter(3,user.getMail());
        q.setParameter(4,user.getPhone());
        q.executeUpdate();
    }

    public void updateUser(UserEntity user) {
       NativeQuery q= getSessionFactory().createSQLQuery("update user set name=?, surname=?, mail=?, phone=? where id=?");
        q.setParameter(1,user.getName());
        q.setParameter(2,user.getSurname());
        q.setParameter(3,user.getMail());
        q.setParameter(4,user.getPhone());
        q.setParameter(5,user.getId());
        q.executeUpdate();
    }

    public void deleteUser(int id) {
       Query q=  getSessionFactory().createQuery("update UserEntity  set active=0 where id=:id");
       q.setParameter("id",id);
       q.executeUpdate();

    }
    public UserEntity getUserById(int id) {
        UserEntity user= getSessionFactory().get(UserEntity.class,id);
        return  user;
    }

    public UserEntity getUserByMail(String mail){
        UserEntity user;
        Query q=  getSessionFactory().createQuery("from  UserEntity  where mail=:mail");
        q.setParameter("mail",mail);
       List<UserEntity> userEntityList= q.getResultList();
       if(userEntityList.size()==0){
           user=null;
       } else{
           user= (UserEntity) q.getResultList().get(0);
       }
        return  user;
    }

}
