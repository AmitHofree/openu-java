import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class MultiChoiceController {
    private static final String QUESTION_FILE_PATH = "./example.txt";
    private List<Question> questions;
    private int currentQuestionNumber, correctAnswersCount;
    @FXML
    private RadioButton option1, option2, option3, option4;
    private ToggleGroup toggleGroup;
    @FXML
    private Button nextButton;
    @FXML
    private Label questionText;
    private boolean lastAnswerCorrect, isFirstQuestion;

    public MultiChoiceController() {
        try {
            this.currentQuestionNumber = 0;
            this.correctAnswersCount = 0;
            this.toggleGroup = new ToggleGroup();
            this.lastAnswerCorrect = false;
            this.isFirstQuestion = true;
            this.questions = loadData();
        } catch (FileNotFoundException exception) {
            System.out.printf("Question file not found in path - %s, aborting.%n", QUESTION_FILE_PATH);
            Platform.exit();
        }
    }

    private List<Question> loadData() throws FileNotFoundException, IllegalArgumentException {
        File file = new File(QUESTION_FILE_PATH);
        QuestionLoader loader = new QuestionLoader();
        return loader.loadQuestionFile(file);
    }

    public void initialize() {
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);
        option4.setToggleGroup(toggleGroup);
        setStateForQuestion();
    }

    private void setStateForQuestion() {
        Question question = questions.get(currentQuestionNumber);
        if (isFirstQuestion) {
            questionText.setText(question.getQuestion());
        } else {
            questionText.setText(lastAnswerCorrect ? "Correct!\n" + question.getQuestion()
                    : "Incorrect!\n" + question.getQuestion());
        }
        List<String> answers = question.getAnswers();
        Collections.shuffle(answers);
        option1.setText(answers.get(0));
        option2.setText(answers.get(1));
        option3.setText(answers.get(2));
        option4.setText(answers.get(3));
        toggleGroup.selectToggle(option1);
    }

    private void setStateForFinish() {
        nextButton.setDisable(true);
        option1.setText("Option #1");
        option1.setDisable(true);
        option2.setText("Option #2");
        option2.setDisable(true);
        option3.setText("Option #3");
        option3.setDisable(true);
        option4.setText("Option #4");
        option4.setDisable(true);
        toggleGroup.selectToggle(option1);
        float score = (float) correctAnswersCount / questions.size() * 100;
        questionText.setText(String.format("Final score - %.1f%%", score));
    }

    public void onReset(ActionEvent actionEvent) {
        currentQuestionNumber = 0;
        correctAnswersCount = 0;
        isFirstQuestion = true;
        lastAnswerCorrect = false;
        option1.setDisable(false);
        option2.setDisable(false);
        option3.setDisable(false);
        option4.setDisable(false);
        nextButton.setDisable(false);
        setStateForQuestion();
    }

    public void onNext(ActionEvent actionEvent) {
        isFirstQuestion = false;

        RadioButton selectedToggle = (RadioButton) toggleGroup.getSelectedToggle();
        String selectedAnswer = selectedToggle.getText();
        if (selectedAnswer.equals(questions.get(currentQuestionNumber).getCorrectAnswer())) {
            correctAnswersCount += 1;
            lastAnswerCorrect = true;
        } else {
            lastAnswerCorrect = false;
        }

        currentQuestionNumber += 1;
        if (currentQuestionNumber >= questions.size()) {
            setStateForFinish();
        } else {
            setStateForQuestion();
        }
    }
}
