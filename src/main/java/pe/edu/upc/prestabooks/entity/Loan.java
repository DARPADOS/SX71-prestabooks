package pe.edu.upc.prestabooks.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Loan")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Ingrese fecha de reserva")
	//@Future(message = "La FECHA DEL PEDIDO debe ser futura")
	@Temporal(TemporalType.DATE)
	@Column(name = "loan_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date loan_date;
	
	@NotNull(message = "Ingrese fecha de entrega")
	//@Future(message = "La FECHA DEL PEDIDO debe ser futura")
	@Temporal(TemporalType.DATE)
	@Column(name = "return_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date return_date;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "reader_id", nullable = false)
	private Reader reader;
	
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(int id, @NotNull(message = "Ingrese fecha de reserva") Date loan_date,
			@NotNull(message = "Ingrese fecha de entrega") Date return_date, Employee employee, Reader reader,
			Book book) {
		super();
		this.id = id;
		this.loan_date = loan_date;
		this.return_date = return_date;
		this.employee = employee;
		this.reader = reader;
		this.book = book;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLoan_date() {
		return loan_date;
	}

	public void setLoan_date(Date loan_date) {
		this.loan_date = loan_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	}
	
}
