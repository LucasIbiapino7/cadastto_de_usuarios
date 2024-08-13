import java.io.*;
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

        File file = new File("D:\\dev-dojo-java\\sistemadecastastro\\users\\" + fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(user.getName());
            bw.append(",");
            bw.append(user.getEmail());
            bw.append(",");
            bw.append(Integer.toString(user.getAge()));
            bw.append(",");
            bw.append(Double.toString(user.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
