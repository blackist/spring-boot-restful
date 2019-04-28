package org.blackist.web.springbootor.repository.system;

import org.blackist.web.springbootor.model.entity.system.User;
import org.blackist.web.springbootor.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    User findUserByUsername(String username);
}
