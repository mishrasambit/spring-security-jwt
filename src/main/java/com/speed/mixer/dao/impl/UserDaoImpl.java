package com.speed.mixer.dao.impl;

import com.speed.mixer.dao.AbstractDao;
import com.speed.mixer.dao.UserDao;
import com.speed.mixer.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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


}
