package many.tables.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import many.tables.model.SecurityQA;
import many.tables.model.loginmodel;

@EnableJpaRepositories
@Repository
public interface logininterface extends JpaRepository<loginmodel, SecurityQA> {

	
	loginmodel findBymail(String mail);
	
	   @Query("select u from loginmodel u WHERE u.mail = mail")
		loginmodel findBymailcontains(String mail2);

	loginmodel findBypassword(String userMail);
	   
	   
		
		
	
}
