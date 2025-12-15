public class Aluno extends Pessoa {
    private int matricula;
    private String plano;

    public Aluno(int id, String nome, String cpf, String dataNascimento, String endereco, int matricula, String plano) {
        super(id, nome, cpf, dataNascimento, endereco);
        this.matricula = matricula;
        this.plano = plano;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Detalhes do Aluno:");
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data de Nascimento: " + getDataNascimento());
        System.out.println("Endereço: " + getEndereco());
        System.out.println("Matrícula: " + matricula);
        System.out.println("Plano: " + plano);
    }

    public void inscreverAtividade(String atividade) {
        System.out.println("Aluno " + getNome() + " inscrito na atividade: " + atividade);
    }

    public void cancelarInscricao(String atividade) {
        System.out.println("Inscrição do aluno " + getNome() + " na atividade " + atividade + " cancelada.");
    }


    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public String getPlano() {
        return plano;
    }
    public void setPlano(String plano) {
        this.plano = plano;
    }
    
}
