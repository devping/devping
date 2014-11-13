package org.jbug.devping.domain.social;

import org.jbug.devping.utils.StringUtil;
import org.jbug.devping.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by nadal on 14. 10. 6.
 */
public class SecurityUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    public static void logInUser(User user) {
        LOGGER.info("Logging in user: {}", user);

        UserVo userVo = UserVo.getBuilder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .personalTagList(StringUtil.arrayToSet(user.getTags(), ","))
                .userId(user.getEmail())
                .build();
        LOGGER.debug("Logging in principal: {}", userVo);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userVo, null, userVo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("User: {} has been logged in.", userVo);
    }
}
