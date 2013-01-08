package net.binout.codestory2013;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HeinRestService {

    @GET
    public String getEmail() {
        return "binout@gmail.com";
    }
}
