package sandbox.nextapi.model.instrument;

public class Derivative {
	
	private String shortname;
	
	private Integer multiplier;
	
	private Double strikeprice;
	
	private String expirydate;
	
	private String marketId;
	
	private String identifier;
	
	private String kind;
	
	private String expirytype;
	
	private String currency;
	
	private String callPut;

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public Integer getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Integer multiplier) {
		this.multiplier = multiplier;
	}

	public Double getStrikeprice() {
		return strikeprice;
	}

	public void setStrikeprice(Double strikeprice) {
		this.strikeprice = strikeprice;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getExpirytype() {
		return expirytype;
	}

	public void setExpirytype(String expirytype) {
		this.expirytype = expirytype;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCallPut() {
		return callPut;
	}

	public void setCallPut(String callPut) {
		this.callPut = callPut;
	}
	
}
