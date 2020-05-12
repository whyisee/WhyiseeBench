package com.bigdata.zookeeper;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/5/12 10:09
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class DigestAuthenticationProviderUsage {
    public static void main(String args[]) throws NoSuchAlgorithmException {
        System.out.println("Test--------10:10--->:"+ DigestAuthenticationProvider.generateDigest("super:super"));
    }
}
