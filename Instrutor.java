public class Instrutor extends Pessoa implements Autenticavel {
    private String especializacao;
    private String cref;


    public Instrutor(int id, String nome, String cpf, String dataNascimento, String endereco, String especializacao, String cref) {
        super(id, nome, cpf, dataNascimento, endereco);
        this.especializacao = especializacao;
        this.cref = cref;
    }

    @Override //Dados do Instrutor  
    public void exibirDetalhes() {
        System.out.println("Detalhes do Instrutor:");
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
        System.out.println("Data de Nascimento: " + getDataNascimento());
        System.out.println("Endereço: " + getEndereco());
        System.out.println("Especialização: " + especializacao);
        System.out.println("CREF: " + cref);
    }

    @Override
    public boolean login(String usuario, int senha) {
        // Implementação simples de login
        int crefNumerico = Integer.parseInt(getCref());
        if (usuario.equalsIgnoreCase(getNome()) && senha == crefNumerico) {
            System.out.println("Login bem-sucedido para o instrutor: " + getNome());
            return true;
        } else {
            System.out.println("Falha no login para o instrutor: " + getNome());
            return false;
        }
    }

    // Getters and Setters
    public String getEspecializacao() {
        return especializacao;
    }
    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }
    public String getCref() {
        return cref;
    }
    public void setCref(String cref) {
        this.cref = cref;
    }


}
