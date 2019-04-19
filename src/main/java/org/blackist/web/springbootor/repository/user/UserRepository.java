package org.blackist.web.springbootor.repository.user;

import org.blackist.web.springbootor.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
