import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static Questions readAndPersist() {
        Questions questions = new Questions();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("formulario.txt")))) {
            String line = br.readLine();
            while (line != null) {
                questions.addQuestion(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static void saveFileUser(User user, List<User> users) {
        String fileName = concatenateName(user.getName(), users.size() + 1);
        System.out.println(fileName);
    }

    private static String concatenateName(String name, int i) {
        String[] nomeSplit = name.split(" ");
        String fileName = i + "-";
        for (String s : nomeSplit) {
            fileName = fileName.concat(s).toUpperCase();
        }
        fileName = fileName.concat(".TXT");
        return fileName;
    }

}
