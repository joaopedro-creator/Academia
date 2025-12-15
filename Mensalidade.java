public class Mensalidade {
    private String aluno;
    private float valor;
    private String dataVencimento;
    private boolean pagamentoEfetuado;
    private boolean cancelar;


    public Mensalidade(String aluno, float valor, String dataVencimento) {
        this.aluno = aluno;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.pagamentoEfetuado = false;
    }

    public void efetuarPagamento() {
        this.pagamentoEfetuado = true;
        System.out.println("Pagamento efetuado para o aluno: " + aluno);
    }

    public String encerrarPagamento(){
        if(cancelar){
            return "Pagamento cancelado para o aluno: " + aluno;
        } else {
            return "Pagamento não cancelado para o aluno: " + aluno;
        }
    }
}
