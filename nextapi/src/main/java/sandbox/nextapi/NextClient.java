package sandbox.nextapi;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class NextClient extends BasicNextClient {

	public NextClient(String keyFile) {
		super(keyFile);
	}

	public JSONArray getAccounts() throws JSONException {
		System.out.println("ACCOUNTS");
		JSONArray response = resource.path("accounts").accept(MEDIATYPE).get(JSONArray.class);
		System.out.println(response.toString(2));
		return response;
	}
	
	public JSONObject getAccount(int accountId) throws JSONException {
		System.out.println("ACCOUNT (" + accountId + ")");
		JSONObject response = resource.path("accounts").path(String.valueOf(accountId)).accept(MEDIATYPE).get(JSONObject.class);
		System.out.println(response.toString(2));
		return response;
	}
	
	public JSONArray getAccountLedgers(int accountId) throws JSONException {
		System.out.println("ACCOUNT LEDGERS (" + accountId + ")");
		JSONArray response = resource.path("accounts").path(String.valueOf(accountId)).path("ledgers").accept(MEDIATYPE).get(JSONArray.class);
		System.out.println(response.toString(2));
		return response;
	}

	public JSONArray getAccountPositions(int accountId) throws JSONException {
		System.out.println("ACCOUNT POSITIONS (" + accountId + ")");
		JSONArray response = resource.path("accounts").path(String.valueOf(accountId)).path("positions").accept(MEDIATYPE).get(JSONArray.class);
		System.out.println(response.toString(2));
		return response;
	}
	
	public JSONArray getAccountOrders(int accountId) throws JSONException {
		System.out.println("ACCOUNT ORDERS (" + accountId + ")");
		JSONArray response = resource.path("accounts").path(String.valueOf(accountId)).path("orders").accept(MEDIATYPE).get(JSONArray.class);
		System.out.println(response.toString(2));
		return response;
	}

	public JSONArray getAccountTrades(int accountId) throws JSONException {
		System.out.println("ACCOUNT TRADES (" + accountId + ")");
		JSONArray response = resource.path("accounts").path(String.valueOf(accountId)).path("trades").accept(MEDIATYPE).get(JSONArray.class);
		System.out.println(response.toString(2));
		return response;
	}
}
