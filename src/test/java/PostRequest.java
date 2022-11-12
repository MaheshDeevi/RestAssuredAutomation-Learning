import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


import static io.restassured.RestAssured.*;
public class PostRequest {

    @Test
    public void test1(){
        //pass json body directly
       Response response = given().header("Content-Type","application-json").
               body("{\n" +
               "      \"id\": 116,\n" +
               "      \"first_name\": \"Mahesh\",\n" +
               "      \"last_name\": \"Deevi\",\n" +
               "      \"email\": \"mahesh@gmail.com\"\n" +
               "    }").log().all().post("http://localhost:3000/Employees");
       response.prettyPrint();

    }

    @Test
    public void test2(){
        //pass json body as a string

        String json = "{\n" +
                "      \"id\": 118,\n" +
                "      \"first_name\": \"Mahesh\",\n" +
                "      \"last_name\": \"Deevi\",\n" +
                "      \"email\": \"mahesh@gmail.com\"\n" +
                "    }";
        Response response = given().header("Content-Type",ContentType.JSON).
                body(json).log().all().post("http://localhost:3000/Employees");
        response.prettyPrint();

    }

    @Test
    public void test3(){
        //pass json body from external file
        //1.Request will not be printed on console when we pass from external file
        //2.Use this only for static data

        Response response = given().header("Content-Type",ContentType.JSON).
                body(new File(System.getProperty("user.dir")+"/test.json")).log().all().post("http://localhost:3000/Employees");
        response.prettyPrint();

    }

    @Test
    public void test4() throws IOException {
        //pass json body from external file and covert into String
        //1.Request will be printed on console 
        //2.Use this only for changing few parameters in the request
        //3.This is not suitable if the request is complex json

        String reqbody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/test.json")));
        String replace =reqbody.replace("1",String.valueOf(new Faker().number().numberBetween(100,1000)))
                .replace("fname",new Faker().name().firstName());

        Response response = given().header("Content-Type",ContentType.JSON).
                body(replace).log().all().post("http://localhost:3000/Employees");
        response.prettyPrint();

    }

    @Test
    public void test5(){
     //Passing Json as Map object
        //{}-->map
        //[]-->List
//Serializers ---> converts your language objects-->stream of bytes--->json
        //Use Linked hashmap to get json request in order
        Map<String,Object> obj = new LinkedHashMap<>();
        obj.put("id",new Faker().number().numberBetween(100,1000));
        obj.put("first_name","mahesh");
        obj.put("last_name","deevi");

        Response response = given().header("Content-Type",ContentType.JSON).
                body(obj). //Jackson binding
                log().
                all().
                post("http://localhost:3000/Employees");
        response.prettyPrint();

    }

    @Test
    public void test6(){
        //Passing Json as Map object, using complex Json here
        //{}-->map
        //[]-->List
        //verbose and not suitable for big json files
        //need to mention generics
//Serializers ---> converts your language objects-->stream of bytes--->json
        Map<String,Object> obj = new LinkedHashMap<>();
        obj.put("id",new Faker().number().numberBetween(100,1000));
        obj.put("first_name","mahesh");
        obj.put("last_name","deevi");

//        List<String> jobs = new ArrayList<>();
//        jobs.add("tester");
//        jobs.add("trainer");
        obj.put("jobs",Arrays.asList("tester","trainer"));


        Map<String,Object> favFoods = new LinkedHashMap<>();
        favFoods.put("breakfast","idly");
        favFoods.put("lunch","rice");
        List<String> dinner = new ArrayList<>();
        dinner.add("chapathi");
        dinner.add("milk");
        favFoods.put("dinner",dinner);

        obj.put("favFoods",favFoods);

        Response response = given().header("Content-Type",ContentType.JSON).
                body(obj). //Jackson binding
                log().
                all().
                post("http://localhost:3000/Employees");
        response.prettyPrint();

    }

    @Test
    public void test7()  {
        //Using Json Java library
        //{}--->JsonObject
        //[]-->JsonArray

        JSONObject obj = new JSONObject();
        obj.put("id",new Faker().number().numberBetween(100,1000));
        obj.put("first_name","test");
        obj.put("last_name","abc");

        JSONArray jobs = new JSONArray();
        jobs.put("tester");
        jobs.put("trainer");
        obj.put("jobs",jobs);

        JSONObject favFoods = new JSONObject();
        favFoods.put("breakfast","idly");
        favFoods.put("lunch","rice");
        JSONArray dinner = new JSONArray();
        dinner.put("chapathi");
        dinner.put("milk");
        favFoods.put("dinner",dinner);
        obj.put("favFoods",favFoods);



        Response response = given().header("Content-Type",ContentType.JSON).
                body(obj.toMap()).  // convert to map or String. Jackson binding doesn't have ability to serialize when we pass as a obj
                log().
                all().
                post("http://localhost:3000/Employees");
        response.prettyPrint();
    }


}
