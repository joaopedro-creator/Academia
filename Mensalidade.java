public class Mensalidade {
    public static float valorMensalidade = 80.0f;
    private String aluno;
    private float valor;
    private String dataVencimento;
    private boolean pagamentoEfetuado;
    private boolean cancelar;
    private int diasTreino;


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
    // Define o valor com base nos dias de treino
    public void valorPlano(){
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
    
    public void Aulaesperimental(boolean NuncaFrequentou){
        if(NuncaFrequentou){
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
        return cancelar;
    }
    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }
    public int getDiasTreino() {
        return diasTreino;
    }
    public void setDiasTreino(int diasTreino) {
        this.diasTreino = diasTreino;
    }



}
