package sandbox.nextapi.model.session;


public class Session {
	
	private String sessionKey;
	
	private String environment;
	
	private Integer expiresIn;
	
	private String country;
	
	private Integer publicFeedPort;
	
	private String publicFeedHostname;
	
	private Boolean publicFeedEncrypted;
	
	private Integer privateFeedPort;
	
	private String privateFeedHostname;
	
	private Boolean privateFeedEncrypted;
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPublicFeedPort() {
		return publicFeedPort;
	}

	public void setPublicFeedPort(Integer publicFeedPort) {
		this.publicFeedPort = publicFeedPort;
	}

	public String getPublicFeedHostname() {
		return publicFeedHostname;
	}

	public void setPublicFeedHostname(String publicFeedHostname) {
		this.publicFeedHostname = publicFeedHostname;
	}

	public Boolean getPublicFeedEncrypted() {
		return publicFeedEncrypted;
	}

	public void setPublicFeedEncrypted(Boolean publicFeedEncrypted) {
		this.publicFeedEncrypted = publicFeedEncrypted;
	}

	public Integer getPrivateFeedPort() {
		return privateFeedPort;
	}

	public void setPrivateFeedPort(Integer privateFeedPort) {
		this.privateFeedPort = privateFeedPort;
	}

	public String getPrivateFeedHostname() {
		return privateFeedHostname;
	}

	public void setPrivateFeedHostname(String privateFeedHostname) {
		this.privateFeedHostname = privateFeedHostname;
	}

	public Boolean getPrivateFeedEncrypted() {
		return privateFeedEncrypted;
	}

	public void setPrivateFeedEncrypted(Boolean privateFeedEncrypted) {
		this.privateFeedEncrypted = privateFeedEncrypted;
	}
	
}
