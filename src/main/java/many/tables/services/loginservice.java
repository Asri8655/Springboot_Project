package many.tables.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import many.tables.model.loginmodel;
import many.tables.repository.logininterface;

@Service
public class loginservice {
	@Autowired
    logininterface logininterface ;
	  
//		public Object createLogin(loginmodel login) {
//			return logininterface.save(login);
//		}
//		public Object saveOrUpdatePassword(loginmodel login) {
//			return logininterface.save(login);
//		}
	

}