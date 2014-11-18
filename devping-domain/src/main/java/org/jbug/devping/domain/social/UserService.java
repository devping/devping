package org.jbug.devping.domain.social;

import org.jbug.devping.domain.UserVo;

/**
 * Created by nadal on 14. 10. 6.
 */
public interface UserService {

    /**
     * Creates a new user account to the service.
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     * @throws DuplicateEmailException Thrown when the email address is found from the database.
     */
    public UserVo registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}