package ua.lviv.office.service.unit;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.lviv.office.categories.UnitTest;
import ua.lviv.office.config.TestBaseConfigClass;
import ua.lviv.office.entity.Role;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.UserService;


@Category(UnitTest.class)
@ContextConfiguration(classes = TestBaseConfigClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserServiceImplsTest {
    @Autowired
    private UserService userService;

    @Before
    public void init(){
        User user=new User();
        user.setEmail("test@mail.com");
        user.setPassword("123456");
        user.setFirstName("Ivan");
        user.setLastName("Test");
        user.setRole(Role.ADMINISTRATOR);
        user.setRoleConfirmed(false);
        userService.saveUser(user);
    }
    @After
    public void destroy(){
        userService.deleteAllUsers();
    }

    @Test
    public void testFindUserByEmail(){
        Assert.assertEquals("Ivan",userService.findUserByEmail("test@mail.com").getFirstName());
    }
    @Test
    public void testFindAllUsers(){
        Assert.assertEquals(1L,userService.findAllUsers().size());
    }

    @Test
    public void testFindUserById(){
        User user=userService.findUserByEmail("test@mail.com");
        Assert.assertEquals("test@mail.com",userService.findUserById(user.getId()).getEmail());
    }

    @Test
    public void testFindUserByLastName(){
        Assert.assertEquals("test@mail.com",userService.findUserByLastName("Test").getEmail());
    }
    @Test
    public void testUpdateUserPassword(){
        String correct_password="123456";
        String new_password="qwerty123456";
        Assert.assertEquals(true,  userService.updateUserPassword("test@mail.com",correct_password,new_password));
}
    @Test
    public void testFindUsersByRole(){
        Assert.assertEquals(1,userService.findUsersByRole(Role.ADMINISTRATOR).size());
    }
    @Test
    public void testUsersNonConfirmedRole(){
        Assert.assertEquals(1,userService.usersNonConfirmedRole().size());
    }
    @Test
    public void testChangeUserRole(){
        User user=userService.findUserByEmail("test@mail.com");
        userService.changeUserRole(user.getId(),Role.MANAGER);
        Assert.assertEquals(Role.MANAGER,userService.findUserByEmail("test@mail.com").getRole());
    }
}
