package sandbox.nextapi.service;

import java.util.List;

import sandbox.nextapi.model.account.Account;
import sandbox.nextapi.model.account.AccountSummary;
import sandbox.nextapi.model.account.Ledger;
import sandbox.nextapi.model.account.Order;
import sandbox.nextapi.model.account.Position;
import sandbox.nextapi.model.account.Trade;

public interface AccountService {
	
	List<Account> getAccounts();
	
	AccountSummary getAccount(String accountId);
	
	List<Ledger> getLedgers(String accountId);

	List<Position> getPositions(String accountId);

	List<Order> getOrders(String accountId);
	
	List<Trade> getTrades(String accountId);
	
}
