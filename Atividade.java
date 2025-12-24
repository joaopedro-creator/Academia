public class Atividade {
    private String nome; // Nome da atividade
    private String tipo;// "superiores", "inferiores" ou "cardio";
    private int series;
    private int repeticoes;
    private int duracao; // em minutos
    private String equipamentoNecessario;
    private int carga; // em kg
    private String sexo; // "masculino" ou "feminino"


    public Atividade(String nome, String tipo, int series, int repeticoes, int duracao, String equipamentoNecessario, int carga, String sexo) {
        this.nome = nome;
        this.tipo = tipo;
        this.series = series;
        this.repeticoes = repeticoes;
        this.duracao = duracao;
        this.equipamentoNecessario = equipamentoNecessario;
        this.carga = carga;
        this.sexo = sexo;
    }

    public void divisaoTreino() {
        System.out.println("Divisão de treinos" );
        if(sexo.equalsIgnoreCase("masculino")){
            if(tipo.equalsIgnoreCase("superiores")){
                System.out.println("Segunda-feira: Peito e Tríceps");
                System.out.println("Quarta-feira: Costas e Bíceps");
                System.out.println("Sexta-feira: Pernas e Ombros");
            } else if (tipo.equalsIgnoreCase("inferiores")){
                System.out.println("Terça-feira: Pernas");
                System.out.println("Quinta-feira: Glúteos e Abdômen");
            } else if (tipo.equalsIgnoreCase("cardio")){
                System.out.println("Segunda, Quarta e Sexta-feira: 30 minutos de corrida");
            }
        } else if (sexo.equalsIgnoreCase("feminino")){
            if(tipo.equalsIgnoreCase("superiores")){
                System.out.println("Segunda-feira: Peito e Tríceps");
                System.out.println("Quarta-feira: Costas e Bíceps");
                System.out.println("Sexta-feira: Pernas e Ombros");
            } else if (tipo.equalsIgnoreCase("inferiores")){
                System.out.println("Terça-feira: Pernas");
                System.out.println("Quinta-feira: Glúteos e Abdômen");
            } else if (tipo.equalsIgnoreCase("cardio")){
                System.out.println("Segunda, Quarta e Sexta-feira: 30 minutos de caminhada rápida");
            }
        }
    }






    //getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getSeries() {
        return series;
    }
    public void setSeries(int series) {
        this.series = series;
    }
    public int getRepeticoes() {
        return repeticoes;
    }
    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public String getEquipamentoNecessario() {
        return equipamentoNecessario;
    }
    public void setEquipamentoNecessario(String equipamentoNecessario) {
        this.equipamentoNecessario = equipamentoNecessario;
    }
    public int getCarga() {
        return carga;
    }
    public void setCarga(int carga) {
        this.carga = carga;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}