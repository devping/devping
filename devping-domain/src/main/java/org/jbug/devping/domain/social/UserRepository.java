package org.jbug.devping.domain.social;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nadal on 14. 10. 6.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}