package com.swiftwayz.service.user;

import com.swiftwayz.domain.user.Authority;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sydney on 2017/07/15.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        user.getAuthorities().add(createUserAuthority());
        return userRepository.save(user);
    }

    private Authority createUserAuthority() {
        Authority authority = new Authority();
        authority.setCode("USER");
        authority.setName("User Role");
        return authority;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
