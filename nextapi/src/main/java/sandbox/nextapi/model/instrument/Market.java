package sandbox.nextapi.model.instrument;

import java.util.ArrayList;
import java.util.List;

public class Market {
	
	private String name;
	
	private String marketId;
	
	private String country;
	
	private final List<OrderType> ordertypes = new ArrayList<OrderType>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getMarketId() {
		return marketId;
	}


	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public List<OrderType> getOrdertypes() {
		return ordertypes;
	}


	public static class OrderType {
		
		private String type;
		
		private String text;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
	}
}
