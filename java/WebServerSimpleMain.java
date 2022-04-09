import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandlers;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Request;
import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class WebServerSimpleMain {
    private static final int SIMPLE_SERVER_PORT = 8000;
    private static final int HANDLER_SERVER_PORT = 8001;

    public static void main(String[] args) throws Exception {
        System.out.println("Java WebServer example....");
        if(args.length > 0){
            switch(args[0]){
                case "simple" -> runSimpleWebServer();
                case "advanced" -> runSimpleWebServerWithHandler();
                default -> new IllegalArgumentException("choose: simple, advanced");  
            }
        } else {
            throw new IllegalArgumentException("choose: simple, advanced");    
        }
    }

    private static void runSimpleWebServer() {
        printInfo(SIMPLE_SERVER_PORT);
        var servedPath = Paths.get("http-static2").toAbsolutePath();
        var server = SimpleFileServer.createFileServer(
                new InetSocketAddress(SIMPLE_SERVER_PORT), servedPath, OutputLevel.VERBOSE);
        server.start();
    }

    private static void runSimpleWebServerWithHandler() throws IOException {
        printInfo(HANDLER_SERVER_PORT);
        //Json Header
        var jsonHeaders = Headers.of("Content-Type", "application/json");
        //Create GET and POST Paths
        var getResponsePath = Paths.get("http-static2/get/get_request.json").toAbsolutePath();
        var postResponsePath = Paths.get("http-static2/post/post_request.json").toAbsolutePath();

        //GET, POST Handlers with the content and predicates
        var getHandler = HttpHandlers.of(200, jsonHeaders, Files.readString(getResponsePath));
        var postHandler = HttpHandlers.of(200, jsonHeaders, Files.readString(postResponsePath));
        Predicate<Request> IS_GET = r -> r.getRequestMethod().equals("GET");
        Predicate<Request> IS_POST = r -> r.getRequestMethod().equals("POST");
        var notAllowedHandler = HttpHandlers.of(405,
                Headers.of("Access-Control-Allow-Methods", "GET,POST"), "Sorry, not allowed method");


        var h1 = HttpHandlers.handleOrElse(IS_GET, getHandler, notAllowedHandler);
        var h2 = HttpHandlers.handleOrElse(IS_POST, postHandler, h1);
        var server = HttpServer.create(new InetSocketAddress(HANDLER_SERVER_PORT), 2,
                "/", h2, SimpleFileServer.createOutputFilter(System.out, OutputLevel.INFO));

        server.start();
    }

    private static void printInfo(int port) {
        System.out.println("""
                Port: %s
                """.formatted(port));
    }
}