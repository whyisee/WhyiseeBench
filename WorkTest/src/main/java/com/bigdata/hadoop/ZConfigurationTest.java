package com.bigdata.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ZConfigurationTest {
    Configuration conf=new Configuration();

    @Before
    public void setUp() throws Exception {
        conf.addResource("conf/configuration-1.xml");
    }

    @Test
    public void main() {
        assertThat(conf.get("color"),is("yellow"));
        assertThat(conf.get("color2","yellow"),is("yellow"));
        assertThat(conf.get("size-weight","yellow"),is("10-100"));


    }
}