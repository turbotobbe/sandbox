package sandbox.nextapi.service;

import java.util.List;

import sandbox.nextapi.model.instrument.AListItem;
import sandbox.nextapi.model.instrument.Derivative;
import sandbox.nextapi.model.instrument.Index;
import sandbox.nextapi.model.instrument.Instrument;
import sandbox.nextapi.model.instrument.InstrumentSummary;
import sandbox.nextapi.model.instrument.AList;
import sandbox.nextapi.model.instrument.Market;
import sandbox.nextapi.model.instrument.RelatedMarket;
import sandbox.nextapi.model.instrument.Tick;
import sandbox.nextapi.model.instrument.TickSize;
import sandbox.nextapi.model.instrument.TradingDay;
import sandbox.nextapi.model.instrument.Underlying;

public interface InstrumentService {
	
	List<Instrument> search(String query, String type, String country);
	
	InstrumentSummary lookup(String marketId, String identifier);
	
	List<InstrumentSummary> lookup(List<String> identifiers);
	
	List<Tick> getChart(String marketId, String identifier);
	
	List<AList> getLists();
	
	List<AListItem> getList(String listId);
	
	List<Market> getMarkets();
	
	List<TradingDay> getTradingDays();
	
	List<Index> getIndicies();
	
	List<TickSize> getTicksizes(String fooId);
	
	List<String> getCountries(String type);
	
	List<Underlying> getUnderlying(String type, String country);
	
	List<Derivative> getDerivatives(String type);
	
	List<RelatedMarket> getRelatedMarkets(String marketId, String identifier);
}
