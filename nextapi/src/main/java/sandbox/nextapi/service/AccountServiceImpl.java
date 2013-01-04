package sandbox.nextapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import sandbox.nextapi.model.account.Account;
import sandbox.nextapi.model.account.AccountSummary;
import sandbox.nextapi.model.account.Ledger;
import sandbox.nextapi.model.account.Order;
import sandbox.nextapi.model.account.Position;
import sandbox.nextapi.model.account.Trade;

import com.sun.jersey.api.client.WebResource;

public class AccountServiceImpl extends AbstractService implements AccountService {

	private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class.getName());

	public AccountServiceImpl(WebResource base) {
		super(base);
	}

	@Override
	public List<Account> getAccounts() {
		LOG.fine("enter!");
		JSONArray resp = base.path("accounts").accept(JSON).get(JSONArray.class);
		List<Account> list = new ArrayList<Account>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject json = resp.getJSONObject(i);
				Account a = new Account();
				a.setAccountId(json.getString("id"));
				a.setDefaultAccount(json.optBoolean("default", false));
				list.add(a);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

	@Override
	public AccountSummary getAccount(String accountId) {
		LOG.fine(String.format("enter! accountId=%s", accountId));
		JSONObject resp = base.path("accounts").path(accountId).accept(JSON).get(JSONObject.class);
		AccountSummary as = new AccountSummary();
		try {
			LOG.info(resp.toString(2));
			as.setAccountCurrency(resp.getString("accountCurrency"));
			as.setAccountSum(resp.getDouble("accountSum"));
			as.setCollateral(resp.getDouble("collateral"));
			as.setForwardSum(resp.getDouble("forwardSum"));
			as.setFullMarketValue(resp.getDouble("fullMarketvalue"));
			as.setFutureSum(resp.getDouble("futureSum"));
			//as.setHasBankServices(resp.getBoolean("has_bank_services"));
			as.setInterest(resp.getDouble("interest"));
			as.setLoanLimit(resp.getDouble("loanLimit"));
			as.setOwnCapital(resp.getDouble("ownCapital"));
			as.setOwnCapitalMorning(resp.getDouble("ownCapitalMorning"));
			as.setPawnValue(resp.getDouble("pawnValue"));
			as.setTradingPower(resp.getDouble("tradingPower"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return as;
	}

	@Override
	public List<Ledger> getLedgers(String accountId) {
		LOG.fine(String.format("enter! accountId=%s", accountId));
		JSONArray resp = base.path("accounts").path(accountId).path("ledgers").accept(JSON).get(JSONArray.class);
		List<Ledger> list = new ArrayList<Ledger>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject json = resp.getJSONObject(i);
				Ledger l = new Ledger();
				l.setCurrency(json.getString("currency"));
				l.setAccountSum(json.getDouble("accountSum"));
				l.setAccountSumAcc(json.getDouble("accountSumAcc"));
				l.setAccIntCred(json.getDouble("accIntCred"));
				l.setAccIntDeb(json.getDouble("accIntDeb"));
				list.add(l);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

	@Override
	public List<Position> getPositions(String accountId) {
		// TODO untested
		LOG.fine(String.format("enter! accountId=%s", accountId));
		JSONArray resp = base.path("accounts").path(accountId).path("positions").accept(JSON).get(JSONArray.class);
		List<Position> list = new ArrayList<Position>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject json = resp.getJSONObject(i);
				Position p = new Position();
				p.setAcqPrice(json.getDouble("acqPrice"));
				p.setAcqPriceAcc(json.getDouble("acqPriceAcc"));
				p.setPawnPercent(json.getDouble("pawnPercent"));
				p.setQty(json.getDouble("qty"));
				p.setMarketValue(json.getDouble("marketValue"));
				p.setMarketValueAcc(json.getDouble("marketValueAcc"));
				
				JSONObject js = json.getJSONObject("instrumentID");
				p.setInstrumentMainMarketId(js.getString("mainMarketId"));
				p.setInstrumentIdentifier(js.getString("identifier"));
				p.setInstrumentType(js.getString("type"));
				p.setInstrumentCurrency(js.getString("currency"));
				p.setInstrumentMainMarketPrice(js.getDouble("mainMarketPrice"));
				
				list.add(p);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

	@Override
	public List<Order> getOrders(String accountId) {
		// TODO untested
		LOG.fine(String.format("enter! accountId=%s", accountId));
		JSONArray resp = base.path("accounts").path(accountId).path("positions").accept(JSON).get(JSONArray.class);
		List<Order> list = new ArrayList<Order>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject json = resp.getJSONObject(i);
				Order o = new Order();
				o.setExchangeOrderId(json.getString("exchangeOrderID"));
				o.setOrderId(json.getInt("orderID"));
				o.setRegdate(json.getString("regdate"));
				o.setPriceCondition(json.getString("priceCondition"));
				o.setVolumeCondition(json.getString("volumeCondition"));
				o.setVolume(json.getDouble("volume"));
				o.setSide(json.getString("side"));
				o.setTradedVolume(json.getDouble("tradedVolume"));
				o.setAccno(json.getString("accno"));
				o.setOrderState(json.getString("orderState"));
				o.setStatusText(json.getString("statusText"));
				
				JSONObject js = json.getJSONObject("activationCondition");
				o.setActivationConditionType(js.getString("type"));
				o.setActivationConditionDate(js.getString("date"));
				
				js = js.getJSONObject("price");
				o.setActivationConditionPriceCurrency(js.getString("curr"));
				o.setActivationConditionPriceValue(js.getDouble("value"));
				
				js = json.getJSONObject("price");
				o.setPriceCurrency(js.getString("curr"));
				o.setPriceValue(js.getDouble("value"));
				
				js = json.getJSONObject("instrumentID");
				o.setInstrumentMarketId(js.getInt("marketID"));
				o.setInstrumentIdentifier(js.getString("identifier"));
				
				js = json.getJSONObject("validity");
				o.setValidityType(js.getString("type"));
				o.setValidityDate(js.getString("date"));
				
				list.add(o);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

	@Override
	public List<Trade> getTrades(String accountId) {
		// TODO untested
		LOG.fine(String.format("enter! accountId=%s", accountId));
		JSONArray resp = base.path("accounts").path(accountId).path("trades").accept(JSON).get(JSONArray.class);
		List<Trade> list = new ArrayList<Trade>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject json = resp.getJSONObject(i);
				Trade t = new Trade();
				t.setAccno(json.getString("accno"));
				t.setOrderId(json.getInt("orderID"));
				t.setVolume(json.getDouble("volume"));
				t.setSide(json.getString("side"));
				t.setTradeId(json.getString("tradeID"));
				t.setCouterparty(json.getString("counterparty"));
				t.setTradetime(json.getString("tradetime"));
				
				JSONObject js = json.getJSONObject("instrumentID");
				t.setInstrumentMarketId(js.getInt("marketID"));
				t.setInstrumentIdentifier(js.getString("identifier"));
				
				js = json.getJSONObject("price");
				t.setPriceCurrency(js.getString("curr"));
				t.setPriceValue(js.getDouble("value"));
				
				list.add(t);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

}
