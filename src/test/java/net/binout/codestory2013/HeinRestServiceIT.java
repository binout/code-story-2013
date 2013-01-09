package net.binout.codestory2013;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientFactory;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
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

        // create a new server listening at port 8080
        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);

        // create a handler wrapping the JAX-RS application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new HeinRestApplication(), HttpHandler.class);

        // map JAX-RS handler to the server root
        server.createContext(uri.getPath(), handler);

        // start the server
        server.start();
    }

    @AfterClass
    public static void stop() {
        server.stop(0);
    }

    @Test
    public void shouldReturnMail() {
        String mail = client.target(uri).request().get(String.class);
        Assert.assertEquals(HeinRestService.MAIL, mail);
    }

}
