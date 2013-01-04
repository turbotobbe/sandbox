package sandbox.nextapi;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


/**
 * Hello world!
 *
 */
public class App 
{
	private static final String KEYFILE = "/NEXTAPI_TEST_public.der";

	public static void main( String[] args ) throws Exception
    {
		if (args.length != 2) {
			System.err.println("Args: <username> <password>");
			return;
		}

		String username = args[0];
		String password = args[1];
    	NextClient client = new NextClient(KEYFILE);
    	client.login(username, password);
    	JSONArray accounts = client.getAccounts();
    	for (int i = 0; i < accounts.length(); i++) {
			JSONObject account = accounts.getJSONObject(i);
			int accountId = account.getInt("id");
			client.getAccount(accountId);
			client.getAccountLedgers(accountId);
			client.getAccountOrders(accountId);
			client.getAccountPositions(accountId);
			client.getAccountTrades(accountId);
		}
    	client.logout();
    }
}
