package net.binout.codestory2013;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

public class HeinRestServiceIT {

    private static HttpServer server;
    private static URI uri;
    private static Client client;

    @BeforeClass 
    public static void init() throws IOException {
        uri = UriBuilder.fromUri("http://localhost/").port(8282).build();

        client = ClientFactory.newClient();

        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new HeinRestApplication(), HttpHandler.class);
        server.createContext(uri.getPath(), handler);

        server.start();
    }

    @AfterClass
    public static void stop() {
        server.stop(0);
    }

    private String transformQuery(String query) {
        return query.replaceAll(" ", "+");
    }

    @DataProvider
    public Object[][] query_response_provider() {
        return new Object[][] {
                new String[] {HeinRestService.QUELLE_EST_TON_ADRESSE_EMAIL, HeinRestService.MAIL},
                new String[] {HeinRestService.ES_TU_ABONNE_A_LA_MAILING_LIST_OUI_NON, HeinRestService.OUI},
                new String[] {HeinRestService.ES_TU_HEUREUX_DE_PARTICIPER_OUI_NON, HeinRestService.OUI},
        };
    }

    @Test(dataProvider = "query_response_provider")
    public void get_should_return_response(String query, String expected) {
        String mailQuery = uri + "?" + HeinRestService.Q + "=" + transformQuery(query);
        String response = client.target(mailQuery).request().get(String.class);
        Assert.assertEquals(expected, response);
    }

    @Test(dataProvider = "query_response_provider")
    public void get_should_return_response_with_ok_status(String query, String expected) {
        String mailQuery = uri + "?" + HeinRestService.Q + "=" + transformQuery(query);
        Response response = client.target(mailQuery).request().get(Response.class);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(expected, response.readEntity(String.class));
    }

    @Test
    public void post_should_response_created_status() {
        Response response = client.target(uri).request().post(Entity.text("message"));
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

}
