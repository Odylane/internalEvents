package fr.formation.internalevents.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_employee")
	private Long id;
	
	@Column(nullable=false, length=11, unique=true)
	private String username;
	
	@Column(nullable=false, length=100)
	private String firstname;
	
	@Column(nullable=false, length=100)
	private String lastname;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false, length=100)
	private String password;
	
	//fetch = FetchType.EAGER, fetch the role associated to the user
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employees_roles",
	joinColumns = @JoinColumn(name= "id_employee", referencedColumnName = "id_employee"),
	inverseJoinColumns = @JoinColumn(name= "id_role", referencedColumnName = "id_role"))
	private Set<Role> roles;
	
	@Convert(converter = BooleanConverter.class)
	@Column(length=1, nullable=false)
	private boolean enabled=true;
	
	@Convert(converter = BooleanConverter.class)
	@Column(length = 1, nullable = false)
	private boolean accountNonExpired;

	@Convert(converter = BooleanConverter.class)
	@Column(length = 1, nullable = false)
	private boolean accountNonLocked;

	@Convert(converter = BooleanConverter.class)
	@Column(length = 1, nullable = false)
	private boolean credentialsNonExpired;

	public Employee() {
		//
	}
  
    /**
     * Creates a new user(employee)
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles    some roles
     * @param enabled  {@code true} if enabled; {@code false} otherwise
     */
	public Employee(String username, String password, Set<Role> roles, boolean enabled) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.enabled = enabled;		
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	 public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
    public String toString() {
	// password=[PROTECTED] for not displaying in logs
	return "{id=" + id + ", username=" + username
		+ ", password=[PROTECTED], roles=" + roles + ", enabled="
		+ enabled + ", accountNonExpired=" + accountNonExpired
		+ ", accountNonLocked=" + accountNonLocked
		+ ", credentialsNonExpired=" + credentialsNonExpired
		+ ", firstname=" + firstname + ", lastname=" + lastname
		+ ", email=" + email +", role=" + roles + "}";
    }

}
