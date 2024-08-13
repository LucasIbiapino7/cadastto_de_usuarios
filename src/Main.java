import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();

        Questions questions = FileService.readAndPersist();

        for (String question : questions.getList()) {
            System.out.println(question);
        }

        System.out.println("Responda as perguntas:");
        String name = scanner.nextLine();
        String email = scanner.nextLine();
        Integer age = scanner.nextInt();
        Double height = scanner.nextDouble();

        User user = new User(name, email, age, height);

        FileService.saveFileUser(user, users);
        users.add(user);

        scanner.close();

    }
}