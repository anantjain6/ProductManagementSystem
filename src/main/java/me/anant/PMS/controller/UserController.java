package me.anant.PMS.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.anant.PMS.model.Password;
import me.anant.PMS.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/change-password")
	public ModelAndView changePasswordView() {
		ModelAndView modelAndView = new ModelAndView("user/change-password");
		modelAndView.addObject("command", new Password());
		return modelAndView;
	}
	
	@PostMapping("/user/change-password")
	public String changePassword(@ModelAttribute("command") @Valid Password password, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(userService.updateUserPassword(userName, password)) {
			redirectAttributes.addFlashAttribute("msg", "Password Changed successfully");
			redirectAttributes.addFlashAttribute("class", "alert-success");
		}
		else {
			redirectAttributes.addFlashAttribute("msg", "Error changing Password");
			redirectAttributes.addFlashAttribute("class", "alert-danger");
		}
		return "redirect:/user/change-password";
	}

}
