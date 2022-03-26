package codesquad.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import codesquad.domain.user.User;
import codesquad.domain.user.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public String getForm() {
		return "user/form";
	}

	@PostMapping("/users")
	public String join(User user) {
		System.out.println("user : " + user);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/users")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}

	@GetMapping("/users/{id}")
	public ModelAndView show(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("user/profile");
		mav.addObject("user", userRepository.findById(id).get());
		return mav;
	}

	@GetMapping("/users/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		Optional<User> savedUser = userRepository.findById(id);
		if(savedUser.get().isSameId(id)) {
			model.addAttribute("user", savedUser.get());
		}
		return "user/updateForm";
	}

	@PostMapping("/users/{id}/update")
	public String update(@PathVariable Long id, User updatedUser) {
		Optional<User> user = userRepository.findById(id);
		if(user.get().isSamePassword(updatedUser.getPassword())) {
			user.get().update(updatedUser);
			userRepository.save(user.get());
		}
		return "redirect:/users";
	}
}
