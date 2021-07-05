package many.tables.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import many.tables.model.SecurityQA;
import many.tables.model.SecurityQuestions;
import many.tables.model.loginmodel;
import many.tables.model.usermodel;
import many.tables.repository.AnswerInterface;
import many.tables.repository.SecurityQues;
import many.tables.repository.logininterface;
import many.tables.repository.userinterface;
import many.tables.services.loginservice;
import many.tables.services.userservice;


@RestController
@RequestMapping("users")
@CrossOrigin
public class usercontroller {
	
	@Autowired
	private userinterface userinterface;
	@Autowired
	private logininterface logininterface;
	@Autowired
	 private userservice userservice;
	@Autowired
	private loginservice loginservice;
	@Autowired
	private SecurityQues securityQues; 
    @Autowired
    private AnswerInterface Ans;
//	private SecurityQuestionAns Ans;
	
     // To create User Details,Login Credentials,Security Questions
	    @PostMapping(value="/createUser&Login")
	    
	    public usermodel createUser(@Valid @RequestBody HashMap<String, Object> user,loginmodel login) throws JSONException {
	    	
	        JSONObject userObj = new JSONObject(user);
	        JSONObject loginObj = new JSONObject(login);
	        JSONObject obj = userObj.getJSONObject("userData").getJSONObject("loginData");
	        String mail=obj.optString("mail");
	        loginmodel mailid=logininterface.findBymail(mail);
	        loginmodel loginData;
	        SecurityQA AnswerData;
	        usermodel userData;
	        if(mailid==null) {
	         userData = userservice.createUser(userObj.getJSONObject("userData"));
	        if(userObj.getJSONObject("userData").optJSONObject("loginData") != null) {
            loginData = userservice.createLogin(userObj.getJSONObject("userData").optJSONObject("loginData"), userData);
//	         userservice.CreateAnswers(userObj.getJSONObject("userData").getJSONObject("loginData").optJSONObject("AnswerData"), loginData);	
            AnswerData =  userservice.CreateAnswers(userObj.getJSONArray("AnswerData"),loginData);
            
	        }	      	       
	    }else {
	        userData = null;
	        
	    }
	        return userData;
	    }
	    
	    //Login User
	    @PostMapping(path="/UserLogin")
	    public String UserLogin(@Valid @RequestBody HashMap<String, Object> mail ) throws JSONException {
	    	String login = null;
	    	JSONObject loginObj = new JSONObject(mail);
	    	System.out.println(loginObj);
	    	String userMail=loginObj.optString("mail");
	    	System.out.println(userMail);
	    	String userPassword=loginObj.optString("password");
	    	System.out.println(userPassword);
	    	loginmodel mailid=logininterface.findBymail(userMail);
	    	loginmodel password=logininterface.findBypassword(userPassword);
	    	if(mailid !=null && password != null ) {
	    		login="Valid";
	    		System.out.print("If Working");
	    	}else {
	    		login="Not Valid";
	    		System.out.print("else Working");
	    	}
	    	return login;
	    }
	    
	    // Forgot Password
	    @PostMapping(path="/ForgotPassword")
	    @ResponseBody
	     public Object forgotPassword(@Valid @RequestBody HashMap<String, Object> mail ) throws JSONException, JsonProcessingException {
	      List<SecurityQuestions> questions=new ArrayList<>();
	      ObjectMapper mapper = new ObjectMapper();
	    	JSONObject userObj = new JSONObject(mail);
	    	String forgotmail=userObj.optString("mail");
	    	loginmodel mailid=logininterface.findBymail(forgotmail);
	        	if(mailid != null) {
	    		 loginmodel loginDetails= (loginmodel) logininterface.findBymailcontains(forgotmail);
	    		 Integer loginid=loginDetails.getLoginid();	
	    		if(loginid != 0) {
	    			List<SecurityQA> questionAnswers= Ans.findBySecurityQAIdLoginId(loginid);
	    			String json = mapper.writeValueAsString(questionAnswers);
                     System.out.println(json);
	    			questionAnswers.stream().forEach(questionAnswer->{
	    				questions.add(securityQues.findByquestionNo(questionAnswer.getSecurityQAId().getQuestionId()));
	    			});
	    		}
	    	} else {
	    		return "mail is not valid";
	    	}
	    	
	    	 return questions;
	    	
	    	
	    }

		//To Validate Answer
	    @PostMapping(path="/ValidateAnswer")
	    @ResponseBody
	    public boolean ValidateAnswer(@Valid @RequestBody HashMap<String, Object> answer ) throws JSONException {
	     	boolean Answer = false;
	    	JSONObject userObj = new JSONObject(answer);
	    	System.out.println(userObj);
	    	String answer1=userObj.optString("answer");
	    	String answer2=userObj.optString("answer2");
	    	int qid=userObj.optInt("question_Id");
	    	int qid2=userObj.optInt("question_Id2");
	   
	    	List<SecurityQA> ansvalidation=Ans.findByanswer(answer1);
	    	boolean ans = ansvalidation.isEmpty();
	    	List<SecurityQA> ansvalidation2=Ans.findByanswer(answer2);
	    	boolean ans2 = ansvalidation2.isEmpty();
	         if(ans != true && ans2 != true) {
	        	 Answer= true;
	         }
	    return Answer;
	    }
	    
	    //To Update Password
	    @PutMapping(path="/UpdatePassword")
	    public boolean updatePassword(@Valid @RequestBody HashMap<String, Object> password ) throws JSONException {
	     	boolean status = false;
	     	JSONObject userObj = new JSONObject(password);
	    	String passWord=userObj.optString("passWord");
	    	String mail=userObj.optString("mail");
	    	loginmodel login= logininterface.findBymail(mail);
	    	if(login !=null) {
	    	login.setPassword(passWord);
	    	logininterface.save(login);
	    	status=true;
	    	}
	     	return status;
	    }
	    
	 // To Get All Questions 
	 @GetMapping(path="/getAllQuestions")
		public List<SecurityQuestions> allQues() {
		  return securityQues.findAll();
		    }
	 
	 //To Create the Question
	 @PostMapping(value="/createQuestion")
		public SecurityQuestions CreateQuestions(@RequestBody SecurityQuestions SecurityQuestions) {
			return userservice.CreateQuestions(SecurityQuestions);
		}
	 
//	@GetMapping(value="/getAllUsers")
//	public List<usermodel> all() {
//	        return userinterface.findAll();
//	    }
// 
//	 @GetMapping(value="/getAllLogin")
//	public List<loginmodel> alll() {
//	        return logininterface.findAll();
//	    }
//	@PostMapping(value="/createUser")
//	public Object createUser(@RequestBody  usermodel usermodel) {
//		return userservice.createUser(usermodel);
//	}
//	@PostMapping(value="/createLogin")
//	public Object createLogin(@RequestBody  loginmodel loginmodel) {
//		return loginservice.createLogin(loginmodel);
//	}
//	 @PostMapping("/createUser&Login")
//	    public usermodel createUser(@Valid @RequestBody HashMap<String, Object> user) throws JSONException {
//	        JSONObject userObj = new JSONObject(user);
//	        usermodel userData = userservice.createUser(userObj.getJSONObject("userData"));	   
//	        if(userObj.getJSONObject("userData").optJSONObject("loginData") != null) {
//	        	userservice.createLogin(userObj.getJSONObject("userData").optJSONObject("loginData"), userData);
//	        }	 
//	        return userData;
//	        }
////	
//	
	 
	 
	
	 
	
//	 @GetMapping(path="/getAllAnswers")
//		public List<SecurityQA> allAns() {
//		 
//		        return Ans.findAll();
//		    
//		    }
//	 @PostMapping(value="/createAnswer")
//		public Object CreateAnswers(@RequestBody SecurityQA SecurityQA) {
//			return userservice.CreateAnswers(SecurityQA);
//		}

}
