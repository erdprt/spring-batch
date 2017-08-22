package fr.erdprt.samples.persistance.model.products.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.erdprt.samples.persistance.model.products.Order;
import fr.erdprt.samples.persistance.model.products.Person;
import fr.erdprt.samples.persistance.model.products.Product;
import fr.erdprt.samples.persistance.model.products.RefProduct;
import fr.erdprt.samples.persistance.model.products.services.dao.OrderDao;
import fr.erdprt.samples.persistance.model.products.services.dao.PersonDao;
import fr.erdprt.samples.persistance.model.products.services.dao.RefProductDao;

@Service
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private RefProductDao refProductDao;
	
	@Transactional
	@Override
	public Order saveOrUpdate(Order order) {
		
		double orderPrice	=	0;
		
		for (Product product:order.getProducts()) {
			RefProduct refProduct	=	this.refProductDao.findByCode(product.getRefProduct().getCode());
			product.setRefProduct(refProduct);
			orderPrice	+=	product.getPrice();
		}
		order.setPrice(orderPrice);
		this.orderDao.save(order);
		return order;
	}
}
