Composite Key
Way 1:
	create a class defining key members as attributes
	Add @Embeddable at class level, This indicates this would be embedded in other entity
	implements Serializable
	Define hascode and equals method
	Ex - EmployeeEntryId
	Add this is attribute in EmployeeEntity class with @EmbeddedId annotation
Way 2:
	Not add @Embeddable at EmployeeEntryId. other would remain same
	
One-to-one relationship: - In dbase implemented as Foreign key
	EmployeeContactInfo -> Employee
	create table employee (id integer not null, first_name varchar(255), job varchar(255), 
				last_name varchar(255), salary double, primary key (id))
	create table employee_contact_info (address varchar(255), phone_num varchar(255), 
				employee_id integer not null, primary key (employee_id))
	alter table employee_contact_info add constraint FKe6sk8libwjwy3orf1hampnbgx 
				foreign key (employee_id) references employee
	
	Employee [id=6, firstName=Peter, lastName=Holand, job=Manager, salary=12000.0, contactInfo=EmployeeContactInfo [id=6, address=Address 1, phoneNum=Number 1]]
	EmployeeContactInfo [id=6, address=Address 1, phoneNum=Number 1]
	
One-to-Many relationship:
	Employee -> Department
	Department can have many employees - OneToMany
	Employee can belong to only one department - ManyToOne
	Many side - owning type
	One side - referencing type
	Many side, owning side would have foreign key i.e Employee
	
	create table department (department_id integer generated by default as identity, 
						location varchar(255), name varchar(255), primary key (department_id))
	create table employee (id integer not null, first_name varchar(255), job varchar(255), 
					last_name varchar(255), salary double, department_id integer not null, primary key (id))
	alter table employee add constraint FKbejtwvg9bxus2mffsm3swj3u9 foreign key (department_id) references department
	
	Employee [id=1, firstName=Peter, lastName=Holand, job=Manager, salary=12000.0, contactInfo=EmployeeContactInfo [id=1, address=Address 1, phoneNum=Number 1]]
	Department [id=1, name=Accounts, location=Floor 1]
	
Many-to-Many Relationship:
	Employee -> Project
	mappedBy should be specified on any one side
	separate relationship table would be created in DBase
	
	create table employee (id integer not null, first_name varchar(255), job varchar(255), 
						last_name varchar(255), salary double, department_id integer not null, primary key (id))
	create table project (project_id integer generated by default as identity, project_deadline date, 
						project_name varchar(255), primary key (project_id))
	create table employees_projects (employee_id integer not null, projectid integer not null, 
						primary key (employee_id, projectid))
	alter table employees_projects add constraint FKlp2melciuhn0m9e6rgbyor1me foreign key (projectid) references project
	alter table employees_projects add constraint FKicms2qddrg3fa7tsd8f40da2i foreign key (employee_id) references employee
	
Performance related - REST API/General application
	https://thorben-janssen.com/collections-hibernate-jpa/
	https://thorben-janssen.com/avoid-cascadetype-delete-many-assocations/
	https://thorben-janssen.com/ultimate-guide-association-mappings-jpa-hibernate/
	https://thorben-janssen.com/best-practices-many-one-one-many-associations-mappings/
	https://thorben-janssen.com/association-mappings-bag-list-set/
	
Many-to-Many - Use Set instead of list as Hibernate handles the removal of associated entities very inefficiently.
	Book with 2 authors - need to remove one author 
	
	Hibernate removes all records from the association table before it inserts a new record for the remaining association.
	This approach is obviously very inefficient. Depending on the number of associated entities, the additional INSERT statements can create performance problems.
	
	List(if order is not specified hiberlate treat it as bag data type(unordered list))

FETCH TYPE: - FetchType defines when Hibernate initializes an association. 
	All to-one relationships use FetchType.EAGER and all to-many relationships FetchType.LAZY.
	FetchType.EAGER, it initializes the association when you load the entity. You should avoid this FetchType because 
	it fetches all association elements even if you don???t use them.
	
	FetchType.LAZY is the default for all to-many association, and it provides much better performance. 
	Hibernate then only fetches the association when you use it in your business code.
	
CascadeType: Cascading applies the lifecycle state change of a parent entity to all its child entities. 
	You can activate it by referencing the type of operation you want to cascade in the cascade attribute of the one-to-many or many-to-many annotation.
	This works well for all parent-child associations in which the child depends on its parent.
	
	CascadeType.REMOVE on both sides of many-to-many:
		It can bounce the cascade operation back and forth between the 2 tables until all records are removed.
	CascadeType.REMOVE on One sides of many-to-many:
		It might delete more data than you expected
		If you now remove an Author entity, your persistence provider will cascade the operation to all associated Book entities. 
		As a result, all of them will get removed. Unfortunately, that includes all books that have been written by more than one author.
	CascadeType.REMOVE, ALL should not be used for to-many associations.
	
@ElementCollection:
		An @ElementCollection enables you to map a Collection of values that are not an entity itself. 
		In the database, Hibernate maps the @ElementCollection to a separate table. Each value of the collection gets stored as a separate record.
		
		Disadvantages:
			The elements of the collection don???t have their own identity and lifecycle. They are a part of the surrounding entity. 
			This often becomes a performance issue if you need to change the elements in the collection. 
			Because they don???t have their own identity, all elements of an @ElementCollection are always read, removed, and written,
			even if you only add, change, or remove one of them. This makes write operations on an @ElementCollection much more 
			expensive than the same operation on a mapped association.
			
			recommend modeling an additional entity and a one-to-many association instead of an @ElementCollection. 
			This enables you to use lazy loading and to update these values independently of each other. 
			Doing that requires only a minimum amount of code but provides much better performance.
			 
			Customer cust = custRepo.findById(1).get();
			cust.getPhoneNumbers().add(new PhoneNumber("Test","5634",false));
			custRepo.save(cust);
			
			It first deletes everything from customer phone number table and then add again everything with new entry also.
			

	
	
	
	
	
