package org.jbug.devping.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.jbug.devping.domain.social.Role;
import org.jbug.devping.domain.social.SocialMediaService;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nadal on 14. 10. 6.
 */
@Entity
@Table(name = "user_accounts")
public class UserVo extends SocialUser {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", length = 100,nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "password", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaService socialSignInProvider;

    @Column(name = "creation_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTime;

    @Column(name = "modification_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;

    @Column(name = "tags", nullable = false)
    private String tags;

    @Version
    private long version;

    public DateTime getCreationTime() {
        return creationTime;
    }

    public DateTime getModificationTime() {
        return modificationTime;
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        DateTime now = DateTime.now();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = DateTime.now();
    }

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

    public String getTags() {
        return tags;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public SocialMediaService getSocialSignInProvider() {
        return socialSignInProvider;
    }

    @Override
    public String getUserId() {
        return getEmail();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("email", getUserId())
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("tags", tags)
                .append("role", role)
                .append("socialSignInProvider", socialSignInProvider)
                .toString();
    }

    public static class Builder {

        private Long id;

        private String email;

        private String firstName;

        private String lastName;

        private String password;

        private String tags;

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

        public Builder email(String email) {
            this.email = email;
            return this;
        }


        public Builder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public UserVo build() {
            UserVo user = new UserVo(email, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.tags = tags;
            user.socialSignInProvider = socialSignInProvider;

            return user;
        }
    }
}
