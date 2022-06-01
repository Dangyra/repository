package lt.ku.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,length = 64)
	private String name;
	
	@Column(nullable = false,length = 64)
	private String author;
	
	@Column(nullable = false,length = 64)
	private String genre;
	
	@Column(nullable = false,length = 64)
	private String publication_details;
	
	@Column
	private String fileName;

	@Column
	private Integer places;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublication_details() {
		return publication_details;
	}

	public void setPublication_details(String publication_details) {
		this.publication_details = publication_details;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public Book() {

	}

	public Book(String name, String author, String genre, String publication_details, String fileName, Integer places) {

		this.name = name;
		this.publication_details = publication_details;
		this.author = author;
		this.genre = genre;
		this.fileName = fileName;
		this.places = places;
	}

}
