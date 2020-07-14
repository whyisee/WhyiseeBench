package com.base.utils;

import java.util.*;

public class AuthManager {
    public enum AuthType{INSERT,UPDATE,DELETE,ALTER,DROP,CREATE }
    public void addAuth(String oldAuth, Set<AuthType> authTypes){
        System.out.println("===test===>"+oldAuth);
        List<String> oldAuths = Arrays.asList(oldAuth.split(","));
        oldAuths.forEach(n -> authTypes.add(AuthType.valueOf(n)));
        System.out.println("===test===>"+authTypes);
    }

    public void removeAuth(String oldAuth,Set<AuthType> authTypes){
        System.out.println("===test===>"+oldAuth);
        List<String> oldAuths = Arrays.asList(oldAuth.split(","));
        List<String> arrList = new ArrayList(oldAuths);
        Iterator<AuthType> removeAuthType = authTypes.iterator();
        removeAuthType.forEachRemaining(n -> arrList.remove(n.toString()));
        System.out.println("===test===>"+arrList);
    }

}
