package org.jbug.devping.utils;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * Created by nadal on 2014. 9. 15..
 */
public class ConfigurationProperties {

    public static final String PROFILE_PROPERTY_NAME = AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;
    public static final String NO_PROFILE_ERROR_MESSAGE = "Profile이 지정되지 않았습니다.";
    public static final String TOO_MANY_PROFILES_ERROR_MESSAGE = "Profile이 너무 많이 지정돼 있습니다. 1개만 허용됩니다. 현재 프로파일 : ";

    private Environment environment;

    public ConfigurationProperties(Environment environment) {
        this.environment = environment;
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

    public <T> T getProperty(String key, Class<T> targetType) {
        return environment.getProperty(key, targetType);
    }

    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return environment.getProperty(key, targetType, defaultValue);
    }

    public String getActiveProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (StringUtils.isEmpty(activeProfiles)) {
            throw new IllegalStateException(NO_PROFILE_ERROR_MESSAGE);
        }

        if (activeProfiles.length > 1) {
            String msg = getTooManyActiveProfileErrorMessage(activeProfiles);
            throw new IllegalStateException(msg);
        }

        return activeProfiles[0];
    }

    private String getTooManyActiveProfileErrorMessage(String[] activeProfiles) {
        StringBuilder sb = new StringBuilder();
        sb.append(TOO_MANY_PROFILES_ERROR_MESSAGE);

        for (int i = 0; i < activeProfiles.length; i++) {
            sb.append(activeProfiles[i]);
            if (i < activeProfiles.length - 1) {
                sb.append(',');
            }
        }

        return sb.toString();
    }
}
