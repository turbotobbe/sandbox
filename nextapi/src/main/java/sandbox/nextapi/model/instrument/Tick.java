package sandbox.nextapi.model.instrument;

public class Tick {
	
	private String timestamp;
	
	private Float change;
	
	private Integer volume;
	
	private Float price;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Float getChange() {
		return change;
	}

	public void setChange(Float change) {
		this.change = change;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
}
