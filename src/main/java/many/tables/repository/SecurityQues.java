package many.tables.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import many.tables.model.SecurityQuestions;

public interface SecurityQues extends JpaRepository<SecurityQuestions, Integer> {

	SecurityQuestions findByquestionNo(Integer questionNo);
}
