import java.util.ArrayList;
import java.util.List;

public class Questions {
    private int tam;
    private List<String> list;

    public Questions() {
        this.list = new ArrayList<>();
        tam = 0;
    }

    public List<String> getList() {
        return list;
    }

    public int getTam() {
        return tam;
    }

    public void addQuestion(String question){
        list.add(question);
        tam++;
    }

    public void deleteQuestion(int index){
        list.remove(index - 1);
        tam--;
    }
}
