import exceptions.EmailException;
import exceptions.NameException;

import java.util.List;

public class Validate {

    public static void name(String name) {
        if (name.toCharArray().length <= 10){
            throw new NameException("O nome precisa ter no mínimo 10 caracteres");
        }
    }

    public static void email(String email, List<User> users) {

        if (!email.contains("@")){
            throw new EmailException("O formato do email está inválido, precisa conter um '@'");
        }

        for (User user : users) {
            if (user.getEmail().equals(email)){
                throw new EmailException("O email já está cadastrado!");
            }
        }

    }
}
