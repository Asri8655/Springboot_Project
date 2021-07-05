package many.tables.services;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import ch.qos.logback.core.db.dialect.MsSQLDialect;
import many.tables.model.SecurityQAId;
import many.tables.model.SecurityQA;
import many.tables.model.SecurityQuestions;
import many.tables.model.loginmodel;
import many.tables.model.usermodel;
import many.tables.repository.AnswerInterface;
import many.tables.repository.SecurityQues;
import many.tables.repository.logininterface;
import many.tables.repository.userinterface;

@SpringBootApplication

public class userservice {
	
	@Autowired
	userinterface userinterface;
 @Autowired
 logininterface loginInterface;
 @Autowired
 SecurityQues SecureQues;
 @Autowired
AnswerInterface SecureAns;
//	public Object createUser(usermodel user) {
//		return userinterface.save(user);
//	}
//	
// 
      public usermodel createUser(JSONObject userData){

 	usermodel userModel = new usermodel();
     userModel.setFirstName(userData.optString("firstName"));
     userModel.setLastName(userData.optString("lastName"));
     return userinterface.save(userModel);
   }

	 public loginmodel createLogin(JSONObject loginData, usermodel userData){
		  
		 JSONObject obj = loginData;
		String mail=obj.optString("mail");
		loginmodel login = new loginmodel();
//		  loginmodel mailid=loginInterface.findBymail(mail);
//		 if(mailid==null) {
//	    	
	        login.setMail(loginData.optString("mail"));
	        login.setPassword(loginData.optString("password"));
	        login.setUserModel(userData);
//		 }else {
//			 System.out.println("Already Exist");
//		 }
		 return loginInterface.save(login);
	    }
 
	   public SecurityQuestions CreateQuestions(SecurityQuestions securityQuestions) {
		   SecurityQuestions SQ = new SecurityQuestions();
		  SQ.setQuestion(securityQuestions.getQuestion());
	    	 return SecureQues.save(SQ);
	    }

public SecurityQA CreateAnswers(JSONArray AnswerData,loginmodel loginData) throws JSONException {
	 JSONObject obj = new JSONObject(loginData);
	 SecurityQA res=new SecurityQA();
	 int loginid=obj.optInt("loginid");
	 for (int i = 0; i < AnswerData.length(); i++) {
     JSONObject addressJson =AnswerData.getJSONObject(i);
     int questionId=addressJson.getInt("question_Id");
	 SecurityQA SQ = new SecurityQA(new SecurityQAId(loginid,questionId),addressJson.optString("answer"));
      res = SecureAns.save(SQ);
	 }
	return (res);
}
//    public SecurityQA getQuestionid(String mail) {
//    	return SecureAns.(id);
//    }

//public loginmodel updateLogin(JSONObject loginData, usermodel userData){
//	  
//	 JSONObject obj = loginData;
//	String mail=obj.optString("mail");
//	loginmodel login = new loginmodel();
////	  loginmodel mailid=loginInterface.findBymail(mail);
////	 if(mailid==null) {
////   	
//       login.setMail(loginData.optString("mail"));
//       login.setPassword(loginData.optString("password"));
//       login.setUserModel(userData);
////	 }else {
////		 System.out.println("Already Exist");
////	 }
//	 return loginInterface.save(login);
//   }
}


		     