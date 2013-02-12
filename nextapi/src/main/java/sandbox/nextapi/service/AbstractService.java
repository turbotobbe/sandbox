package sandbox.nextapi.service;

import java.io.PrintStream;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.WebResource;

public class AbstractService {

	public final static MediaType JSON = MediaType.APPLICATION_JSON_TYPE;

	protected WebResource base;

	public AbstractService(WebResource base) {
		this.base = base;
	}

	protected WebResource addParam(WebResource resource, String key, String value) {
		if (key != null) {
			resource = resource.queryParam(key, value);
		}
		return resource;
	}
}
