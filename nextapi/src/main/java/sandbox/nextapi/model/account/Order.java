package sandbox.nextapi.model.account;

public class Order {

	private String exchangeOrderId;
	
	private Integer orderId;
	
	private String activationConditionType;
	
	private String activationConditionDate;
	
	private String activationConditionPriceCurrency;
	
	private Double activationConditionPriceValue;
	
	private String regdate;
	
	private String priceCondition;
	
	private String priceCurrency;
	
	private Double priceValue;
	
	private String volumeCondition;
	
	private Double volume;
	
	private String side;
	
	private Double tradedVolume;
	
	private String accno;
	
	private Integer instrumentMarketId;
	
	private String instrumentIdentifier;
	
	private String validityType;
	
	private String validityDate;
	
	private String orderState;
	
	private String statusText;

	public String getExchangeOrderId() {
		return exchangeOrderId;
	}

	public void setExchangeOrderId(String exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getActivationConditionType() {
		return activationConditionType;
	}

	public void setActivationConditionType(String activationConditionType) {
		this.activationConditionType = activationConditionType;
	}

	public String getActivationConditionDate() {
		return activationConditionDate;
	}

	public void setActivationConditionDate(String activationConditionDate) {
		this.activationConditionDate = activationConditionDate;
	}

	public String getActivationConditionPriceCurrency() {
		return activationConditionPriceCurrency;
	}

	public void setActivationConditionPriceCurrency(
			String activationConditionPriceCurrency) {
		this.activationConditionPriceCurrency = activationConditionPriceCurrency;
	}

	public Double getActivationConditionPriceValue() {
		return activationConditionPriceValue;
	}

	public void setActivationConditionPriceValue(
			Double activationConditionPriceValue) {
		this.activationConditionPriceValue = activationConditionPriceValue;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getPriceCondition() {
		return priceCondition;
	}

	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
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

	public String getVolumeCondition() {
		return volumeCondition;
	}

	public void setVolumeCondition(String volumeCondition) {
		this.volumeCondition = volumeCondition;
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

	public Double getTradedVolume() {
		return tradedVolume;
	}

	public void setTradedVolume(Double tradedVolume) {
		this.tradedVolume = tradedVolume;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
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

	public String getValidityType() {
		return validityType;
	}

	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
}
