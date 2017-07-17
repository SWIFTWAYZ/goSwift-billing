package com.swiftwayz.service.user;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.user.Authority;
import com.swiftwayz.domain.user.User;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by sydney on 2017/07/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoSwiftApplication.class)
@ActiveProfiles("dev")
@Transactional
public class UserServiceIntTest {

    @Autowired
    private UserService userService;


    @Test
    public void should_create_user(){

        User user = new User();
        user.setFirstName("Sydney");
        user.setLastName("Chauke");
        user.setIdNumber(123456789123L);
        user.setCellNumber("0727744629");
        user.setEmail("sm@gmail.com");

        User newUser = userService.saveUser(user);

        assertThat(newUser.getId()).isNotZero();
        assertThat(newUser.getAuthorities()).hasSize(1);
        assertThat(newUser.getAuthorities())
                .extracting(Authority::getCode)
                .contains("USER");
    }

    @Test
    public void should_get_user_and_authorities(){

        String email = "test@goswfit.com";
        User user = userService.findByEmail(email);

        Set<Authority> authorities = user.getAuthorities();

        assertThat(authorities).isNotEmpty();
        assertThat(authorities).extracting(Authority::getCode).contains("ADMIN");
    }

}
