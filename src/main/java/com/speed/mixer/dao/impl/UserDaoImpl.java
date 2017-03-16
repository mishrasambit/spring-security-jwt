package com.speed.mixer.dao.impl;

import com.speed.mixer.dao.AbstractDao;
import com.speed.mixer.dao.UserDao;
import com.speed.mixer.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sambit on 2/10/2017.
 */


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    @Override
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();
        return users;
    }

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public User findByUserEmail(String email){
        User user = null;
        Criteria criteria = createEntityCriteria();
        Criterion emailidcheck = Restrictions.eq("email",email );
        criteria.add(emailidcheck);
        List<User> userList = criteria.list();
        if(userList!=null && userList.size()!=0){
            if(userList.size()>1) {
                System.out.println("Multiple record selected::"+userList.size());
                user = userList.get(0);
            }else{
                user = userList.get(0);
            }
        }else{
            System.out.println("No record selected::");
        }
        return user;
    }
}
