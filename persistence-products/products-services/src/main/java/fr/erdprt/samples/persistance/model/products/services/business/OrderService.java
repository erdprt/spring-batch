package fr.erdprt.samples.persistance.model.products.services.business;

import fr.erdprt.samples.persistance.model.products.Order;

public interface OrderService {

	public Order saveOrUpdate(Order order);
}