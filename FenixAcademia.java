import java.util.ArrayList;
import java.util.List; // Importação necessária
import java.util.Scanner;      // Importação necessária

public class FenixAcademia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Criando as listas (Banco de dados temporário)
        List<Pessoa> membros = new ArrayList<>();
        List<Atividade> atividades = new ArrayList<>();
        List<Mensalidade> mensalidades = new ArrayList<>();

        System.out.println("Bem-vindo à Fênix Academia!");
        int opcao;

        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gerenciar Alunos (Cadastrar/Listar)");
            System.out.println("2. Gerenciar Instrutores (Cadastrar/Listar)");
            System.out.println("3. Gerenciar Atividades");
            System.out.println("4. Gerenciar Mensalidades");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    menuAlunos(sc, membros);
                    break;
                case 2:
                    menuInstrutores(sc, membros);
                    break;
                case 3:
                    System.out.println("Funcionalidade de Atividades em breve...");
                    break;
                case 4:
                    System.out.println("Funcionalidade de Mensalidades em breve...");
                    break;
                case 5:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    // --- MÉTODOS AUXILIARES PARA ORGANIZAR O CÓDIGO ---

    private static void menuAlunos(Scanner sc, List<Pessoa> membros) {
        System.out.println("\n[1] Cadastrar Aluno | [2] Listar Alunos");
        int subOpcao = Integer.parseInt(sc.nextLine());

        if (subOpcao == 1) {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Data de Nascimento: ");
            String dataNasc = sc.nextLine();
            System.out.print("Endereço: ");
            String endereco = sc.nextLine();
            System.out.print("Matrícula: ");
            int mat = Integer.parseInt(sc.nextLine());
            System.out.print("Plano: ");
            String plano = sc.nextLine();
            System.out.println("Peso: ");
            float peso = Float.parseFloat(sc.nextLine());
            System.out.println("Altura: ");
            float altura = Float.parseFloat(sc.nextLine());
            System.out.println("Objetivo: ");
            String objetivo = sc.nextLine();
            
            // Criando o objeto e adicionando à lista
            Aluno novoAluno = new Aluno(membros.size() + 1, nome, cpf, dataNasc, endereco, mat, plano   , peso, altura, objetivo);
            membros.add(novoAluno);
            System.out.println("Aluno cadastrado com sucesso!");
        } else {
            System.out.println("\n--- LISTA DE ALUNOS ---");
            for (Pessoa p : membros) {
                if (p instanceof Aluno) { // Filtra apenas quem é Aluno
                    p.exibirDetalhes();
                }
            }
        }
    }

    private static void menuInstrutores(Scanner sc, List<Pessoa> membros) {
        System.out.println("\n[1] Cadastrar Instrutor | [2] Listar Instrutores");
        int subOpcao = Integer.parseInt(sc.nextLine());

        if (subOpcao == 1) {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Data de Nascimento: ");
            String dataNasc = sc.nextLine();
            System.out.print("Endereço: ");
            String endereco = sc.nextLine();
            System.out.println("Especialização: ");
            String especializacao = sc.nextLine();
            System.out.print("CREF: ");
            String cref = sc.nextLine();

            Instrutor novoInstrutor = new Instrutor(membros.size() + 1, nome, cpf, dataNasc, endereco, especializacao, cref);
            membros.add(novoInstrutor);
            System.out.println("Instrutor cadastrado!");
        } else {
            System.out.println("\n--- LISTA DE INSTRUTORES ---");
            for (Pessoa p : membros) {
                if (p instanceof Instrutor) { // Filtra apenas quem é Instrutor
                    p.exibirDetalhes();
                }
            }
        }
    }
}





