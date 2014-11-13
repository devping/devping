package org.jbug.devping.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jbug.devping.domain.social.Role;
import org.jbug.devping.domain.social.SocialMediaService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nadal on 14. 10. 6.
 */
public class UserVo extends SocialUser {

    private Long id;

    private String firstName;

    private String lastName;

    private Set<String> personalTagList;

    private Role role;

    private SocialMediaService socialSignInProvider;

    public UserVo(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<String> getPersonalTagList() {
        return personalTagList;
    }

    public Role getRole() {
        return role;
    }

    public SocialMediaService getSocialSignInProvider() {
        return socialSignInProvider;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", getUserId())
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("personalTagList", personalTagList)
                .append("role", role)
                .append("socialSignInProvider", socialSignInProvider)
                .toString();
    }

    public static class Builder {

        private Long id;

        private String userId;

        private String firstName;

        private String lastName;

        private String password;

        private Set<String> personalTagList;

        private Role role;

        private SocialMediaService socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);

            return this;
        }

        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }


        public Builder personalTagList(Set<String> personalTagList) {
            this.personalTagList = personalTagList;
            return this;
        }

        public UserVo build() {
            UserVo user = new UserVo(userId, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.personalTagList = personalTagList;
            user.socialSignInProvider = socialSignInProvider;

            return user;
        }
    }
}
