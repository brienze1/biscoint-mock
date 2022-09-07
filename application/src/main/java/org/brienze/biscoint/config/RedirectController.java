package org.brienze.biscoint.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RedirectController {
	
	@GetMapping("/swagger")
	public ModelAndView test() {
		return new ModelAndView("redirect:/swagger-ui.html");
	}

}
