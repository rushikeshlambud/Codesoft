import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    public int correctOptionIndex;

    public Question(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOptionIndex;
    }
}

class Quizgame {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private int timeLimitInSeconds;

    public Quizgame(List<Question> questions, int timeLimitInSeconds) {
        this.questions = questions;
        this.timeLimitInSeconds = timeLimitInSeconds;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void start() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            startTimer();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice (1-" + options.size() + "): ");
            int selectedOption = scanner.nextInt();

            if (currentQuestion.isCorrect(selectedOption - 1)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer was: " + options.get(currentQuestion.correctOptionIndex));
            }

            currentQuestionIndex++;
            stopTimer();

            start();
        } else {
            displayResult();
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! The correct answer was: " + questions.get(currentQuestionIndex).getOptions().get(questions.get(currentQuestionIndex).correctOptionIndex));
                currentQuestionIndex++;
                stopTimer();
                start();
            }
        }, timeLimitInSeconds * 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

public class quizess {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Shape of Egg is?", List.of("Circal", "Oval", "Round", "Rectangle"), 1 ));
        questions.add(new Question("What is the largest planet in our solar system?", List.of("Earth", "Mars", "Jupiter", "Venus"), 2));
        questions.add(new Question("Which gas do plants absorb from the atmosphere?", List.of("Oxygen", "Carbon dioxide", "Hydrogen", "Nitrogen"), 1));
        questions.add(new Question("Brain of computer?", List.of("CPU", "Mause", "Keyboard", "Printer"), 0));
        questions.add(new Question("Which is the most sensitive organ in our body?", List.of("Brain", "Hand", "Hair", "Skin"), 3));
        questions.add(new Question("How many Cricket world cups does India have?", List.of("5", "3", "2", "1"), 2));

        int timeLimitInSeconds = 30; // Adjust the time limit for each question as needed

        Quizgame quiz = new Quizgame(questions, timeLimitInSeconds);
        quiz.start();
    }
}
