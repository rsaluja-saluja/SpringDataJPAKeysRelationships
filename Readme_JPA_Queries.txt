JPA Queries

Derived:
	//Below 3 gives same results
	List<User> findByName(String name);
	List<User> findByNameIs(String name);
	List<User> findByNameEquals(String name);
	
	List<User> findByNameIsNot(String name);
	List<User> findByNameIsNull();
	List<User> findByNameIsNotNull();
	List<User> findByNameStartingWith(String prefix);
	List<User> findByNameEndingWith(String suffix);
	List<User> findByNameContaining(String infix);
	List<User> findByNameLike(String likePattern);
	List<User> findByAgeLessThan(Integer age);
	
	//COmparison conditions
	List<User> findByAgeLessThanEqual(Integer age);
	List<User> findByAgeGreaterThan(Integer age);
	List<User> findByAgeGreaterThanEqual(Integer age);
	List<User> findByAgeBetween(Integer startAge, Integer endAge);
	List<User> findByAgeIn(Collection<Integer> ages);
	List<User> findByBirthDateAfter(ZonedDateTime birthDate);
	List<User> findByBirthDateBefore(ZonedDateTime birthDate);

	//Multiple COnditions
	List<User> findByNameOrBirthDate(String name, ZonedDateTime birthDate);
	List<User> findByNameOrBirthDateAndActive(String name, ZonedDateTime birthDate, Boolean active);
	
	//Sorting teh results
	List<User> findByNameOrderByName(String name);
	List<User> findByNameOrderByNameAsc(String name);
	List<User> findByNameOrderByNameDesc(String name);
	
	List<User> findTop3ByAge()

Custom Queries: 
		Use JPQL(Java Persistence Query Language) that is SQL like. SQL syntax query can also be specified
	
	// Using JPQL
	@Query("SELECT b FROM Book b WHERE b.available = true")
	List<Book> findAvailabeBooks();
	
	//Using short hand query
	@Query("FROM Book b WHERE b.available = true")
	List<Book> findAvailabeBooks();
	
	// Using SQL
	@Query(value="SELECT * FROM Book b WHERE b.available = 1", nativeQuery = true)
	List<Book> findAvailabeBooks();
	
	//Parameterized query -- Query would take precedence on derived query method findByAuthor
	@Query("SELECT b FROM Book b WHERE b.author = ?1")
	List<Book> findByAuthor(String author);
	
	//Use positional arguments
	@Query("SELECT b FROM Book b WHERE b.author = ?1 and b.available = ?2")
	List<Book> findByAuthorAndAvailability(String author, boolean available);
	
	// Use named parameters 
	@Query("SELECT b FROM Book b WHERE b.author = :author")
	List<Book> findByAuthor(@Param("author") String author);
	
	@Query("SELECT b FROM Book b WHERE b.author = :author and b.available = :avail ORDER BY title")
	List<Book> findByAuthorAndAvailability(@Param("author") String author, @Param("avail" boolean available);
	
	//******* Update/Delete Query ******
	
	//Returns no of updated records
	//@Transactional is required to be added in repository interface start or at specific function so that functions would be run in a transaction
	@Modifying
	@Query("UPDATE Bood b SET b.title = ?1 WHERE b.bookId = ?2)
	int updateTitleById(String title, long bookId)
	
	//@Transactional is required for delete also
	@Modifying
	@Query("DELETE FROM Book b where b.title = ?1)	
	int deleteByTitle(String title)
	
Define Named Queries in properties files:
	create resources/META-INF
	create file jpa-named-queries.properties // name should be same as JPA search for this file only
	
	File content: 
		// Book is Entity on which to run these queries, findAllNamedQuery is the name of query
		# find all books order by descending order of rating
		Book.findAllNamedQuery=SELECT b FROM Book b ORDER BY b.rating DESC
		
		# find books by title
		Book.findByTitleNamedQuery=SELECT b from Book b where b.title = ?1
		
		#find a book by author
		Book.findByAuthorNativeNamedQuery=SELECT * FROM Book b WHERE b.author = :author
	
	Refer these queries in repository file:
		//method names shoudl match query name defined in named queries properties file
		List<Book> findAllNamedQuery();
		
		List<Book> findByTitleNamedQuery(String title);
		
		@Query(nativeQuery=true)
		List<Book> findByAuthorNativeNamedQuery(@Param("author") String suthor);
		
Defind named-queries in XML file
	create resources/META-INF/orm.xml file and add named queries in this
	
	<?xml version="1.0" encoding="UTF-8"?>
	<entity-mappings version="2.0" xmlns="http://java.sun.com/xml/ns/persistence/orm"
					 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					 xsi:schemaLocation="http://java.sun.com/xml/ns/persistence/orm
			http://java.sun.com/xml/ns/persistence/orm_2_0.xsd ">

		<!--find all books order by price descending-->
		<named-query name="Book.findAllXML">
			<query>SELECT b FROM Book b ORDER BY b.rating DESC</query>
		</named-query>

		<!--find books by minimum rating-->
		<named-query name="Book.findByMinRatingXML">
			<query>SELECT b FROM Book b WHERE b.rating >= ?1 ORDER BY b.rating</query>
		</named-query>

		<!--native SQL query to find a book by author-->
		<named-native-query name="Book.findByAuthorNativeXML"
							result-class="com.skillsoft.springdatajpa.model.Book">
			<query>SELECT * FROM book b WHERE b.author = :author</query>
		</named-native-query>

	</entity-mappings>
	
Define Named Queries in Entity Files
	@Entity
	@NamedQuery(name = "Book.findAvailableBooksJPQL"
				query = "SELECT b FROM Book b WHERE b.available=1")
	@NamedQuery(name = "Book.findByRatingsJPQL"
				query = "SELECT b FROM Book b WHERE b.rating >= ?1")
	
	@NamedNativeQuery(name = "Book.findByTitleNative"
				query = "SELECT * FROM Book b WHERE b.title = :title",
				resultClass = Book.class)
	