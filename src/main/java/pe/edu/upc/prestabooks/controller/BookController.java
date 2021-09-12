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

import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.service.AuthorService;
import pe.edu.upc.prestabooks.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	//@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newBook(Model model){
		model.addAttribute("book", new Book());
		try {
			model.addAttribute("listaAutores",authorService.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "book/book";
	}
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveBook(@Valid @ModelAttribute(value = "book") Book book, BindingResult result,
			Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaAutores", authorService.getAll());
			return "book/book";
		} else {
			bookService.create(book);
			model.addAttribute("mensaje", "Se realizó bien!!");
			status.setComplete();
			return "redirect:/books/list";
		}
	}
	//@Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String listBook(Model model) {
		try {
			model.addAttribute("listaLibros", bookService.getAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "book/listBook";
	}
	//@Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteBook(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
				bookService.deleteById(id);
				model.put("mensaje", "Se eliminó correctamente!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/books/list";
	}
	//@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String viewBook(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Book> book = bookService.findById(id);
			model.addAttribute("listaAutores", authorService.getAll());
			if(!book.isPresent()) {
				model.addAttribute("mensaje","Libro no existe");
				return "redirect:/books/list";
			}
			else {
				model.addAttribute("book",book.get());
				return "book/updateBook";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "book/updateBook";
	}
}
