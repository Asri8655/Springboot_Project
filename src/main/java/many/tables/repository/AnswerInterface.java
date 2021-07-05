package many.tables.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import many.tables.model.SecurityQAId;
import many.tables.model.SecurityQA;
import many.tables.model.loginmodel;

public interface AnswerInterface extends JpaRepository<SecurityQA, SecurityQAId> {

	void save(JSONObject answerData);

//	 @Query("select u from SecurityQA u WHERE u.login_Id = login_Id")
	
	List<SecurityQA> findBySecurityQAIdLoginId(Integer loginid);

	
	List<SecurityQA> findByanswer(String qid);
	
//	@Query("select u from SecurityQA u WHERE u.login_Id = login_Id")
//	ArrayList<SecurityQA> findBylogin_Id(Integer loginid);

//	LoginId findByLogin_Id(int qid);

//	 @Query("select u from SecurityQA u WHERE u.login_Id = login_Id")
//		LoginId findById(Integer mail3);
		
//	  LoginId findById(Integer loginid);
	 
	 
}
