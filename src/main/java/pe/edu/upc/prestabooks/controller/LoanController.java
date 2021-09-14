package pe.edu.upc.prestabooks.controller;

import java.util.Map;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.prestabooks.entity.Loan;
import pe.edu.upc.prestabooks.service.BookService;
import pe.edu.upc.prestabooks.service.EmployeeService;
import pe.edu.upc.prestabooks.service.LoanService;
import pe.edu.upc.prestabooks.service.ReaderService;

@Controller
@RequestMapping("/loans")
public class LoanController {
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private ReaderService readerService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private EmployeeService employeeService;
	
	//@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newLoan(Model model) {
		model.addAttribute("loan", new Loan());
		try {
			model.addAttribute("listaLectores", readerService.getAll());
			model.addAttribute("listaLibros", bookService.getAll());
			model.addAttribute("listaEmpleados", employeeService.getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loan/loan";
	}
	//@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveLoan(@Valid @ModelAttribute(value = "loan") Loan loan, BindingResult result,
			Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaLectores", readerService.getAll());
			model.addAttribute("listaLibros", bookService.getAll());
			model.addAttribute("listaEmpleados", employeeService.getAll());
			return "loan/loan";
		} else {
			loanService.create(loan);
			model.addAttribute("mensaje", "Se realizó bien!!");
			status.setComplete();
			return "redirect:/loans/list";
		}
	}
	//@Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String listLoan(Model model) {
		try {
			model.addAttribute("listLoan", loanService.getAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "loan/listLoan";
	}
	//@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteLoan(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				loanService.deleteById(id);
				model.put("mensaje", "Se eliminó correctamente!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/loans/list";

	}
	//@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String viewLoan(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Loan> loan = loanService.findById(id);
			model.addAttribute("listaLectores", readerService.getAll());
			model.addAttribute("listaLibros", bookService.getAll());
			model.addAttribute("listaEmpleados", employeeService.getAll());
			if(!loan.isPresent()) {
				model.addAttribute("mensaje","Reserva no existe");
				return "redirect:/loans/list";
			}
			else {
				model.addAttribute("loan",loan.get());
				return "loan/updateLoan";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "loan/updateLoan";
	}
	
}
