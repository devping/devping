package org.jbug.devping.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by nadal on 2014. 10. 4..
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor<Member> {
}
