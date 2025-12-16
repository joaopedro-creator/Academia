public class Mensalidade {
    private String aluno;
    private float valor;
    private String dataVencimento;
    private boolean pagamentoEfetuado;
    private boolean cancelada;
    private int diasTreino;


    public Mensalidade(String aluno, String dataVencimento, int diasTreino) {
        this.aluno = aluno;
        this.valor = 80.0f;
        this.dataVencimento = dataVencimento;
        this.pagamentoEfetuado = false;
        this.cancelada = false;
        this.diasTreino = diasTreino;
        definirValorPlano();
    }

    public void efetuarPagamento() {
        if (!cancelada) {
            this.pagamentoEfetuado = true;
            System.out.println("Pagamento efetuado para o aluno: " + aluno);
        } else {
            System.out.println("Pagamento não permitido. Mensalidade cancelada.");
    }
}
    

    public void cancelarPagamento() {
        this.cancelada = true;
        this.pagamentoEfetuado = false;
        System.out.println("Pagamento cancelado para o aluno: " + aluno);
    }


    // Define o valor com base nos dias de treino
    public void definirValorPlano(){
        if(diasTreino == 3){
            this.valor = 45.0f;
        } else if (diasTreino == 5){
            this.valor = 80.0f;
        } 
    }


    // Pagamento de diária 
    public void pagarDiaria(){
        System.out.println("Pagamento de diária efetuado para o aluno: " + aluno + " no valor de R$20,00");
    }
    

    public void aulaExperimental(boolean nuncaFrequentou){
        if(nuncaFrequentou){
            System.out.println("Aluno: " + aluno + " pode participar de uma aula experimental gratuita.");
        } else {
            System.out.println("Aluno: " + aluno + " não pode participar de uma aula experimental gratuita.");
        }
    }


    // Getters and Setters
    public String getAluno() {
        return aluno;
    }
    public void setAluno(String aluno) {
        this.aluno = aluno;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public String getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public boolean isPagamentoEfetuado() {
        return pagamentoEfetuado;
    }
    public void setPagamentoEfetuado(boolean pagamentoEfetuado) {
        this.pagamentoEfetuado = pagamentoEfetuado;
    }
    public boolean isCancelar() {
        return cancelada;
    }
    public void setCancelar(boolean cancelar) {
        this.cancelada = cancelar;
    }
    public int getDiasTreino() {
        return diasTreino;
    }
    public void setDiasTreino(int diasTreino) {
        this.diasTreino = diasTreino;
    }



}
