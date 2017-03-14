package com.speed.mixer.dao;

import com.speed.mixer.config.HibernateTestConfiguration;
import com.speed.mixer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sambit on 3/14/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateTestConfiguration.class}, loader=AnnotationConfigContextLoader.class)
@Transactional
public class TestUserDao {
    @Autowired
    @Qualifier("userDao")
    private UserDao dao;


    @Test
    @Transactional
    @Rollback(true)
    public void test_saveUser(){
        User user = new User();
        user.setEmail("sambit@gmail.com");
        user.setPassword("123456");
        user.setSsoId("abcd12345g");
        user.setFirstName("sambit");
        user.setLastName("mishra");

        dao.saveUser(user);
        List<User> allUsers = dao.findAllUsers();

        assertEquals(1, allUsers.size());
        assertEquals(user.getEmail(), allUsers.get(0).getEmail());

    }
}
