package practice.myServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RestServer {

    static String filename;
    static File file;
    static Scanner in;
    static Scanner input;
    static String name;
    static String entry;

    static void main() throws IOException {

        System.out.println("Enter the employee name to search for");

        filename = "employees.txt";
        file = new File(filename);
        in = new Scanner(file);
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/user", new UserHandler());
        input = new Scanner(System.in);
        name = input.next();
        server.setExecutor(null);
        System.out.println("REST API Server running on port " + port);
        server.start();
// TODO find out why this is so slow
    }

    static class UserHandler implements HttpHandler {

        @Override
        public void handle( HttpExchange exchange ) throws IOException {

            // set global json header
            exchange.getResponseHeaders().set("Content-Type", "application/json");

            if ( "GET".equalsIgnoreCase(exchange.getRequestMethod()) ) {
                handleGetRequest();
            } else if ( "POST".equalsIgnoreCase(exchange.getRequestMethod()) ) {
                handlePostRequest(exchange);
            } else {
                sendResponse(exchange, 405, "{\"error\": \"Only POST requests allowed");
            }
        }

        private void handleGetRequest() throws IOException {

            while ( !(entry = in.next()).isEmpty() ) {

                //  System.out.println(entry);
                String[] array = entry.split(":");

                if ( array[1].equalsIgnoreCase(name) ) {
                    int id = Integer.parseInt(array[0]);
                    name = array[1];
                    String job = array[2];

                    System.out.printf("Employee %s with id %d works as a %s", name, id, job);
                    return;
                }
            }
        }

        private void handlePostRequest( HttpExchange exchange ) throws IOException {

            // Read request body
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // Extract name value from JSON { "name": "Value" }
            String name = "guest";

            if ( body.contains("\"name\"") ) {
                int start = body.indexOf("\"name\"") + 6;
                start = body.indexOf("\"", start) + 1;
                int end = body.indexOf("\"", start);

                if ( start > 0 && end > start ) {
                    name = body.substring(start, end);
                }
            }

            // CREATE JSON RESPONSE
            String jsonResponse = String.format("{\"status\": \"success\", \"message\": \"Hello, %s\"}", name);
            sendResponse(exchange, 200, jsonResponse);
        }

        private void sendResponse( HttpExchange exchange, int statusCode, String response ) throws IOException {

            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(statusCode, bytes.length);

            try ( OutputStream os = exchange.getResponseBody() ) {
                os.write(bytes);
            }
        }
    }
}


