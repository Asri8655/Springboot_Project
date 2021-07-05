package many.tables.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "SecurityQuestion")
@JsonIgnoreProperties(ignoreUnknown = true) 
@JsonSerialize
public class SecurityQuestions {

//	@ManyToOne(fetch = FetchType.LAZY, optional=false)
//	 @JoinColumn(name = "loginId", nullable = false)
//	 @JsonIgnore
//	 public loginmodel loginModel;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QNo",length = 10)
	private Integer questionNo;
	
	@Column(name = "Question",length = 100	)
	private String question;

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


//	@OneToMany(mappedBy = "SecurityQuestion", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Set<SecurityQA> securityQA;

//	public Set<SecurityQA> getSecurityQA() {
//		return securityQA;
//	}
//
//	public void setSecurityQA(Set<SecurityQA> securityQA) {
//		this.securityQA = securityQA;
//	}	
////	
//	public loginmodel getLoginModel() {
//		return loginModel;
//	}
//
//	public void setLoginModel(loginmodel loginModel) {
//		this.loginModel = loginModel;
//	}

}
