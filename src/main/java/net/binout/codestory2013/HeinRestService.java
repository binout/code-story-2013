package net.binout.codestory2013;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.petebevin.markdown.MarkdownProcessor;
import net.binout.codestory2013.scalaskel.Scalaskel;
import net.binout.codestory2013.scalaskel.ScalaskelResult;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Path("/")
@ApplicationScoped
public class HeinRestService {

    static final String Q = "q";
    static final String QUELLE_EST_TON_ADRESSE_EMAIL = "Quelle est ton adresse email";
    static final String MAIL = "binout@gmail.com";
    static final String ES_TU_ABONNE_A_LA_MAILING_LIST_OUI_NON = "Es tu abonne a la mailing list(OUI/NON)";
    static final String ES_TU_HEUREUX_DE_PARTICIPER_OUI_NON = "Es tu heureux de participer(OUI/NON)";
    static final String ES_TU_PRET_A_RECEVOIR_UNE_ENONCE_AU_FORMAT_MARKDOWN_PAR_HTTP_POST_OUI_NON = "Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)";
    static final String EST_CE_QUE_TU_REPONDS_TOUJOURS_OUI_OUI_NON = "Est ce que tu reponds toujours oui(OUI/NON)";
    static final String OUI = "OUI";
    static final String NON = "NON";
    public static final String AS_TU_BIEN_RECU_LE_PREMIER_ENONCE_OUI_NON = "As tu bien recu le premier enonce(OUI/NON)";

    private List<String> enonces = new ArrayList<String>();
    private Scalaskel scalaskel = new Scalaskel();
    private Gson gson = new Gson();

    public HeinRestService() throws IOException {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("scalaskel.txt");
        String scalaskel = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
        enonces.add(scalaskel);
    }

    private Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST).entity("Hein binout?").build();
    }

    @GET
    public Response get(@Context UriInfo uriInfo, @QueryParam(Q) String query) {
        System.out.println("\nGET");
        logAllQueryParameters(uriInfo);

        if (QUELLE_EST_TON_ADRESSE_EMAIL.equals(query)) {
            return Response.ok(MAIL).build();
        }
        if (ES_TU_ABONNE_A_LA_MAILING_LIST_OUI_NON.equals(query)
                || ES_TU_HEUREUX_DE_PARTICIPER_OUI_NON.equals(query)
                || ES_TU_PRET_A_RECEVOIR_UNE_ENONCE_AU_FORMAT_MARKDOWN_PAR_HTTP_POST_OUI_NON.equals(query)
                || AS_TU_BIEN_RECU_LE_PREMIER_ENONCE_OUI_NON.equals(query)) {
            return Response.ok(OUI).build();
        }
        if (EST_CE_QUE_TU_REPONDS_TOUJOURS_OUI_OUI_NON.equals(query)) {
            return Response.ok(NON).build();
        }
        return badRequest();
    }

    private void logAllQueryParameters(UriInfo uriInfo) {
        MultivaluedMap<String,String> queryParameters = uriInfo.getQueryParameters();
        Set<Map.Entry<String,List<String>>> entries = queryParameters.entrySet();
        for (Map.Entry<String,List<String>> entry : entries) {
            System.out.println("Parameter : " + entry.getKey());
            for (String value : entry.getValue()) {
                System.out.println("Value : " + value);
            }
        }
    }

    @GET
    @Path("/enonce")
    public String getEnonces() {
        StringBuilder builder = new StringBuilder();
        builder.append("<hr/>");
        MarkdownProcessor m = new MarkdownProcessor();
        for(String enonce : enonces) {
            builder.append(m.markdown(enonce));
            builder.append("<hr/>");
        }
        return builder.toString();
    }

    @POST
    @Path("/enonce/{id}")
    public Response postEnonce(@PathParam("id") String id, String message) {
        System.out.println("\nPOST : Enonce" + id);
        enonces.add(message);
        System.out.println(message);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("scalaskel/change/{X}")
    public Response getScalaskel(@PathParam("X") String number) {
        System.out.println("\nGET : scalaskel/change/" + number);
        int toChange;
        try {
            toChange = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            return badRequest();
        }
        if (toChange<1 || toChange>100) {
            return badRequest();
        }
        List<ScalaskelResult> result = scalaskel.change(toChange);
        String json = gson.toJson(result);
        return Response.ok(json, MediaType.APPLICATION_JSON_TYPE).build();
    }

}
