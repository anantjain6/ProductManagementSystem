package me.anant.PMS.controller;

import java.awt.Dialog.ModalExclusionType;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.anant.PMS.model.Order;
import me.anant.PMS.model.OrderProduct;
import me.anant.PMS.model.Product;
import me.anant.PMS.model.User;
import me.anant.PMS.service.EmailService;
import me.anant.PMS.service.OrderService;
import me.anant.PMS.service.ProductService;
import me.anant.PMS.service.UserService;

/**
 * This Order Controller has rest endpoints which are responsible to perform following functionalities :
 * <ul>
 *     <li>Place an order</li>
 *     <li>View Order</li>
 *     <li>View All Orders - Used By Admin</li>
 *     <li>View Order Details Of a particular Customer</li>
 *     <li>Change Order Status</li>
 *     <li>Cancel an Order</li>
 * </ul>
 *
 */
@Controller
public class OrderController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	EmailService emailService;

	/**
	 * This GET api is responsible to view the home screen for Place Orders
	 * @return ModelAndView
	 */
	@GetMapping("/customer/order_place")
	public ModelAndView customerHome() {
		List<Product> pList =  productService.get();
		ModelAndView modelAndView = new ModelAndView("customer/home");
		modelAndView.addObject("pList", pList);
		return modelAndView;
	}

	/**
	 * This POST api is responsible to place an Order
	 * @param request
	 * @param principal
	 * @return ModelAndView
	 */
	@PostMapping("/customer/order_place")
	public ModelAndView orderPlace(HttpServletRequest request, Principal principal) {
		String[] pIds = request.getParameterValues("productId");
		Set<OrderProduct> opList = new HashSet<>();
		for(String pId: pIds) {
			long pid = Long.parseLong(pId);
			Product product = productService.findById(pid).get();
			int buyqty = Integer.parseInt(request.getParameter(pId));
			opList.add(new OrderProduct(product, buyqty));
			productService.deductQty(pid, buyqty);
		}
		User user = userService.findByEmail(principal.getName());
		orderService.save(new Order(user, "PROCESSING", opList));
		
		String message = "Hello,<br><br>Your order has been placed successfuly. Following is the detail of your order.<br><br>"
				+ "<table>" + 
				"<tr>" + 
				"<th>Name</th>" + 
				"<th>Price</th>" + 
				"<th>Qty</th>" + 
				"<th>Amount</th>" + 
				"</tr>";
		float sum = 0;
		for (OrderProduct op : opList)
		{
			sum = sum + op.getProduct().getProductPrice() * op.getBuyqty();
			message = message + "<tr>" + 
					"<td>"+op.getProduct().getProductName()+"</td>" + 
					"<td>Rs. "+op.getProduct().getProductPrice()+"</td>" + 
					"<td>"+op.getBuyqty()+"</td>" + 
					"<td>Rs. "+op.getProduct().getProductPrice() * op.getBuyqty()+"</td>" + 
					"</tr>";
		}
		message = message + "<tr><td  colspan=\"3\"><center><b>Total Amount</b></center></td><td>Rs. "+sum+"</td></tr>" + 
				"</table>";
		emailService.send(principal.getName(), "Order Placed successfully", message);
		
		ModelAndView modelAndView = new ModelAndView("customer/order_place");
		modelAndView.addObject("opList", opList);
		return modelAndView;
	}

	/**
	 * This Get api is responsible to View Customer's Order.
	 *
	 * @param principal
	 * @return ModelAndView
	 */
	@GetMapping("customer/order/list")
	public ModelAndView viewMyOrder(Principal principal) {
		User user = userService.findByEmail(principal.getName());
		ModelAndView modelAndView = new ModelAndView("customer/order/list");
		modelAndView.addObject("orderList", user.getOrders());
		return modelAndView;
	}

	/**
	 * This GET api is responsible to List All Orders.
	 * @return
	 */
	@GetMapping("admin/order/list")
	public ModelAndView viewAllOrder() {
		List<Order> oList = orderService.get();
		ModelAndView modelAndView = new ModelAndView("admin/order/list");
		modelAndView.addObject("oList", oList);
		return modelAndView;
	}

	/**
	 * This GET api is responsible to view the details of an Order.
	 * This feature is used by Admin Module.
	 * @param id
	 * @return ModelAndView
	 */
	@GetMapping("admin/order/detail")
	public ModelAndView orderDetailAdmin(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView("admin/order/detail");
		modelAndView.addObject("order", orderService.findById(id).get());
		return modelAndView;
	}

	/**
	 * This GET api is responsible to view the details of an order by passing Order Id.
	 * This api is used by Customer Module.
	 * @param id
	 * @return ModelAndView
	 */
	@GetMapping("customer/order/detail")
	public ModelAndView orderDetailCustomer(@RequestParam("id") long id) {
		ModelAndView modelAndView = new ModelAndView("customer/order/detail");
		modelAndView.addObject("order", orderService.findById(id).get());
		return modelAndView;
	}

	/**
	 * This GET api is responsible to cancel an Order.
	 * @param id
	 * @param redirectAttributes
	 * @return String containing message
	 */
	@GetMapping("customer/order/cancel")
	public String orderCancel(@RequestParam("id") long id, final RedirectAttributes redirectAttributes) {
		if(orderService.cancelOrder(id)) {
			redirectAttributes.addFlashAttribute("msg", "Order cancelled successfully");
			redirectAttributes.addFlashAttribute("class", "alert-success");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Order not cancelled, since you can cencel PROCESSING or CONFIRMED order with 24 hours only.");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
		}
		return "redirect:/customer/order/list";
	}

	/**
	 * This POST api changeStatus is responsible to change the status of an Order.
	 * @param id
	 * @param status
	 * @param redirectAttributes
	 * @return String {Message containing "status of order changed"}
	 */
	@PostMapping("admin/order/status")
	public String changeStatus(@RequestParam("id") long id, @RequestParam("status") String status, final RedirectAttributes redirectAttributes) {
		orderService.changeStatus(id, status);
		redirectAttributes.addFlashAttribute("msg", "Status of order changed.");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/order/list";
	}
}
