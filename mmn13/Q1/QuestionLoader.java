import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QuestionLoader {
    public List<Question> loadQuestionFile(File file) throws FileNotFoundException, IllegalArgumentException {
        ArrayList<Question> questionList = new ArrayList<Question>();
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            Question question = readQuestion(input);
            questionList.add(question);
        }
        return questionList;
    }

    private Question readQuestion(Scanner input) {
        try {
            String questionText = input.nextLine();
            String correctAnswer = input.nextLine();
            String wrongAnswer1 = input.nextLine();
            String wrongAnswer2 = input.nextLine();
            String wrongAnswer3 = input.nextLine();
            ArrayList<String> answers = new ArrayList<>(Arrays.asList(correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3));
            return new Question(questionText, answers, correctAnswer);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("File is malformed!");
        }
    }
}
