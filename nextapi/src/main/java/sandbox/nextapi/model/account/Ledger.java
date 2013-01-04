package sandbox.nextapi.model.account;

public class Ledger {

	private String currency;
	
	private Double accountSum;
	
	private Double accountSumAcc;
	
	private Double accIntCred;
	
	private Double accIntDeb;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAccountSum() {
		return accountSum;
	}

	public void setAccountSum(Double accountSum) {
		this.accountSum = accountSum;
	}

	public Double getAccountSumAcc() {
		return accountSumAcc;
	}

	public void setAccountSumAcc(Double accountSumAcc) {
		this.accountSumAcc = accountSumAcc;
	}

	public Double getAccIntCred() {
		return accIntCred;
	}

	public void setAccIntCred(Double accIntCred) {
		this.accIntCred = accIntCred;
	}

	public Double getAccIntDeb() {
		return accIntDeb;
	}

	public void setAccIntDeb(Double accIntDeb) {
		this.accIntDeb = accIntDeb;
	}
	
	
}
