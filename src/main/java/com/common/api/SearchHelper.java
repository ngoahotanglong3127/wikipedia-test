/* (C)2023 */
package com.common.api;

import static org.testng.Assert.assertNotNull;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class SearchHelper {

    public static Response searchPage(JSONObject request) throws Exception {

        Response response =
                RestAssured.given()
                        .queryParam("action", "query")
                        .queryParam("format", "json")
                        .queryParam("list", "search")
                        .queryParam("formatversion", "2")
                        .queryParam("srsearch", request.get("searchedText"))
                        .queryParam("sroffset", request.get("srOffset"))
                        .when()
                        .get("https://en.wikipedia.org/w/api.php")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();
        // Check response not null
        assertNotNull(response);
        return response;
    }
}
