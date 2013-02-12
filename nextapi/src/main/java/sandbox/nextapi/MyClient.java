package sandbox.nextapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import sandbox.nextapi.model.account.Account;
import sandbox.nextapi.model.account.AccountSummary;
import sandbox.nextapi.model.account.Ledger;
import sandbox.nextapi.model.account.Order;
import sandbox.nextapi.model.account.Position;
import sandbox.nextapi.model.account.Trade;
import sandbox.nextapi.model.instrument.AList;
import sandbox.nextapi.model.instrument.AListItem;
import sandbox.nextapi.model.instrument.Tick;
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

    private static final Logger LOG = Logger.getLogger(MyClient.class.getName());

    private static final String BASEURL = "https://api.test.nordnet.se/next/1";

	private SessionService sessionService;
	private AccountService accountService;
	private OrderService orderService;
	private InstrumentService instrumentService;

	public MyClient() throws FileNotFoundException {

		Client client = Client.create();
		WebResource base = client.resource(BASEURL);
		sessionService = new SessionServiceImpl(base, client);
		accountService = new AccountServiceImpl(base);
		orderService = new OrderServiceImpl(base);
		instrumentService = new InstrumentServiceImpl(base);
	}

	private void run(String username, String password) throws IOException {
//		sessionService.getStatus();
		
		Session session = sessionService.login(username, password);
		
//		for (AList aList : lists)
//        {
//            if ("SE".equals(aList.getCountry())) {
//                
//            }
//        }
		
		sessionService.getRealtimeAccesses();
//		sessionService.touch(session.getSessionKey());

//		List<Account> accounts = accountService.getAccounts();
//		for (Account account : accounts) {
//			AccountSummary as = accountService.getAccount(account.getAccountId());
//			List<Ledger> ledgers = accountService.getLedgers(account.getAccountId());
//			List<Position> positions = accountService.getPositions(account.getAccountId());
//			List<Order> orders = accountService.getOrders(account.getAccountId());
//			List<Trade> trades = accountService.getTrades(account.getAccountId());
//		}

//		PrintStream psLists = new PrintStream(new FileOutputStream("/tmp/nextapi/lists.log"));
//		List<AList> alists = instrumentService.getLists();
//		for (AList aList : alists) {
//		    psLists.println(String.format("%s,%s,%s", aList.getCountry(), aList.getListId(), aList.getName()));
//			List<AListItem> aListItems = instrumentService.getList(aList.getListId());
//            String id = String.format("%s-%s", aList.getCountry(), aList.getListId(), aList.getName());
//			PrintStream psList = new PrintStream(new FileOutputStream("/tmp/nextapi/list-" + id + ".log"));
//			for (AListItem aListItem : aListItems)
//            {
//			    psList.println(String.format("%s,%s,%s", aListItem.getMarketId(), aListItem.getIdentifier(), aListItem.getShortname()));
//            }
//			psList.close();
//		}
//		psLists.close();

//		BufferedReader file = new BufferedReader(new FileReader("/tmp/nextapi/list-SE-366337.log"));
//		String line = file.readLine();
//		while (line != null) {
//		    String[] split = line.split(",");
//		    if (split.length > 2) {
//	            String marketId = split[0];
//	            String identifier = split[1];
//	            String id = String.format("%s-%s", marketId, identifier);
//	            try {
//    	            List<Tick> chart = instrumentService.getChart(marketId, identifier);
//    	            LOG.info("handeling " + id);
//    	            PrintStream ps = new PrintStream(new FileOutputStream("/tmp/nextapi/chart-" + id + ".log"));
//    	            for (Tick tick : chart)
//                    {
//    	                ps.println(String.format("%s,%s,%s,%s", tick.getTimestamp(), tick.getPrice(), tick.getVolume(), tick.getChange()));
//                    }
//    	            ps.close();
//	            } catch (UniformInterfaceException e) {
//	                LOG.warning("Unable to fetch " + id);
//	            }
//		    }
//	        line = file.readLine();
//		}
//		file.close();
		sessionService.logout(session.getSessionKey());
	}

	public static void main(String[] args) throws IOException {
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
