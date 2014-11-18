package org.jbug.devping.domain.social;

import org.jbug.devping.domain.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nadal on 14. 10. 6.
 */
public interface UserRepository extends JpaRepository<UserVo, Long> {

    public UserVo findByEmail(String email);
}