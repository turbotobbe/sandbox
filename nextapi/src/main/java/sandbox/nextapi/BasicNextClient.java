package sandbox.nextapi;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.Base64;

public class BasicNextClient {

	public final static MediaType MEDIATYPE = MediaType.APPLICATION_JSON_TYPE;

    private static final String BASEURL = "https://api.test.nordnet.se/next/1";

    private final String keyFile;

	protected final WebResource resource;

	private final Client client;

	private String sessionKey;

	public BasicNextClient(String keyFile) {
		this.keyFile = keyFile;
		this.client = Client.create();
		this.resource = client.resource(BASEURL);
	}

	public void login(String username, String password) throws Exception {
		System.out.println("LOGIN");
		String auth = encrypt(username, password);
		JSONObject response = resource.path("login")
				.queryParam("service", "NEXTAPI").queryParam("auth", auth)
				.accept(MEDIATYPE).post(JSONObject.class);
		sessionKey = response.getString("session_key");
		client.addFilter(new HTTPBasicAuthFilter(sessionKey, sessionKey));
		System.out.println(response.toString(2));
	}
	
	public void logout() throws JSONException {
		System.out.println("LOGOUT");
		JSONObject response = resource.path("login").path(sessionKey).accept(MEDIATYPE).delete(JSONObject.class);
		System.out.println(response.toString(2));
	}

	private String encrypt(String username, String password) throws Exception{
		String un = new String(Base64.encode(username));
		String pw= new String(Base64.encode(password));
		String ct = new String(Base64.encode(String.valueOf(System.currentTimeMillis())));
		String auth = String.format("%s:%s:%s", un, pw, ct);

		InputStream in = BasicNextClient.class.getResourceAsStream(keyFile);
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
	}

}
