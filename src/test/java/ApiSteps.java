import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class ApiSteps {
    public static void createUser() throws IOException {
        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/1.json"))));
        Response createUser = given()
                .contentType(ContentType.JSON)
                .body(body.put("name", "Tomato"))
                .body(body.put("job", "Eat maket"))
                .body(body.toString())
                .baseUri("https://reqres.in")
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Tomato"))
                .body("job", equalTo("Eat maket"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .log().all()
                .extract().response();
    }
}
