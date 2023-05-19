import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import user.CreateAndAuthUserResponse;
import user.User;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class BaseApi extends BaseTest{
    public static RequestSpecification specification = RestAssured.given().baseUri("https://stellarburgers.nomoreparties.site").header("Content-type", "application/json");
    public static Faker faker = new Faker(new Locale("ru"));
    public static String email = faker.internet().emailAddress();
    public static String password = faker.internet().password(7,10);
    public static String invalidPassword = faker.internet().password(1,4);
    public static String name = faker.name().fullName();
    public static String tokenUser;

    @BeforeClass
    public static void createUser() {
        User user = new User(email, password, name);
        CreateAndAuthUserResponse responseUser =
                given().spec(specification)
                        .body(user)
                        .when()
                        .post("/api/auth/register")
                        .body().as(CreateAndAuthUserResponse.class);
        tokenUser = responseUser.getAccessToken();
    }

    @AfterClass
    public static void deleteUser() {
        given().spec(specification)
                .header("Authorization", tokenUser)
                .when()
                .delete("api/auth/user")
                .then()
                .statusCode(202);
    }
}
