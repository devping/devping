package org.jbug.devping.cache;

import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public interface ITagCache {

    public Set<String> get(String key);

    public void put(String key, Set<String> userListForTag);

    public void remove(String key);

}
