package com.smartera3s.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class LinkedInController {

	private LinkedIn linkedIn;
	private ConnectionRepository connectionRepository;

	@Autowired
	public LinkedInController(LinkedIn linkedIn, ConnectionRepository connectionRepository) {
		this.linkedIn = linkedIn;
		this.connectionRepository = connectionRepository;
	}

	@GetMapping
	public String getBasicProfile(Model model) {
		if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
			return "redirect:/connect/linkedin";
		}

        model.addAttribute("linkedInProfile", linkedIn.profileOperations().getUserProfileFull());

		return "profile/linkedin";
	}
}
