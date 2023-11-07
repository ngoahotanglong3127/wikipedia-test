/* (C)2023 */
package api;

import static org.testng.Assert.*;

import com.common.api.SearchHelper;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class SearchPagesTest {
    @Test
    @Description("Verify the api test")
    public void testSearchPages() throws Exception {

        // 1. Define the input test data
        int originOffset = 40;
        JSONObject request = new JSONObject();
        request.put("searchedText", "Software Testing");
        request.put("srOffset", originOffset);

        // 2. Search pages by input data and get response
        Response response = SearchHelper.searchPage(request);

        // 3. Exact search response to check search result
        JsonPath batchCompletePath = response.jsonPath();
        Boolean batchComplete = batchCompletePath.get("batchcomplete");
        // Check that search is successful by checking batchComplete = true
        assertEquals(batchComplete, true, "Verify batchComplete = true?");

        // 4. Exact search response to check srOffset
        JSONObject responseObj = new JSONObject(response.getBody().asString());
        // 4.1. Get continue object
        JSONObject continueObj = responseObj.getJSONObject("continue");
        // Check that continue object is not null
        assertNotNull(continueObj);

        // 4.2 Get srOffset object
        Integer srOffset = continueObj.getInt("sroffset");
        // Check that srOffset not null
        assertNotNull(srOffset);
        System.out.println("sroffset = " + srOffset);
        // Check that srOffset is = original srOffset + 10 by rule
        assertEquals(srOffset, originOffset + 10, "Verify sroffset");
    }
}
