package codesquad.service;

import codesquad.domain.Answer;
import codesquad.repository.AnswerRepository;
import codesquad.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionService questionService;

    public void create(Long questionId, String answer, HttpSession session) {
        answerRepository.save(new Answer().builder().question(questionService.findQuestion(questionId)).answer(answer).writer(HttpSessionUtils.getUserFromSession(session).getName()).build());
    }

    public List<Answer> findAnswers(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
}
