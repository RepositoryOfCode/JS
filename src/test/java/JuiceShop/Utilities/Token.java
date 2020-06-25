package JuiceShop.Utilities;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class Token {


        public static String getToken(String email, String password) {
            String token=null;

            Response response = given().
                    queryParam("email", email).
                    queryParam("password", password).
                    when().get("sign");
            response.then().statusCode(200);
            token = response.path("accessToken");
            return token;

        }


    }

