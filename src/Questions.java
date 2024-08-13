import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<String> list;

    public Questions() {
        this.list = new ArrayList<>();
    }

    public List<String> getList() {
        return list;
    }

    public void addQuestion(String question){
        list.add(question);
    }
}
