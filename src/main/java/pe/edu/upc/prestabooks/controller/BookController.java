package pe.edu.upc.prestabooks.controller;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.service.AuthorService;
import pe.edu.upc.prestabooks.service.BookService;
import pe.edu.upc.prestabooks.service.DetailAuthorBookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private DetailAuthorBookService detailAuthorBookService;

	// @Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		try {
			model.addAttribute("listaAutores", authorService.getAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "book/book";
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes redirectAttributes) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaAutores", authorService.getAll());
			return "book/book";
		} else {

			List<Author> authors = book.getAuthors();
			Book newBook = bookService.create(book);
			detailAuthorBookService.addAuthorsWithBook(newBook, authors);

			redirectAttributes.addFlashAttribute("mensaje", "Se registró el libro satisfactoriamente.");

			status.setComplete();
			return "redirect:/books/list";
		}
	}

	@PostMapping("/update")
	public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes redirectAttributes) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaAutores", authorService.getAll());
			return "book/updateBook";
		} else {

			List<Author> authors = book.getAuthors();
			Book newBook = bookService.create(book);
			detailAuthorBookService.updateAuthorsWithBook(newBook, authors);

			redirectAttributes.addFlashAttribute("mensaje", "Se modificó el libro satisfactoriamente.");

			status.setComplete();
			return "redirect:/books/list";
		}
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String listBook(@ModelAttribute("bookSearch") Book bookSearch, Model model) {
		try {
			if (bookSearch.getTitle() != null) {
				model.addAttribute("listaLibros", bookService.findByTitleOrAuthorName(bookSearch.getTitle()));
			}
			else {
				model.addAttribute("listaLibros", bookService.getAll());
				model.addAttribute("bookSearch", new Book());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "book/listBook";
	}

	// @Secured("ROLE_ADMIN")
	@RequestMapping("/delete")
	public String deleteBook(Map<String, Object> model, @RequestParam(value = "id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null && id > 0) {
				bookService.deleteById(id);
				redirectAttributes.addFlashAttribute("mensaje", "Se eliminó el libro correctamente.");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
		}
		return "redirect:/books/list";
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String viewBook(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Optional<Book> book = bookService.findById(id);
			model.addAttribute("listaAutores", authorService.getAll());
			if (!book.isPresent()) {
				redirectAttributes.addFlashAttribute("mensaje", "El libro no existe.");
				return "redirect:/books/list";
			} else {
				model.addAttribute("book", book.get());
				return "book/updateBook";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "book/updateBook";
	}
}
