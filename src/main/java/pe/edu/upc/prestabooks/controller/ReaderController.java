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

import pe.edu.upc.prestabooks.entity.Reader;
import pe.edu.upc.prestabooks.service.ReaderService;

@Controller
@RequestMapping("/readers")
public class ReaderController {

	@Autowired
	private ReaderService readerService;

	// @Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newReader(Model model) {
		model.addAttribute("reader", new Reader());
		return "reader/reader";
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveReader(@Valid @ModelAttribute(value = "reader") Reader reader, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "reader/reader";
		} else {
			readerService.create(reader);
			model.addAttribute("mensaje", "Se realizó bien!");
			status.setComplete();
			return "redirect:/readers/list";
		}
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String listReader(@ModelAttribute("readerSearch") Reader readerSearch, Model model) {
		try {
			if (readerSearch.getFirstName() != null) {
				model.addAttribute("listaLectores", readerService.findByFirstNameOrLastName(readerSearch.getFirstName()));
			} else {
				model.addAttribute("listaLectores", readerService.getAll());
				model.addAttribute("readerSearch", new Reader());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "reader/listReader";
	}

	// @Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteReadaer(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				readerService.deleteById(id);
				model.put("mensaje", "Se eliminó correctamente!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/readers/list";
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String viewReader(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Reader> reader = readerService.findById(id);
			if (!reader.isPresent()) {
				model.addAttribute("mensaje", "Reader no existe");
				return "redirect:/readers/list";
			} else {
				model.addAttribute("reader", reader.get());
				return "reader/updateReader";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "reader/updateReader";
	}
}
