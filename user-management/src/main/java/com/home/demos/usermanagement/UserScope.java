package com.home.demos.usermanagement;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserScope implements Scope {
    private Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<>());
    private Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<String, Runnable>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
        String userName = ((ServletRequestAttributes) attributes).getRequest().getHeader("userName"); //todo change to principal
        if(!scopedObjects.containsKey(userName)) {
            Object object = objectFactory.getObject();

            if (object instanceof UserContext) {
                ((UserContext)object).setName(userName);
            }

            scopedObjects.put(userName, object);
        }
        return scopedObjects.get(userName);
    }

    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return scopedObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        destructionCallbacks.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}