public class Vendedor extends Pessoa {
    //Armazena a matrícula do vendedor.
    private String matricula;

    //Construtor da Classe.
    public Vendedor(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.matricula = matricula;
    }

    // Método getter para obter a matrícula do vendedor.
    public String getMatricula() {
        return matricula;
    }

    // Método setter para definir ou atualizar a matrícula do vendedor.
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}