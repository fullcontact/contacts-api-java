package fullcontact.contacts.api.tests;
import fullcontact.contacts.api.*;
import fullcontact.contacts.api.requests.APIRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class APITest {
    private final API api;
    public APITest() {
        this.api = new Account(new HashMap<>());
    }

    @Test public void test_urlEncode() {
        String input = "Test This";
        String expected = "Test+This";
        String result = this.api.urlEncode(input);
        Assert.assertEquals(result, expected);
    }

    @Test public void test_urlEncode_null() {
        String result = this.api.urlEncode(null);
        Assert.assertNull(result);
    }

    @Test public void test_toJSON() {
        APIRequest req = new APIRequest();
        req.teamId = "TEST";
        try {
            String result = this.api.toJSON(req);
            String expected = "{\"teamId\":\"TEST\"}";
            Assert.assertEquals(result, expected);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
