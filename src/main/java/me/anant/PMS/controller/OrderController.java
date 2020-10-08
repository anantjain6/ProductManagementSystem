package me.anant.PMS.controller;

import java.awt.Dialog.ModalExclusionType;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import me.anant.PMS.Helper.EmailHelper;
import me.anant.PMS.Helper.ModelAndViewProviderHelper;
import me.anant.PMS.RequestMappingConstants.ViewConstants;
import me.anant.PMS.exceptions.OrderNotFoundException;
import me.anant.PMS.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

	ProductService productService;
	UserService userService;
	OrderService orderService;
	EmailHelper emailHelper;
	ModelAndViewProviderHelper modelAndViewProviderHelper;


	public OrderController(ProductService productService,
						   UserService userService,OrderService orderService,
						   @Lazy(value = true) EmailHelper emailHelper,
							ModelAndViewProviderHelper modelAndViewProviderHelper)
	{
		this.productService=productService;
		this.userService=userService;
		this.orderService=orderService;
		this.emailHelper=emailHelper;
		this.modelAndViewProviderHelper=modelAndViewProviderHelper;
	}

	/**
	 * This GET api is responsible to view the home screen for Place Orders
	 * @return ModelAndView
	 */
	@GetMapping("/customer/order_place")
	public ModelAndView customerHome() {
		List<Product> pList =  productService.get();
		return this.modelAndViewProviderHelper.generateModelAndView(ViewConstants.CUSTOMER_HOME_PAGE,"pList",pList);

	}

	/**
	 * This POST api is responsible to place an Order
	 * @param request
	 * @param principal
	 * @return ModelAndView
	 */
	@PostMapping("/customer/order_place")
	public ModelAndView orderPlace(HttpServletRequest request, Principal principal) throws ProductNotFoundException{
		String[] pIds = request.getParameterValues("productId");
		Set<OrderProduct> opList=this.orderService.createOrderedProduct(pIds,request);
		User user = userService.findByEmail(principal.getName());
		orderService.save(new Order(user, "PROCESSING", opList));

		this.emailHelper.sendSuccessfulOrderEmail(principal, opList);
		return this.modelAndViewProviderHelper.
				generateModelAndView(ViewConstants.CUSTOMER_ORDER_PLACE,"opList",opList);
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
		return this.modelAndViewProviderHelper.
				generateModelAndView(ViewConstants.CUSTOMER_ORDER_LIST,"orderList",user.getOrders());
	}

	/**
	 * This GET api is responsible to List All Orders.
	 * @return ModelAnndView
	 */
	@GetMapping("admin/order/list")
	public ModelAndView viewAllOrder() {
		List<Order> oList = orderService.get();
		return this.modelAndViewProviderHelper.
				generateModelAndView(ViewConstants.ADMIN_ORDER_LIST,"oList",oList);
	}

	/**
	 * This GET api is responsible to view the details of an Order.
	 * This feature is used by Admin Module.
	 * @param id
	 * @return ModelAndView
	 */
	@GetMapping("admin/order/detail")
	public ModelAndView orderDetailAdmin(@RequestParam("id") long id) throws ProductNotFoundException {
		return this.modelAndViewProviderHelper.
				generateModelAndView(ViewConstants.ADMIN_ORDER_DETAIL,"order", orderService.findById(id));
	}

	/**
	 * This GET api is responsible to view the details of an order by passing Order Id.
	 * This api is used by Customer Module.
	 * @param id
	 * @return ModelAndView
	 */
	@GetMapping("customer/order/detail")
	public ModelAndView orderDetailCustomer(@RequestParam("id") long id) throws ProductNotFoundException{
		return this.modelAndViewProviderHelper.
				generateModelAndView(ViewConstants.CUSTOMER_ORDER_DETAIL,"order", orderService.findById(id));
	}

	/**
	 * This GET api is responsible to cancel an Order.
	 * @param id
	 * @param redirectAttributes
	 * @return String redirect path
	 */
	@GetMapping("customer/order/cancel")
	public String orderCancel(@RequestParam("id") long id, final RedirectAttributes redirectAttributes) throws ProductNotFoundException {
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
	 * @return String redirect path
	 */
	@PostMapping("admin/order/status")
	public String changeStatus(@RequestParam("id") long id, @RequestParam("status") String status, final RedirectAttributes redirectAttributes) throws OrderNotFoundException, ProductNotFoundException {
		orderService.changeStatus(id, status);
		redirectAttributes.addFlashAttribute("msg", "Status of order changed.");
		redirectAttributes.addFlashAttribute("class", "alert-success");
		return "redirect:/admin/order/list";
	}
}
