public class Mensalidade {
    private Aluno aluno;
    private float valor;
    private String dataVencimento;
    private boolean pagamentoEfetuado;
    private boolean cancelada;
    private int diasTreino;
    private boolean temInstrutor;
    

    public Mensalidade(Aluno aluno, String dataVencimento, int diasTreino) {
        this.aluno = aluno;
        this.valor = 100.0f;
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
    
    // Cancela o pagamento da mensalidade
    public void cancelarPagamento() {
        this.cancelada = true;
        this.pagamentoEfetuado = false;
        System.out.println("Pagamento cancelado para o aluno: " + aluno);
    }


    // Define o valor com base nos dias de treino
    public void definirValorPlano() {
    if (diasTreino == 1) {
        this.valor = 20.0f;
        System.out.println("Diária selecionada. Valor: R$20,00");
    } else if (temInstrutor && diasTreino == 3) {
        this.valor = 195.0f;
        System.out.println("Plano com instrutor (3 dias). Valor: R$195,00");

    } else if (temInstrutor && diasTreino == 5) {
        this.valor = 240.0f;
        System.out.println("Plano com instrutor (5 dias). Valor: R$240,00");

    } else if (diasTreino == 3) {
        this.valor = 45.0f;
        System.out.println("Plano de 3 dias selecionado. Valor: R$45,00");

    } else if (diasTreino == 5) {
        this.valor = 80.0f;
        System.out.println("Plano de 5 dias selecionado. Valor: R$80,00");

    } else {
        this.valor = 100.0f;
        System.out.println("Plano padrão selecionado. Valor: R$100,00");
    }
}

    // Pagamento de diária 
    public void pagarDiaria(){
        this.valor = 20.0f;
        this.pagamentoEfetuado = true;
        System.out.println("Pagamento de diária efetuado para o aluno: " + aluno.getNome() + " no valor de R$20,00");
    }
    


    // Getters and Setters
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
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
    public boolean isTemInstrutor() {
        return temInstrutor;
    }
    public void setTemInstrutor(boolean temInstrutor) {
        this.temInstrutor = temInstrutor;
    }
}
