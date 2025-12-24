



public class Aluno extends Pessoa {
    private int matricula;
    private String plano;
    private float peso;
    private float altura;
    private String objetivo;
    


    public Aluno(int id, String nome, String cpf, String dataNascimento, String endereco, int matricula, String plano, float peso, float altura, String objetivo) {
        super(id, nome, cpf, dataNascimento, endereco);
        this.matricula = matricula;
        this.plano = plano;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
    }

    @Override //Dados do Aluno
    public void exibirDetalhes() {
        System.out.println("Detalhes do Aluno:");
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data de Nascimento: " + getDataNascimento());
        System.out.println("Endereço: " + getEndereco());
        System.out.println("Matrícula: " + matricula);
        System.out.println("Plano: " + plano);
        System.out.println("Peso: " + peso);
        System.out.println("Altura: " + altura);
        System.out.println("Objetivo: " + objetivo);
        System.out.println("IMC: " + calculoIMC());
    }

    public void inscreverAtividade(String atividade) {
        System.out.println("Aluno " + getNome() + " inscrito na atividade: " + atividade);
    }
    // Cancelar inscrição em atividade
    public void cancelarInscricao(String atividade) {
        System.out.println("Inscrição do aluno " + getNome() + " na atividade " + atividade + " cancelada.");
    }

    // Cálculo do IMC
    public float calculoIMC() {
    try {
        // Tentativa de cálculo
        float imc = peso / (altura * altura);
        System.out.println("IMC do aluno " + getNome() + ": " + imc);
        return imc;
    } catch (ArithmeticException e) {
        // Se a altura for 0, o Java cairá aqui
        System.err.println("Erro: Não é possível calcular o IMC com altura zero.");
        return 0;
    } catch (Exception e) {
        // Captura qualquer outro erro inesperado
        System.err.println("Ocorreu um erro inesperado no cálculo: " + e.getMessage());
        return 0;
    }
}
    

    // Getters and Setters
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
    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }
    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
    public String getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    
}
