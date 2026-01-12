import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private static final String FILE_MEMBROS = "membros.txt";
    private static final String FILE_ATIVIDADES = "atividades.txt";
    private static final String FILE_MENSALIDADES = "mensalidades.txt";

    // --- SALVAMENTO ---
    public static void salvarTudo(List<Pessoa> membros, List<Atividade> atividades, List<Mensalidade> mensalidades) {
        salvarMembros(membros);
        salvarAtividades(atividades);
        salvarMensalidades(mensalidades);
    }

    private static void salvarMembros(List<Pessoa> membros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_MEMBROS))) {
            for (Pessoa p : membros) {
                if (p instanceof Aluno) {
                    writer.write("ALUNO;" + ((Aluno) p).toFileString());
                } else if (p instanceof Instrutor) {
                    writer.write("INSTRUTOR;" + ((Instrutor) p).toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar membros: " + e.getMessage());
        }
    }

    private static void salvarAtividades(List<Atividade> atividades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_ATIVIDADES))) {
            for (Atividade a : atividades) {
                writer.write(a.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar atividades: " + e.getMessage());
        }
    }

    private static void salvarMensalidades(List<Mensalidade> mensalidades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_MENSALIDADES))) {
            for (Mensalidade m : mensalidades) {
                // Mantendo a ordem correta para leitura posterior
                String mStr = m.getAluno().getMatricula() + ";" + // [0]
                             m.getValor() + ";" +                // [1]
                             m.getDataVencimento() + ";" +       // [2]
                             m.isPagamentoEfetuado() + ";" +     // [3]
                             m.isCancelar() + ";" +              // [4]
                             m.getDiasTreino() + ";" +           // [5]
                             m.isTemInstrutor();                 // [6]
                writer.write(mStr);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar mensalidades: " + e.getMessage());
        }
    }

    // --- CARREGAMENTO ---
    public static List<Pessoa> carregarMembros() {
        List<Pessoa> lista = new ArrayList<>();
        File file = new File(FILE_MEMBROS);
        if (!file.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] d = linha.split(";");
                
                try {
                    if (d[0].equals("ALUNO")) {
                        // id[1], nome[2], cpf[3], dataNasc[4], endereco[5], mat[6], plano[7], peso[8], altura[9], obj[10]
                        lista.add(new Aluno(Integer.parseInt(d[1]), d[2], d[3], d[4], d[5],
                                Integer.parseInt(d[6]), d[7], Float.parseFloat(d[8]),
                                Float.parseFloat(d[9]), d[10]));
                    } else if (d[0].equals("INSTRUTOR")) {
                        lista.add(new Instrutor(Integer.parseInt(d[1]), d[2], d[3], d[4], d[5], d[6], d[7]));
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.err.println("Erro ao processar linha de membro: " + linha);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar membros: " + e.getMessage());
        }
        return lista;
    }

    public static List<Mensalidade> carregarMensalidades(List<Pessoa> membros) {
        List<Mensalidade> lista = new ArrayList<>();
        File file = new File(FILE_MENSALIDADES);
        if (!file.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] d = linha.split(";");
                
                int matricula = Integer.parseInt(d[0]);
                Aluno alunoEncontrado = null;

                for (Pessoa p : membros) {
                    if (p instanceof Aluno a && a.getMatricula() == matricula) {
                        alunoEncontrado = a;
                        break;
                    }
                }

                if (alunoEncontrado != null) {
                    // 1. Criar o objeto com os dados básicos
                    Mensalidade m = new Mensalidade(alunoEncontrado, d[2], Integer.parseInt(d[5]));
                    
                    // 2. Restaurar o estado booleano ANTES de qualquer lógica de valor
                    m.setTemInstrutor(Boolean.parseBoolean(d[6]));
                    m.setPagamentoEfetuado(Boolean.parseBoolean(d[3]));
                    m.setCancelar(Boolean.parseBoolean(d[4]));
                    
                    // 3. Forçar o valor que estava salvo no arquivo para evitar que o 
                    //    construtor mude o preço devido ao método definirValorPlano()
                    m.setValor(Float.parseFloat(d[1]));
                    
                    lista.add(m);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar mensalidades: " + e.getMessage());
        }
        return lista;
    }
}