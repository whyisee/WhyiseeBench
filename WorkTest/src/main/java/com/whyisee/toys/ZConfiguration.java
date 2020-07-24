package com.whyisee.toys;

import com.base.annotation.InterfaceAudience;
import com.base.annotation.InterfaceStability;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@InterfaceAudience.Private
@InterfaceStability.Stable
public class ZConfiguration implements Iterable<Map.Entry<String,String>> , Writable {

    private static final Logger LOG = LoggerFactory.getLogger(ZConfiguration.class);
    private static final Logger LOG_DEPRECATION = LoggerFactory.getLogger("com.whyisee.toys.ZConfiguration.deprecation");
    //private static final Log LOG_2 = LogFactory.getLog(ZConfiguration.class);


    private static final Set<String> TAGS = ConcurrentHashMap.newKeySet();

    private boolean quietmode = true;
    private static final String DEFAULT_STRING_CHECK = "testingforemptydefaultvalue";
    private static boolean restrictSystemPropsDefault = false;
    private boolean retrictSystemProps = restrictSystemPropsDefault;
    private boolean allowNullValueProperties = false;

    private ArrayList<Resource> resources = new ArrayList<Resource>();
    static final String UNKNOWN_RESOURCE = "Unknown";

    private Set<String> finalParameters = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
    private Set<String> finalParameters2 = ConcurrentHashMap.newKeySet();

    //private



    {
        TAGS.add("1");
        finalParameters2.add("2");
    }

    public static Set<String> getTAGS() {
        return TAGS;
    }

    private static class Resource {
        private final Object resource;
        private final String name;
        private final boolean restrictParser;

        public Resource(Object resource){
            this(resource,resource.toString());
        }
        public Resource(Object resource,boolean useRestricParser){
            this(resource,resource.toString(),useRestricParser);
        }
        public Resource(Object resource,String name){
            this(resource,name,false);
        }
        public Resource(Object resource,String name ,boolean restrictParser){
            this.resource = resource;
            this.name = name;
            this.restrictParser = restrictParser;
        }

        @Override
        public String toString() {
            return name;
        }

        private static boolean getRestrictParserDefault(Object resource){
            if (resource instanceof String|| !UserGroupInformation.isInitialized()){
                return false;
            }
            UserGroupInformation user;
            try {
                user = UserGroupInformation.getCurrentUser();
            } catch (IOException e) {
                throw new RuntimeException("Unable to determin current user",e);
            }

            return user.getRealUser() != null;
        }

        public Object getResource() {
            return resource;
        }

        public String getName() {
            return name;
        }

        public boolean isRestrictParser() {
            return restrictParser;
        }
    }


    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return null;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
