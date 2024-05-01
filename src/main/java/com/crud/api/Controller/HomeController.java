package com.crud.api.Controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.api.entity.Employee;
import com.crud.api.service.EmpService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

	org.slf4j.Logger logger =LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private EmpService empService;

	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		logger.info("Strating get index method with Info log level");
		m.addAttribute("empList", list);
		return "index";
	}

	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {
		logger.debug("Strating get loadEmpSave method with Info log level");
		return "emp_save";
	}

	@GetMapping("/EditEmp/{id}")
	public String EditEmp(@PathVariable int id, Model m) {
		// System.out.println(id);
		logger.debug("Strating get EditEmp method with Info log level");
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit_emp";
	}

	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);

		Employee newEmp = empService.saveEmp(emp);
		logger.trace("Strating get saveEmp method with Info log level");

		if (newEmp != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Register sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/loadEmpSave";
	}

	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);

		Employee updateEmp = empService.saveEmp(emp);
		logger.warn("Strating get updateEmp method with Info log level");

		if (updateEmp != null) {
			// System.out.println("save success");
			session.setAttribute("msg", "Update sucessfully");
		} else {
			// System.out.println("something wrong on server");
			session.setAttribute("msg", "something wrong on server");
		}

		return "redirect:/";
	}

	@GetMapping("/deleteEmp/{id}")
	public String loadEmpSave(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		logger.error("Strating get loadEmpSave method with Info log level");

		if (f) {
			session.setAttribute("msg", "Delete sucessfully");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}

}