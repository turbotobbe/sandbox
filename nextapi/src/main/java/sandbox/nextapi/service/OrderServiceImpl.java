package sandbox.nextapi.service;

import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import sandbox.nextapi.model.order.OrderStatus;

import com.sun.jersey.api.client.WebResource;

public class OrderServiceImpl extends AbstractService implements OrderService {

	private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class.getName());

	public OrderServiceImpl(WebResource base) {
		super(base);
	}

	@Override
	public OrderStatus entry(String accountId, String marketId, String instrumentId, String price, String volume, String side, String currency, String orderType, String valueUntil, String openVolume, String smartOrder) {
		LOG.fine(String.format("enter! accountId=%s, marketId=%s, instrumentId=%s, price=%s, volume=%s, side=%s, currency=%s, orderType=%s, valueUntil=%s, openVolume=%s, smartOrder=%s", accountId, marketId, instrumentId, price, volume, side, currency, orderType, valueUntil, openVolume, smartOrder));
		WebResource resource = base.path("accounts").path(accountId).path("orders");
		resource = addParam(resource, "marketID", marketId);
		resource = addParam(resource, "identifier", instrumentId);
		resource = addParam(resource, "price", price);
		resource = addParam(resource, "volume", volume);
		resource = addParam(resource, "side", side);
		resource = addParam(resource, "currency", currency);
		resource = addParam(resource, "order_type", orderType);
		resource = addParam(resource, "valid_until", valueUntil);
		resource = addParam(resource, "open_volume", openVolume);
		resource = addParam(resource, "smart_order", smartOrder);
		JSONObject resp = resource.accept(JSON).post(JSONObject.class);
		OrderStatus os = new OrderStatus();
		try {
			LOG.info(resp.toString(2));
			os.setOrderId(resp.getInt("orderID"));
			os.setOrderState(resp.getString("orderState"));
			os.setActionState(resp.getString("actionState"));
			os.setResultCode(resp.getString("resultCode"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return os;
	}

	@Override
	public OrderStatus modify(String accountId, String orderId, String price, String volume) {
		LOG.fine(String.format("enter! accountId=%s, orderId=%s, price=%s, volume=%s", accountId, orderId, price, volume));
		WebResource resource = base.path("accounts").path(accountId).path("orders").path(orderId);
		resource = addParam(resource, "price", price);
		resource = addParam(resource, "volume", volume);
		JSONObject resp = resource.accept(JSON).put(JSONObject.class);
		OrderStatus os = new OrderStatus();
		try {
			LOG.info(resp.toString(2));
			os.setOrderId(resp.getInt("orderID"));
			os.setOrderState(resp.getString("orderState"));
			os.setActionState(resp.getString("actionState"));
			os.setResultCode(resp.getString("resultCode"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return os;
	}

	@Override
	public OrderStatus delete(String accountId, String orderId) {
		LOG.fine(String.format("enter! accountId=%s, orderId=%s", accountId, orderId));
		WebResource resource = base.path("accounts").path(accountId).path("orders").path(orderId);
		JSONObject resp = resource.accept(JSON).delete(JSONObject.class);
		OrderStatus os = new OrderStatus();
		try {
			LOG.info(resp.toString(2));
			os.setOrderId(resp.getInt("orderID"));
			os.setOrderState(resp.getString("orderState"));
			os.setActionState(resp.getString("actionState"));
			os.setResultCode(resp.getString("resultCode"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("json trouble", e);
		}
		return os;
	}

}
