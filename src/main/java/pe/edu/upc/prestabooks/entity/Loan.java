package pe.edu.upc.prestabooks.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Loan")
public class Loan {
	
	// Campos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@NotNull(message = "Ingrese fecha de reserva")
	//@Future(message = "La FECHA DEL PEDIDO debe ser futura")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loan_date")
	@DateTimeFormat(pattern = "EEEE, dd/MM/yyyy HH:mm")
	private Date loanDate;
	
	//@NotNull(message = "Ingrese fecha de entrega")
	//@Future(message = "La FECHA DEL PEDIDO debe ser futura")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "return_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date returnDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "return_limit_date")
	@DateTimeFormat(pattern = "EEEE, dd/MM/yyyy HH:mm")
	private Date returnLimitDate;

	@Column(name = "returned")
	private Boolean returned;

	@Column(name = "delay")
	private Boolean delay;

	// Relaciones

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "reader_id", nullable = false)
	private Reader reader;
	
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	// Transitorios
	@Min(value = 1, message = "No hay stock del Libro")
	private Integer bookStock;


	public Loan() {
		super();
		delay = false;
		returned = false;
		Calendar today = Calendar.getInstance();
		loanDate = today.getTime();
		today.add(Calendar.DATE, 3);
		today.set(Calendar.HOUR_OF_DAY, 19);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		returnLimitDate = today.getTime();
	}

	public Loan(int id, Date loanDate, Date returnDate, Date returnLimitDate, Boolean returned, Boolean delay, User user,
			Reader reader, Book book) {
		this.id = id;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.returnLimitDate = returnLimitDate;
		this.returned = returned;
		this.delay = delay;
		this.user = user;
		this.reader = reader;
		this.book = book;
	}

	@PostLoad
	private void settingDelay(){
		if(!delay && !returned){
			this.delay = returnLimitDate.before(Calendar.getInstance().getTime());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Date getReturnLimitDate() {
		return returnLimitDate;
	}

	public void setReturnLimitDate(Date returnLimitDate) {
		this.returnLimitDate = returnLimitDate;
	}

	public Boolean getReturned() {
		return returned;
	}

	public void setReturned(Boolean returned) {
		this.returned = returned;
	}

	public Boolean getDelay() {
		return delay;
	}

	public void setDelay(Boolean delay) {
		this.delay = delay;
	}

	public int getId() {
		return id;
	}

	public Loan setId(int id) {
		this.id = id;
		return this;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
		this.bookStock = book.getStock();
	}
	
}
