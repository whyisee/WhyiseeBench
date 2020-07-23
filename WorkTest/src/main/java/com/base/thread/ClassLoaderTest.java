package com.base.thread;

import java.util.WeakHashMap;

public class ClassLoaderTest {
    private boolean loadDefaults = true;

    /**
     * Configuration objects
     */
    private static final WeakHashMap<ClassLoaderTest,Object> REGISTRY =
            new WeakHashMap<ClassLoaderTest,Object>();

    private ClassLoader classLoader;
    {
        classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoaderTest.class.getClassLoader();
        }
    }

    public ClassLoaderTest(boolean loadDefaults) {
        this.loadDefaults = loadDefaults;

        synchronized(ClassLoaderTest.class) {
            REGISTRY.put(this, null);
        }
    }
}
