package sandbox.nextapi.model.session;

public class Status {

	private Long timestamp;
	
	private Boolean validVersion;
	
	private Boolean systemRunning;
	
	private Boolean skipPhrase;
	
	private String message;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getValidVersion() {
		return validVersion;
	}

	public void setValidVersion(Boolean validVersion) {
		this.validVersion = validVersion;
	}

	public Boolean getSystemRunning() {
		return systemRunning;
	}

	public void setSystemRunning(Boolean systemRunning) {
		this.systemRunning = systemRunning;
	}

	public Boolean getSkipPhrase() {
		return skipPhrase;
	}

	public void setSkipPhrase(Boolean skipPhase) {
		this.skipPhrase = skipPhase;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
