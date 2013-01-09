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

        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new HeinRestApplication(), HttpHandler.class);
        server.createContext(uri.getPath(), handler);

        server.start();
    }

    @AfterClass
    public static void stop() {
        server.stop(0);
    }

    @Test
    public void shouldReturnMail() {
        String mail = client.target(uri + "?q=Quelle+est+ton+adresse+email").request().get(String.class);
        Assert.assertEquals(HeinRestService.MAIL, mail);
    }

}
