Daily Code Buffer:
	https://www.youtube.com/watch?v=XszpXoII9Sg
Query Methods:
	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
spring.jpa.hibernate.ddl-auto=update
	Whatever changes we would do in entities, it will update the database accordingly.
	Like if we add 2 new attributes, 2 new columns would be created
	Not recommended for production
Student Entity
	@SequenceGenerator - generate sequence that can be used for primary key
	Unique constraint for email
	
@DataJpaTest - to test repository. It will clean the database after running test
@Builder - To apply Builder pattern - Student
JPQL queries - based on entity instead of database tables
Update/delete queries - must have @Modifying and @Transactional

Unidirectional One-to-one relationship: implemented as foreign key 
	Course -> CourseMaterial(having foreign key defined as OneToOne mapping)
	CourseMaterial must have course, it cann't exists on its own.
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", //creates foreign key of name course_id
			referencedColumnName = "courseId")
	private Course course;
	Cascading: 
		Wout cascade: CourseMaterial save fails if course is not added in database first.
		With cascade: saving courseMaterial would insert course record also.
	FetchType:
		FetchType.LAZY - fetching coursematerial will not fetch course and its tostring gives error.
			course can be excluded from toString
		FetchType.EAGER - fetching coursematerial will fetch course
	Fetching Course doesn't have CourseMaterial details as Course doesn't have any reference of curseMaterial
BiDirectional One-To-One mapping
	Also add below mapping in Course:
	@OneToOne(
			mappedBy = "course")
	private CourseMaterial courseMaterial;
	If courseMaterial has fetch type eager then fetching course/courseMaterial will give stackoverflow error as 
	it recursively try to fetch course and coursematerials.
	Change fetchtype to lazy in course material and exclude course from toString.
One to many relationship:
	Teacher - Course
	Course would have foreign key pointing to teacher id
	@OneToMany(
			cascade = CascadeType.ALL
			)
	@JoinColumn(
			name = "teacher_id",
			referencedColumnName = "teacherId")
	List<Course> courses;

Optionality of Relationship:
	Saving coursematerial doesn't require course
	By default its true.
	
Many to one relationship:
	Course - Teacher
	Define Many-to-one whenever possible instead of one-to-many
	Commentout One-to-many in teacher class
	define Many to one in Course class

Paging and Sorting
	Using JPARepository provided findAll methods
	Also custom query methods can be defined
	Example -  CustomerRepo

Many to Many Relationship
	Student - Course
	
	 
