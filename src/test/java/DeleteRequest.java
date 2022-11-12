import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DeleteRequest {

    @Test
    public void test(){
        given().pathParam("id",5)
                .log().all()
                .delete("http://localhost:3000/Employees/{id}")
                .prettyPrint();
    }
}
