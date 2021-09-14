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

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.service.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	//@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newAuthor(Model model) {
		model.addAttribute("author", new Author());
		return "author/author";
	}
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveAuthor(@Valid @ModelAttribute(value = "author") Author author, BindingResult result,
			Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "author/author";
		} else {
			authorService.create(author);
			model.addAttribute("mensaje", "Se realizó bien!!");
			status.setComplete();
			return "redirect:/authors/list";
		}
	}
	//@Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String listAuthor(Model model) {
		try {
			model.addAttribute("listaAutores", authorService.getAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "author/listAuthor";
	}
	//@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteAuthor(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				authorService.deleteById(id);
				model.put("mensaje", "Se eliminó correctamente!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/authors/list";
	}
	//@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String viewAuthor(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Author> author = authorService.findById(id);
			if(!author.isPresent()) {
				model.addAttribute("mensaje","Author no existe");
				return "redirect:/authors/list";
			}
			else {
				model.addAttribute("author",author.get());
				return "author/updateAuthor";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "author/updateAuthor";
	}
}
