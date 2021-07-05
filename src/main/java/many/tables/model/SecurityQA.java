package many.tables.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "QuestionAnswers")
@JsonIgnoreProperties(ignoreUnknown = true) 
//@JsonSerialize
@Embeddable
public class SecurityQA implements Serializable{

	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private SecurityQAId securityQAId;
	private String answer;
	public SecurityQAId getSecurityQAId() {
		return securityQAId;
	}
	public void setSecurityQAId(SecurityQAId securityQAId) {
		this.securityQAId = securityQAId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public SecurityQA(SecurityQAId securityQAId, String answer) {
		super();
		this.securityQAId = securityQAId;
		this.answer = answer;
	}
	public SecurityQA() {
	
	}

	
	




}
