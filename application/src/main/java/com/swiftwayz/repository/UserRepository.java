package com.swiftwayz.repository;

import com.swiftwayz.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sydney on 2017/04/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
