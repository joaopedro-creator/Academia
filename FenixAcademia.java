import java.util.ArrayList;
import java.util.List; 
import java.util.Scanner;      

public class FenixAcademia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Criando as listas (Banco de dados temporário)
        List<Pessoa> membros = new ArrayList<>();
        List<Atividade> atividades = new ArrayList<>();
        List<Mensalidade> mensalidades = new ArrayList<>();
        

        System.out.println("Bem-vindo à Fênix Academia!");

        int opcao;
        // Menu principal
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
                    menuAtividades(sc, atividades);
                    break;
                case 4:
                    menuMensalidades(sc, membros, mensalidades);
                    break;
                case 5:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static boolean realizarLogin(Scanner sc, List<Pessoa> membros){
            System.out.println("\n--- Login Necessário ---");
            System.out.print("Digite seu nome de usuário: ");
            String usuario = sc.nextLine();
            System.out.print("Digite sua senha (número): ");
            int senha = Integer.parseInt(sc.nextLine());

            for (Pessoa p : membros) {
                if (p instanceof Autenticavel) {
                    Autenticavel aut = (Autenticavel) p;
                    if (aut.login(usuario, senha)) {
                        return true; // Login bem-sucedido
                    }
                }
            }
            System.out.println("Falha no login. Usuário ou senha incorretos.");
            return false;
        
    }


    // Menu de Alunos
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
            Aluno novoAluno = new Aluno(membros.size() + 1, nome, cpf, dataNasc, endereco, mat, plano, peso, altura, objetivo);
            membros.add(novoAluno);
            System.out.println("Aluno cadastrado com sucesso!");
        } else if (subOpcao == 2) {
            System.out.println("-- AUTENTICAÇÃO NECESSÁRIA --");
            System.out.print("Digite seu nome de usuário: ");
            String usuario = sc.nextLine();
            System.out.print("Digite sua senha (número): ");
            int senha = Integer.parseInt(sc.nextLine());

            boolean autenticado = false;
            for (Pessoa p : membros) {
                if (p instanceof Autenticavel) {
                    Autenticavel aut = (Autenticavel) p;
                    if (aut.login(usuario, senha)) {
                        autenticado = true;
                        break; // Login bem-sucedido
                    }
                }
            }

            if(autenticado) {
            System.out.println("\n--- LISTA DE ALUNOS ---");
            for (Pessoa p : membros) {
                if (p instanceof Aluno) { // Filtra apenas quem é Aluno
                    p.exibirDetalhes();
                }
            }
        }
    } else {
            System.out.println("Falha no login. Usuário ou senha incorretos.");
        }
    }

    // Menu de Instrutores
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
        } else if (subOpcao == 2) {
            System.out.println("-- AUTENTICAÇÃO NECESSÁRIA --");
            System.out.print("Digite seu nome de usuário: ");
            String usuario = sc.nextLine();
            System.out.print("Digite sua senha (número): ");
            int senha = Integer.parseInt(sc.nextLine());

            boolean autenticado = false;
            for (Pessoa p : membros) {
                if (p instanceof Autenticavel) {
                    Autenticavel aut = (Autenticavel) p;
                    if (aut.login(usuario, senha)) {
                        autenticado = true;
                        break; // Login bem-sucedido
                    }
                }
            }
            if(autenticado) {
            System.out.println("\n--- LISTA DE INSTRUTORES ---");
            for (Pessoa p : membros) {
                if (p instanceof Instrutor) { 
                    p.exibirDetalhes();
                }
            }
          }
        } else {
            System.out.println("Falha no login. Usuário ou senha incorretos.");
        }
    }


    // Menu de Mensalidades
        private static void menuMensalidades(Scanner sc, List<Pessoa> membros, List<Mensalidade> mensalidades) {
        System.out.println("\n--- Gerenciamento de Mensalidades ---");
        System.out.println("1. Registrar Nova Mensalidade/Pagamento || 2. Ver Histórico de Mensalidades|| 3. Pagar Diária ");
        System.out.print("Escolha: ");
        int subOpcao = Integer.parseInt(sc.nextLine());

        if (subOpcao == 1) {
            // 1. Precisamos encontrar o aluno pela matrícula
            System.out.print("Digite a matrícula do aluno: ");
            int matriculaBusca = Integer.parseInt(sc.nextLine());
            Aluno alunoEncontrado = null;

            for (Pessoa p : membros) {
                if (p instanceof Aluno && ((Aluno) p).getMatricula() == matriculaBusca) {
                    alunoEncontrado = (Aluno) p;
                    break;
                }
            }
            
            if (alunoEncontrado != null) {
                // Coletar dados para a mensalidade
                System.out.print("Data de Vencimento (dd/mm/aaaa): ");
                String data = sc.nextLine();
                System.out.print("Dias de treino por semana (3 ou 5): ");
                int dias = Integer.parseInt(sc.nextLine());
                System.out.print("Terá instrutor fixo? (true/false): ");
                boolean temInstrutor = Boolean.parseBoolean(sc.nextLine());

                // Instanciar a Mensalidade
                Mensalidade novaMensa = new Mensalidade(alunoEncontrado, data, dias);
                novaMensa.setTemInstrutor(temInstrutor);
                
                // Calcular valor e processar
                novaMensa.definirValorPlano(); 
                novaMensa.efetuarPagamento();

                // Guardar no histórico
                mensalidades.add(novaMensa);
            } else {
                System.out.println("Erro: Aluno com matrícula " + matriculaBusca + " não encontrado.");
            }

        } else if (subOpcao == 2) {
            System.out.println("\n--- Histórico Geral ---");
            for (Mensalidade m : mensalidades) {
                // Exibe o nome do aluno (acessando o objeto Aluno dentro de Mensalidade) e o valor
                System.out.println("Aluno: " + m.getAluno().getNome() + " | Valor: R$" + m.getValor() + " | Pago: " + m.isPagamentoEfetuado());
            }
        } else if (subOpcao == 3) {
            System.out.println("\n--- PAGAMENTO DE DIÁRIA ---");
            System.out.print("Digite a matrícula do aluno: ");
            int matriculaBusca = Integer.parseInt(sc.nextLine());
            
            Aluno alunoEncontrado = null;
            for (Pessoa p : membros) {
                if (p instanceof Aluno && ((Aluno) p).getMatricula() == matriculaBusca) {
                    alunoEncontrado = (Aluno) p;
                    break;
                }
            }

            if (alunoEncontrado != null) {
                // 1. Criamos o objeto (passamos 1 dia apenas)
                Mensalidade diaria = new Mensalidade(alunoEncontrado, "Hoje", 1);
                
                // 2. Chamamos o seu método que você já tinha na classe!
                diaria.pagarDiaria(); 
                
                // 3. Adicionamos na lista para o histórico funcionar
                mensalidades.add(diaria);
            } else {
                System.out.println("Aluno não encontrado.");
            }
        }
    }

    // Menu de Atividades
    private static void menuAtividades(Scanner sc, List<Atividade> atividades) {
        System.out.println("\n--- Gerenciamento de Atividades ---");
        System.out.println("1. Cadastrar Nova Atividade || 2. Listar Atividades");
        System.out.print("Escolha: ");
        int subOpcao = Integer.parseInt(sc.nextLine());

        if (subOpcao == 1) {
            System.out.print("Nome da Atividade: ");
            String nome = sc.nextLine();
            System.out.print("Tipo (superiores/inferiores/cardio): ");
            String tipo = sc.nextLine();
            System.out.print("Séries: ");
            int series = Integer.parseInt(sc.nextLine());
            System.out.print("Repetições: ");
            int repeticoes = Integer.parseInt(sc.nextLine());
            System.out.print("Duração (minutos): ");
            int duracao = Integer.parseInt(sc.nextLine());
            System.out.print("Equipamento Necessário: ");
            String equipamento = sc.nextLine();
            System.out.print("Carga (kg): ");
            int carga = Integer.parseInt(sc.nextLine());
            System.out.print("Sexo (masculino/feminino): ");
            String sexo = sc.nextLine();

            Atividade novaAtividade = new Atividade(nome, tipo, series, repeticoes, duracao, equipamento, carga, sexo);
            atividades.add(novaAtividade);
            System.out.println("Atividade cadastrada com sucesso!");
        } else if (subOpcao == 2) {
            System.out.println("\n--- Lista de Atividades ---");
            for (Atividade a : atividades) {
                System.out.println("Nome: " + a.getNome() + " | Tipo: " + a.getTipo());
                a.divisaoTreino();
            }
        }
    }
}
