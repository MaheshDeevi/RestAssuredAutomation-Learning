import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class PutRequest {

    @Test
    public void test(){
        given().header("Content-Type", ContentType.JSON)
                .pathParam("id",1)
                .body(new File(System.getProperty("user.dir")+"/test.json"))
                .log()
                .all()
                .put("http://localhost:3000/Employees/{id}")
                .prettyPrint();
    }
}
