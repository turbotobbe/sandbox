package sandbox.nextapi.service;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.crypto.Cipher;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import sandbox.nextapi.model.session.RealtimeAccess;
import sandbox.nextapi.model.session.Session;
import sandbox.nextapi.model.session.Status;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.Base64;

public class SessionServiceImpl extends AbstractService implements SessionService {

	private static final Logger LOG = Logger.getLogger(SessionServiceImpl.class.getName());
	
	private String keyFile = "/NEXTAPI_TEST_public.der";
	
	private Client client;

	public SessionServiceImpl(WebResource base, Client client) {
		super(base);
		this.client = client;
	}

	public SessionServiceImpl(WebResource base, Client client, String keyFile) {
		super(base);
		this.client = client;
		this.keyFile = keyFile;
	}

	@Override
	public Session login(String username, String password) {
		LOG.fine(String.format("enter! username=%s, password=%s", username, password));
		String auth = encrypt(username, password);
		WebResource resource = base.path("login");
		resource = addParam(resource, "service", "NEXTAPI");
		resource = addParam(resource, "auth", auth);
		JSONObject resp = resource.accept(JSON).post(JSONObject.class);
		Session s = new Session();
		try {
			LOG.info(resp.toString(2));
			s.setSessionKey(resp.getString("session_key"));
			s.setEnvironment(resp.getString("environment"));
			s.setExpiresIn(resp.getInt("expires_in"));
			s.setCountry(resp.getString("country"));
			
			JSONObject js = resp.getJSONObject("private_feed");
			s.setPrivateFeedPort(js.getInt("port"));
			s.setPrivateFeedHostname(js.getString("hostname"));
			s.setPrivateFeedEncrypted(js.getBoolean("encrypted"));
			
			js = resp.getJSONObject("public_feed");
			s.setPublicFeedPort(js.getInt("port"));
			s.setPublicFeedHostname(js.getString("hostname"));
			s.setPublicFeedEncrypted(js.getBoolean("encrypted"));
			
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		client.addFilter(new HTTPBasicAuthFilter(s.getSessionKey(), s.getSessionKey()));
		return s;
	}

	@Override
	public Boolean logout(String sessionKey) {
		LOG.fine(String.format("enter! sessionKey=%s", sessionKey));
		WebResource resource = base.path("login").path(sessionKey);
		JSONObject resp = resource.accept(JSON).delete(JSONObject.class);
		Boolean loggedIn = null;
		try {
			LOG.info(resp.toString(2));
			loggedIn = resp.getBoolean("logged_in");
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return loggedIn;
	}

	@Override
	public Boolean touch(String sessionKey) {
		LOG.fine(String.format("enter! sessionKey=%s", sessionKey));
		WebResource resource = base.path("login").path(sessionKey);
		JSONObject resp = resource.accept(JSON).put(JSONObject.class);
		Boolean loggedIn = null;
		try {
			LOG.info(resp.toString(2));
			loggedIn = resp.getBoolean("logged_in");
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return loggedIn;
	}

	@Override
	public List<RealtimeAccess> getRealtimeAccesses() {
		LOG.fine("enter!");
		WebResource resource = base.path("realtime_access");
		JSONArray resp = resource.accept(JSON).get(JSONArray.class);
		List<RealtimeAccess> list = new ArrayList<RealtimeAccess>();
		try {
			LOG.info(resp.toString(2));
			for (int i = 0; i < resp.length(); i++) {
				JSONObject realtimeAccess = resp.getJSONObject(i);
				RealtimeAccess ra = new RealtimeAccess();
				ra.setMarketId(realtimeAccess.getString("marketID"));
				ra.setLevel(realtimeAccess.getInt("level"));
				list.add(ra);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return list;
	}

	@Override
	public Status getStatus() {
		LOG.fine("enter!");
		JSONObject resp = base.accept(JSON).get(JSONObject.class);
		Status s = new Status();
		try {
			LOG.info(resp.toString(2));
			s.setTimestamp(resp.getLong("timestamp"));
			s.setValidVersion(resp.getBoolean("valid_version"));
			s.setSystemRunning(resp.getBoolean("system_running"));
			s.setSkipPhrase(resp.getBoolean("skip_phrase"));
			s.setMessage(resp.getString("message"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return s;
	}

	private String encrypt(String username, String password) {
		try {
			String un = new String(Base64.encode(username));
			String pw= new String(Base64.encode(password));
			String ct = new String(Base64.encode(String.valueOf(System.currentTimeMillis())));
			String auth = String.format("%s:%s:%s", un, pw, ct);
	
			InputStream in = getClass().getResourceAsStream(keyFile);
			DataInputStream dis = new DataInputStream(in);
			byte[] bytes = new byte[1024];
			int length = dis.read(bytes);
			dis.close();
			bytes = Arrays.copyOf(bytes, length);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PublicKey key = factory.generatePublic(spec);
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			bytes = cipher.doFinal(auth.getBytes());
			String param = new String(Base64.encode(bytes));
			return URLEncoder.encode(param, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("encrypt trouble", e);
		}
	}

}
