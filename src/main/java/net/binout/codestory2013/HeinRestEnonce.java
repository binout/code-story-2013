package net.binout.codestory2013;

import com.petebevin.markdown.MarkdownProcessor;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/enonce")
@Singleton
@ApplicationScoped
public class HeinRestEnonce {

    private List<String> enonces = new ArrayList<String>();

    public void addEnonce(String enonce) {
        this.enonces.add(enonce);
    }

    @GET
    public String get() {
        StringBuilder builder = new StringBuilder();
        builder.append("<hr/>");
        MarkdownProcessor m = new MarkdownProcessor();
        for(String enonce : enonces) {
            builder.append(m.markdown(enonce));
            builder.append("<hr/>");
        }
        return builder.toString();
    }
}
