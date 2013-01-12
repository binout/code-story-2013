package net.binout.codestory2013;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class HeinRestApplication extends Application {

    private Set<Class<?>> classes;

    public HeinRestApplication() {
        classes = new HashSet<Class<?>>();
        classes.add(HeinRestService.class);
        classes.add(HeinRestEnonce.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
