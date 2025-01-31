import exceptions.EmailException;
import exceptions.NameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        Questions questions = FileService.readAndPersist();

        while (true) {
            System.out.println("1 - Cadastrar o usuário\n" +
                    "2 - Listar todos usuários cadastrados\n" +
                    "3 - Cadastrar nova pergunta no formulário\n" +
                    "4 - Deletar pergunta do formulário\n" +
                    "5 - Pesquisar usuário por nome ou idade ou email\n" +
                    "6 - sair");

            System.out.print("\nDigite sua opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (int i = 0; i < questions.getTam(); i++) {
                        System.out.println((i + 1) + " - " + questions.getList().get(i));
                    }
                    System.out.println("Responda as perguntas:");
                    try {
                        String name = scanner.nextLine();
                        Validate.name(name);
                        String email = scanner.nextLine();
                        Validate.email(email, users);
                        Integer age = scanner.nextInt();
                        Double height = scanner.nextDouble();

                        User user = new User(name, email, age, height);

                        FileService.saveFileUser(user, users);
                    }catch (NameException | EmailException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("usuários:");

                    for (int i = 0; i < users.size(); i++) {
                        System.out.println((i + 1) + " - " + users.get(i).getName());
                    }

                    System.out.println("-----------------------");
                    break;
                case 3:
                    System.out.println("Digite a nova pergunta a ser cadastrada:");
                    String question = scanner.nextLine();
                    FileService.addNewQuestion(question, questions);
                    break;
                case 4:
                    System.out.println("Essas são as perguntas cadastradas:");
                    for (int i = 0; i < questions.getTam(); i++) {
                        System.out.println((i + 1) + " - " + questions.getList().get(i));
                    }
                    System.out.println("Digite o número da pergunta que quer excluir:");
                    int num = scanner.nextInt();
                    FileService.deleteQuestion(num, questions);
                    break;
                case 5:
                    System.out.print("Digite o nome que vai pesquisar: ");
                    String nameSearch = scanner.nextLine();
                    ArrayList<User> usersResult = FileService.searchName(nameSearch, users);
                    if (usersResult.isEmpty()){
                        System.out.println("Nenhum usuário encontrado");
                    } else {
                        for (User userResult : usersResult) {
                            System.out.println(userResult);
                        }
                    }
                    break;
                case 6:
                    break;
            }

            if (choice == 6) {
                break;
            }
        }

        scanner.close();

    }
}