package me.anant.PMS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping("/hoho")
	public String home() {
		System.out.println("hi");
		return "index";
	}
}
