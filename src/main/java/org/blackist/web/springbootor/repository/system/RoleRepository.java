package org.blackist.web.springbootor.repository.system;

import org.blackist.web.springbootor.model.entity.system.Role;
import org.blackist.web.springbootor.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

}
