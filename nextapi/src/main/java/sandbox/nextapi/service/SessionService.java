package sandbox.nextapi.service;

import java.util.List;

import sandbox.nextapi.model.session.RealtimeAccess;
import sandbox.nextapi.model.session.Session;
import sandbox.nextapi.model.session.Status;

public interface SessionService {

	Session login(String username, String password);
	
	Boolean logout(String sessionKey);
	
	Boolean touch(String sessionKey);
	
	List<RealtimeAccess> getRealtimeAccesses();
	
	Status getStatus();
}
