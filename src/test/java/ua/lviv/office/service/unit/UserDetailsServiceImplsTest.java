package ua.lviv.office.service.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.lviv.office.categories.UnitTest;
import ua.lviv.office.config.TestBaseConfigClass;
import ua.lviv.office.entity.Role;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.UserService;
import ua.lviv.office.service.serviceImpls.UserDetailsServiceImpls;

@Category(UnitTest.class)
@ContextConfiguration(classes = TestBaseConfigClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserDetailsServiceImplsTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpls userDetailsService;

    @Before
    public void init(){
        User user=new User();
        user.setEmail("some@email.com");
        user.setPassword("123456");
        user.setRole(Role.ADMINISTRATOR);
        userService.saveUser(user);
    }
    @After
    public void destroy(){
        userService.deleteAllUsers();
    }
    @Test
    public void testLoadUserByUsername(){
        User user=userService.findUserByEmail("some@email.com");
        Assert.assertEquals(user.getEmail(),userDetailsService.loadUserByUsername("some@email.com").getUsername());
    }
    @Test(expected = UsernameNotFoundException.class)
    public void testLoadNullUserByUsername(){
        userDetailsService.loadUserByUsername("test@email.com");
    }


}
