package pkgLibrary;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;

	@XmlElement(name = "book")
	ArrayList<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	public void AddBook(Catalog cat, Book b) throws BookException {
		try {
			for (Book book : cat.getBooks()) {
				if (book.getId() == b.getId()) {
					throw new BookException(b.getId());
				}
			}
		}
			
			catch (BookException bx) {
				throw bx;
			} catch (Exception ex) {
				throw ex;
			}
			cat.getBooks().add(b);		

	}

	
//getbook passes in a catalog and a book id.  getbook will belong in book and catalog.
	
	public Book GetBook(String id) throws BookException {
		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		ArrayList<Book> bookList = cat.getBooks();

		for (Book b : bookList) {
			if (b.getId() == id);
			{
				return b;
			}
		}
		throw new BookException(id);
	}

}
