package sandbox.nextapi;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import sandbox.nextapi.model.account.Account;
import sandbox.nextapi.model.account.AccountSummary;
import sandbox.nextapi.model.account.Ledger;
import sandbox.nextapi.model.account.Order;
import sandbox.nextapi.model.account.Position;
import sandbox.nextapi.model.account.Trade;
import sandbox.nextapi.model.instrument.AList;
import sandbox.nextapi.model.session.Session;
import sandbox.nextapi.service.AccountService;
import sandbox.nextapi.service.AccountServiceImpl;
import sandbox.nextapi.service.InstrumentService;
import sandbox.nextapi.service.InstrumentServiceImpl;
import sandbox.nextapi.service.OrderService;
import sandbox.nextapi.service.OrderServiceImpl;
import sandbox.nextapi.service.SessionService;
import sandbox.nextapi.service.SessionServiceImpl;

public class MyClient {

	private static final String BASEURL = "https://api.test.nordnet.se/next/1";

	private SessionService sessionService;
	private AccountService accountService;
	private OrderService orderService;
	private InstrumentService instrumentService;

	public MyClient() {

		Client client = Client.create();
		WebResource base = client.resource(BASEURL);
		sessionService = new SessionServiceImpl(base, client);
		accountService = new AccountServiceImpl(base);
		orderService = new OrderServiceImpl(base);
		instrumentService = new InstrumentServiceImpl(base);
	}

	private void run(String username, String password) {
//		sessionService.getStatus();
		
		Session session = sessionService.login(username, password);
		
//		sessionService.getRealtimeAccesses();
//		sessionService.touch(session.getSessionKey());

//		List<Account> accounts = accountService.getAccounts();
//		for (Account account : accounts) {
//			AccountSummary as = accountService.getAccount(account.getAccountId());
//			List<Ledger> ledgers = accountService.getLedgers(account.getAccountId());
//			List<Position> positions = accountService.getPositions(account.getAccountId());
//			List<Order> orders = accountService.getOrders(account.getAccountId());
//			List<Trade> trades = accountService.getTrades(account.getAccountId());
//		}

		List<AList> alists = instrumentService.getLists();
		for (AList aList : alists) {
			instrumentService.getList(aList.getListId());
		}

		sessionService.logout(session.getSessionKey());
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Args: <username> <password>");
			return;
		}
		String username = args[0];
		String password = args[1];
		MyClient myClient = new MyClient();
		myClient.run(username, password);
		// myClient.
	}
}
