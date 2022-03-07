package com.AW.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.AW.entity.Entry;
import com.AW.entity.UserDtls;
import com.AW.repository.EntryRepository;
import com.AW.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntryRepository notesRepository;

	@ModelAttribute
	public void addCommnData(Principal p, Model m) {
		String email = p.getName();
		UserDtls user = userRepository.findByEmail(email);
		m.addAttribute("user", user);
	}

	@GetMapping("/addNotes")
	public String home() {
		return "user/add_notes";
	}

	@GetMapping("/viewNotes/{page}")
	public String viewNotes(@PathVariable int page, Model m, Principal p) {

		String email = p.getName();
		UserDtls user = userRepository.findByEmail(email);

		Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
		Page<Entry> notes = notesRepository.findyNotesByUser(user.getId(), pageable);
		

		m.addAttribute("pageNo", page);
		m.addAttribute("totalPage", notes.getTotalPages());
		m.addAttribute("Entry", notes);
		m.addAttribute("totalElement", notes.getTotalElements());

		return "user/view_notes";
	}
	

	
	
	
	
	
	@GetMapping("/viewall/{page}")
	public String viewall(@PathVariable int page, Model m, Principal p) {

		String email = p.getName();
		UserDtls user = userRepository.findByEmail(email);

		Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
		Page<Entry> notes = notesRepository.findAll(pageable);
		
		m.addAttribute("pageNo", page);
		m.addAttribute("totalPage", notes.getTotalPages());
		m.addAttribute("Entry", notes);
		m.addAttribute("totalElement", notes.getTotalElements());

		return "user/view_all";
	}

	
	
	
	
	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model m) {

		Optional<Entry> n = notesRepository.findById(id);
		if (n != null) {
			Entry notes = n.get();
			m.addAttribute("notes", notes);
		}

		return "user/edit_notes";
	}

	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Entry notes, HttpSession session, Principal p) {
		String email = p.getName();
		UserDtls user = userRepository.findByEmail(email);

		notes.setUserDtls(user);

		Entry updateNotes = notesRepository.save(notes);

		if (updateNotes != null) {
			session.setAttribute("msg", "Entry Update Sucessfully");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}

		System.out.println(notes);

		return "redirect:/user/viewNotes/0";
	}

	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id,HttpSession session) {
		
		Optional<Entry> notes=notesRepository.findById(id);
		if(notes!=null)
		{
			notesRepository.delete(notes.get());
			session.setAttribute("msg", "Entry Delete Successfully");
		}
		
		return "redirect:/user/viewNotes/0";
	}

	@GetMapping("/viewProfile")
	public String viewProfile() {
		return "user/view_profile";
	}

	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Entry notes, HttpSession session, Principal p) {
		String email = p.getName();
		UserDtls u = userRepository.findByEmail(email);
		notes.setUserDtls(u);

		Entry n = notesRepository.save(notes);

		if (n != null) {
			session.setAttribute("msg", "Entry Added Sucessfully");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}

		return "redirect:/user/addNotes";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute UserDtls user,HttpSession session,Model m)
	{
		Optional<UserDtls> Olduser=userRepository.findById(user.getId());
		
		if(Olduser!=null)
		{
			user.setPassword(Olduser.get().getPassword());
			user.setRole(Olduser.get().getRole());
			user.setEmail(Olduser.get().getEmail());
			
			user.setPhonenumber(Olduser.get().getPhonenumber());
	
			
			
			UserDtls updateUser=userRepository.save(user);
			if(updateUser!=null)
			{
				m.addAttribute("user",updateUser);
				session.setAttribute("msg", "Profile Update Sucessfully..");
			}
			
		}
		
		
		return "redirect:/user/viewProfile";
	}
	
	/*@PostMapping("/updatepassword")
	public String updatepassword(@ModelAttribute UserDtls user, Model m, HttpSession session) {
		
		UserDtls Olduser=userRepository.findByEmail(user.getEmail());
		
		
		if(Olduser!=null) {
			user.setPassword(Olduser.getPassword());
			session.setAttribute("msg", "Updated Sucessfully");
		}
		else {
			session.setAttribute("msg", "Something wrong on server");
		}
		
		UserDtls updateUser=userRepository.save(user);
		if(updateUser!=null)
		{
			m.addAttribute("user",updateUser);
			session.setAttribute("msg", "Profile Update Sucessfully..");
		}

		
		return "redirect:/user/forgotpass";
	}*/
	

}