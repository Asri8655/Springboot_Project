package many.tables.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable 	
public class SecurityQAId implements Serializable {

	
	
	private Integer loginId;
	private Integer questionId;
	
	public SecurityQAId() {}
	
	public SecurityQAId(Integer loginId, Integer questionId) {
		super();
		this.loginId = loginId;
		this.questionId = questionId;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityQAId that = (SecurityQAId) o;
        return loginId.equals(that.loginId) &&
        		questionId.equals(that.questionId);
    }
	 @Override
	    public int hashCode() {
	        return Objects.hash(loginId, questionId);
	    }
}
