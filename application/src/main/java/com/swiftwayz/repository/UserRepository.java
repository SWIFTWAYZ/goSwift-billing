package com.swiftwayz.repository;

import com.swiftwayz.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sydney on 2017/04/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
