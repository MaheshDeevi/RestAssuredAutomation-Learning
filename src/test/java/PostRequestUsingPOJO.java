import com.pojo.employee.Employee;
import com.pojo.employee.FavFood;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class PostRequestUsingPOJO {

    @Test
    public void test1(){
        //Run test with various Lombook annotations
        FavFood favFood = new FavFood("idly","rice",Arrays.asList("chapathi","milk"));
        Employee employee = new Employee(400,null,"","mahi@gmail.com", Arrays.asList("tester","trainer"),favFood);
        Response response = given().header("Content-Type", ContentType.JSON).
                body(employee).log().all().post("http://localhost:3000/Employees");
        response.prettyPrint();

    }

    @Test
    public void test2(){
        //Deserialization using Json path and POJO class
        FavFood favFood = new FavFood("idly","rice",Arrays.asList("chapathi","milk"));
        Employee employee = new Employee(600,"mahesh","deevi","mahi@gmail.com", Arrays.asList("tester","trainer"),favFood);
        Response response = given().header("Content-Type", ContentType.JSON).
                body(employee).log().all().post("http://localhost:3000/Employees");
        response.prettyPrint();

        //JsonPath --->Comes from RestAssured library
        //Use online JsonPath to find JsonPath
        System.out.println(response.jsonPath().getString("fname"));
        System.out.println(response.jsonPath().getList("jobs"));
        System.out.println(response.jsonPath().getString("favFood"));
        System.out.println(response.jsonPath().getString("favFood.dinner[0]"));

        //Deserialization using POJO class employee
        //If you want to use this process, create default constructor for all POJO classes
        Employee deserializedEmployee = response.as(Employee.class);
        System.out.println(deserializedEmployee.getFname());

        //Json Schema Validator
        //Take response and get Json Schema from online https://www.liquid-technologies.com/online-json-to-schema-converter
        //Add dependency Json Schema validator

        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

    }
}
