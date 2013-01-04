package sandbox.nextapi.service;

import sandbox.nextapi.model.order.OrderStatus;

public interface OrderService {

	OrderStatus entry(String accountId, String marketId, String instrumentId, String price, String volume, String side, String currency, String orderType, String valueUntil, String openVolume, String smartOrder);
	
	OrderStatus modify(String accountId, String orderId, String price, String volume);
	
	OrderStatus delete(String accountId, String orderId);
}
