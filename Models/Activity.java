package Models;

public class Activity {

    private String name;
    private String Descricao;

    public Activity(String name, String descricao) {
        this.name = name;
        Descricao = descricao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
