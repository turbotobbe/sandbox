package sandbox.nextapi.model.account;


public class Position {

	private Double acqPrice;
	
	private Double acqPriceAcc;
	
	private Double pawnPercent;
	
	private Double qty;
	
	private Double marketValue;
	
	private Double marketValueAcc;
	
	private String instrumentMainMarketId;
	
	private String instrumentIdentifier;
	
	private String instrumentType;
	
	private String instrumentCurrency;
	
	private Double instrumentMainMarketPrice;

	public Double getAcqPrice() {
		return acqPrice;
	}

	public void setAcqPrice(Double acqPrice) {
		this.acqPrice = acqPrice;
	}

	public Double getAcqPriceAcc() {
		return acqPriceAcc;
	}

	public void setAcqPriceAcc(Double acqPriceAcc) {
		this.acqPriceAcc = acqPriceAcc;
	}

	public Double getPawnPercent() {
		return pawnPercent;
	}

	public void setPawnPercent(Double pawnPercent) {
		this.pawnPercent = pawnPercent;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public Double getMarketValueAcc() {
		return marketValueAcc;
	}

	public void setMarketValueAcc(Double marketValueAcc) {
		this.marketValueAcc = marketValueAcc;
	}

	public String getInstrumentMainMarketId() {
		return instrumentMainMarketId;
	}

	public void setInstrumentMainMarketId(String instrumentMainMarketId) {
		this.instrumentMainMarketId = instrumentMainMarketId;
	}

	public String getInstrumentIdentifier() {
		return instrumentIdentifier;
	}

	public void setInstrumentIdentifier(String instrumentIdentifier) {
		this.instrumentIdentifier = instrumentIdentifier;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public String getInstrumentCurrency() {
		return instrumentCurrency;
	}

	public void setInstrumentCurrency(String instrumentCurrency) {
		this.instrumentCurrency = instrumentCurrency;
	}

	public Double getInstrumentMainMarketPrice() {
		return instrumentMainMarketPrice;
	}

	public void setInstrumentMainMarketPrice(Double instrumentMainMarketPrice) {
		this.instrumentMainMarketPrice = instrumentMainMarketPrice;
	}

}
