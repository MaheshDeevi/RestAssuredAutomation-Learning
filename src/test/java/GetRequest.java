import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
public class GetRequest {
    @Test
    public void test() throws IOException {
       Response response= given().get(" http://localhost:3000/employees");
       response.prettyPrint();
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        Headers headers = response.getHeaders();
        for (Header header:headers) {
            System.out.println(header.getName()+" : "+header.getValue() );
        }

        Files.write(Paths.get(System.getProperty("user.dir")+"/src/test/responses/response.txt"),response.asByteArray());

    }




}
