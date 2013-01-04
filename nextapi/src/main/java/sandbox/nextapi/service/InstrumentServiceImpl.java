package sandbox.nextapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import sandbox.nextapi.model.instrument.AList;
import sandbox.nextapi.model.instrument.AListItem;
import sandbox.nextapi.model.instrument.Derivative;
import sandbox.nextapi.model.instrument.Index;
import sandbox.nextapi.model.instrument.Instrument;
import sandbox.nextapi.model.instrument.InstrumentSummary;
import sandbox.nextapi.model.instrument.Market;
import sandbox.nextapi.model.instrument.RelatedMarket;
import sandbox.nextapi.model.instrument.Tick;
import sandbox.nextapi.model.instrument.TickSize;
import sandbox.nextapi.model.instrument.TradingDay;
import sandbox.nextapi.model.instrument.Underlying;

import com.sun.jersey.api.client.WebResource;

public class InstrumentServiceImpl extends AbstractService implements InstrumentService {

	private static final Logger LOG = Logger.getLogger(InstrumentServiceImpl.class.getName());

	public InstrumentServiceImpl(WebResource base) {
		super(base);
	}

	@Override
	public List<Instrument> search(String query, String type, String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstrumentSummary lookup(String marketId, String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstrumentSummary> lookup(List<String> identifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tick> getChart(String marketId, String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AList> getLists() {
		LOG.fine("enter!");
		JSONArray resp = base.path("lists").accept(JSON).get(JSONArray.class);
		List<AList> list = new ArrayList<AList>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject json = resp.getJSONObject(i);
				AList a = new AList();
				a.setListId(json.getString("id"));
				a.setName(json.getString("name"));
				a.setCountry(json.getString("country"));
				list.add(a);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

	@Override
	public List<AListItem> getList(String listId) {
		LOG.fine(String.format("enter! listId=%s", listId));
		JSONArray resp = base.path("lists").path(listId).accept(JSON).get(JSONArray.class);
		List<AListItem> list = new ArrayList<AListItem>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject json = resp.getJSONObject(i);
				AListItem a = new AListItem();
				a.setMarketId(json.getString("marketID"));
				a.setIdentifier(json.getString("identifier"));
				a.setShortname(json.getString("shortname"));
				list.add(a);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

	@Override
	public List<Market> getMarkets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TradingDay> getTradingDays() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Index> getIndicies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TickSize> getTicksizes(String fooId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCountries(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Underlying> getUnderlying(String type, String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Derivative> getDerivatives(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RelatedMarket> getRelatedMarkets(String marketId, String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
