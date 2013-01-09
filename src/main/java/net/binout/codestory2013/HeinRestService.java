package net.binout.codestory2013;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HeinRestService {

    final static String MAIL = "binout@gmail.com";

    @GET
    public String getEmail() {
        return MAIL;
    }
}
