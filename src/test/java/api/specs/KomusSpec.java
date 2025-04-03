package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;


public class KomusSpec {


    public static final RequestSpecification requestSpec = with()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .filter(withCustomTemplates())
            .log().all();

    public static final ResponseSpecification responseSpecWithStatusCode200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();

    public static final ResponseSpecification responseSpecWithStatusCode400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.ALL)
            .build();
}

