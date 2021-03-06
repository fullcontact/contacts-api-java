package fullcontact.contacts.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.asynchttpclient.*;
import fullcontact.contacts.api.requests.contacts.*;
import fullcontact.contacts.api.responses.APIResponse;
import fullcontact.contacts.api.models.Contact;
import fullcontact.contacts.api.responses.contacts.ContactResponseBody;
import fullcontact.contacts.api.responses.contacts.ContactsResponseBody;

import java.util.HashMap;
import java.util.List;

public class Contacts extends API {

    public Contacts(HashMap<String, Object> config) {
        super(config);
    }
    public Contacts(HashMap<String, Object> config, AsyncHttpClient client) {
        super(config, client);
    }

    /***
     * Get a list of contacts by using a list of contact ids.
     * @param accessToken
     * @param contactIds
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactsResponseBody> get(String accessToken, List<String> contactIds, String teamId) throws Exception {
        GetContactsRequest req = new GetContactsRequest();
        req.teamId = teamId;
        req.contactIds = contactIds;
        return this.request(
                ContactsResponseBody.class,
                accessToken,
                "POST",
                "/api/v1/contacts.get",
                req,
                null
        );
    }

    /***
     * Scrolls the user's list of contacts.
     * @param accessToken
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactsResponseBody> scroll(String accessToken, String teamId) throws Exception {
        return scroll(accessToken, null, false, teamId, 100);
    }

    /***
     * Scrolls the user's list of contacts.
     * @param accessToken
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactsResponseBody> scroll(String accessToken, String cursor, String teamId) throws Exception {
        return scroll(accessToken, cursor, false, teamId, 100);
    }

    /***
     * Scrolls the user's list of contacts.
     * @param accessToken
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactsResponseBody> scroll(String accessToken, String cursor, Boolean includeDeleted, String teamId, Integer size) throws Exception {
        ScrollContactsRequest req = new ScrollContactsRequest();
        req.scrollCursor = cursor;
        req.includeDeletedContacts = includeDeleted;
        req.teamId = teamId;
        req.size = size;
        return this.request(
                ContactsResponseBody.class,
                accessToken,
                "POST",
                "/api/v1/contacts.scroll",
                req,
                null
        );
    }

    /***
     * Searches the user's list of contacts.
     * @param accessToken
     * @param query
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactsResponseBody> search(String accessToken, String query, String teamId) throws Exception {
        return search(accessToken, query, null, teamId, null);
    }

    /***
     * Searches the user's list of contacts.
     * @param accessToken
     * @param query
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactsResponseBody> search(String accessToken, String query, String cursor, String teamId) throws Exception {
        return search(accessToken, query, cursor, teamId, null);
    }

    /***
     * Searches the user's list of contacts.
     * @param accessToken
     * @param query
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactsResponseBody> search(String accessToken, String query, String cursor, String teamId, List<String> tagIds) throws Exception {
        SearchContactsRequest req = new SearchContactsRequest();
        req.searchCursor = cursor;
        req.tagIds = tagIds;
        req.teamId = teamId;
        req.searchQuery = query;
        return this.request(
                ContactsResponseBody.class,
                accessToken,
                "POST",
                "/api/v1/contacts.search",
                req,
                null
        );
    }

    /***
     * Creates a new contact.
     * @param accessToken
     * @param contact
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactResponseBody> create(String accessToken, Contact contact, String teamId) throws Exception {
        CreateContactRequest req = new CreateContactRequest();
        req.contact = contact;
        req.teamId = teamId;
        return this.request(
                ContactResponseBody.class,
                accessToken,
                "POST",
                "/api/v1/contacts.create",
                req,
                null
        );
    }

    /***
     * Updates an existing contact
     * @param accessToken
     * @param contact
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<ContactResponseBody> update(String accessToken, Contact contact, String teamId) throws Exception {
        UpdateContactRequest req = new UpdateContactRequest();
        req.contact = contact;
        req.teamId = teamId;
        return this.request(
                ContactResponseBody.class,
                accessToken,
                "POST",
                "/api/v1/contacts.update",
                req,
                null
        );
    }

    /***
     * Manages tags for a list of contactIds.
     * @param accessToken
     * @param contactIds
     * @param addTagIds
     * @param removeTagIds
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<JsonNode> manageTags(String accessToken, List<String> contactIds, List<String> addTagIds, List<String> removeTagIds, String teamId) throws Exception {
        ManageTagsRequest req = new ManageTagsRequest();
        req.teamId = teamId;
        req.contactIds = contactIds;
        req.addTagIds = addTagIds;
        req.removeTagIds = removeTagIds;
        return this.request(
                JsonNode.class,
                accessToken,
                "POST",
                "/api/v1/contacts.manageTags",
                req,
                null
        );
    }

    /***
     * Deletes an existing contact.
     * @param accessToken
     * @param contactId
     * @param etag
     * @param teamId
     * @return
     * @throws Exception
     */
    public APIResponse<JsonNode> delete(String accessToken, String contactId, String etag, String teamId) throws Exception {
        DeleteContactRequest req = new DeleteContactRequest();
        req.teamId = teamId;
        req.contactId = contactId;
        req.etag = etag;
        return this.request(
                JsonNode.class,
                accessToken,
                "POST",
                "/api/v1/contacts.delete",
                req,
                null
        );
    }
}