package sandbox.nextapi.model.account;

public class Trade {

	private String accno;
	
	private Integer orderId;
	
	private Double volume;
	
	private String side;
	
	private String tradeId;
	
	private String couterparty;
	
	private String tradetime;

	private Integer instrumentMarketId;
	
	private String instrumentIdentifier;
	
	private String priceCurrency;
	
	private Double priceValue;
	
	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getCouterparty() {
		return couterparty;
	}

	public void setCouterparty(String couterparty) {
		this.couterparty = couterparty;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public Integer getInstrumentMarketId() {
		return instrumentMarketId;
	}

	public void setInstrumentMarketId(Integer instrumentMarketId) {
		this.instrumentMarketId = instrumentMarketId;
	}

	public String getInstrumentIdentifier() {
		return instrumentIdentifier;
	}

	public void setInstrumentIdentifier(String instrumentIdentifier) {
		this.instrumentIdentifier = instrumentIdentifier;
	}

	public String getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(String priceCurrency) {
		this.priceCurrency = priceCurrency;
	}

	public Double getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(Double priceValue) {
		this.priceValue = priceValue;
	}

}
