import java.util.Scanner;
import java.util.ArrayList;

public class FenixAcademia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Aluno> alunos = new ArrayList<>();
        List<Instrutor> Instrutores = new ArrayList<>();
        List<Atividade> Atividades = new ArrayList<>();

        int opcao;

        System.out.println("\n    -------Bem-vindo à Fênix Academia!-------");
        System.out.println("Digite a opção");
        System.out.println("1- CADASTRAR ALUNO");
        System.out.println("2- CADASTRAR INSTRUTOR");
        System.out.println("3- CRIAR ATIVIDADE");
        System.out.println("4- MATRICULAR ALUNO EM ATIVIDADE");
        System.out.println("5- PAGAR MENSALIDADE");
        System.out.println("6- LISTAR ALUNOS");
        System.out.println("0- SAIR");
        opcao = sc.nextInt();

        switch(opcao) {
            case 1:
                cadastrarAluno();
                break;
            case 2:
                cadastrarInstrutor();
                break;
            case 3:
                criarAtividade();
                break;
            case 4:
                matricularAluno();
                break;
            case 5:
                pagarMensalidade();
                break;
            case 6:
                listarAlunos();
                break;
            case 7:
                listarAtividades();
                break;
            case 0:
                System.out.println("Encerrando sistema...");
                break;
            default:
                System.out.ptintln("Opção inválida");
        }


    }
}





