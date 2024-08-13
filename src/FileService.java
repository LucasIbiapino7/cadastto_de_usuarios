import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static Questions readAndPersist() {
        Questions questions = new Questions();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("formulario.txt")))) {
            String line = br.readLine();
            while (line != null) {
                String[] split = line.split(" - ");
                questions.addQuestion(split[1]);
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

        File file = new File(fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.append(user.getName());
            bw.append(",");
            bw.append(user.getEmail());
            bw.append(",");
            bw.append(Integer.toString(user.getAge()));
            bw.append(",");
            bw.append(Double.toString(user.getHeight()));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        users.add(user);

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

    public static void addNewQuestion(String question, Questions questions) {
        questions.addQuestion(question);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("formulario.txt"), true))){
            String newQuestion = (questions.getTam()) + " - ";
            newQuestion = newQuestion.concat(question);
            bw.newLine();
            bw.append(newQuestion);
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("List: " + questions.getList());
    }

    public static void deleteQuestion(int num, Questions questions) {
        if (num <= 4){
            System.out.println("Você não pode apagar essa pergunta!");
            return;
        }

        if (num > questions.getTam()){
            System.out.println("Essa pergunta não existe!");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("formulario.txt")))){
               questions.deleteQuestion(num);
            for (int i = 0; i < questions.getList().size(); i++) {
                String newQuestion = (i + 1) + " - " + questions.getList().get(i);
                bw.append(newQuestion);
                if (i < (questions.getList().size() - 1)){
                    bw.newLine();
                }
                bw.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("List: " + questions.getList());

    }

    public static ArrayList<User> searchName(String nameSearch, List<User> users) {

        ArrayList<User> result = new ArrayList<>();
        int cont = 0;

        for (User user : users) {
            if (user.getName().contains(nameSearch)){
                result.add(user);
                cont++;
                if (cont >= 2){
                    break;
                }
            }
        }

        return result;

    }
}
