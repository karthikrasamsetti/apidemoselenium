package org.api.base;

import io.restassured.response.Response;
import org.api.endpoints.UserEndpoint;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AppTest extends Base {

    @Test(priority = 1)
    public void testCreateUser() {
        test = extent.createTest("testCreateUser")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        try {
            Response response = UserEndpoint.createUser(userPayload);
            response.then().log().all();
            assertEquals(response.getStatusCode(), 200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        test = extent.createTest("testGetUserByName")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        try {
            Response response = UserEndpoint.readUser(this.userPayload.getUsername());
            response.then().log().all();
            assertEquals(response.getStatusCode(), 200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        test = extent.createTest("testUpdateUserByName")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        try {
            userPayload.setFirstName(faker.name().firstName());
            userPayload.setLastName(faker.name().lastName());
            userPayload.setEmail(faker.internet().safeEmailAddress());
            Response response = UserEndpoint.updateUser(this.userPayload.getUsername(), userPayload);
            response.then().log().body();
            assertEquals(response.getStatusCode(), 200);
            Response responseAfterupdate = UserEndpoint.readUser(this.userPayload.getUsername());
            responseAfterupdate.then().log().all();
            assertEquals(responseAfterupdate.getStatusCode(), 200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        test = extent.createTest("testDeleteUserByName")
                .assignAuthor("karthik").assignDevice("Windows Desktop");
        try {
            Response response = UserEndpoint.deleteUser(this.userPayload.getUsername());
            response.then().log().all();
            assertEquals(response.getStatusCode(), 200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
