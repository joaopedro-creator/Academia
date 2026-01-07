import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class FenixAcademiaGUI extends JFrame {
    private CardLayout cl = new CardLayout();
    private JPanel cards = new JPanel(cl);

    // Listas (Banco de Dados em memória)
    private List<Pessoa> membros = new ArrayList<>();
    private List<Mensalidade> mensalidades = new ArrayList<>();
    private List<Atividade> atividades = new ArrayList<>();

    public FenixAcademiaGUI() {
        setTitle("Fênix Academia - Sistema de Gestão");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criando e adicionando as telas ao CardLayout
        cards.add(criarMenuPrincipal(), "Menu");
        cards.add(criarTelaAlunos(), "TelaAlunos");
        cards.add(criarTelaInstrutores(), "TelaInstrutores");
        cards.add(criarTelaAtividades(), "TelaAtividades");
        cards.add(criarTelaMensalidades(), "TelaMensalidades");

        add(cards);
        cl.show(cards, "Menu");
    }

    // --- 1. TELA: MENU PRINCIPAL ---
    private JPanel criarMenuPrincipal() {
        JPanel painel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("FÊNIX ACADEMIA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        
        // Painel para os 4 botões principais
        JPanel centro = new JPanel(new GridLayout(4, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 80, 30, 80));

        JButton btnAlunos = new JButton("Gerenciar Alunos");
        JButton btnInstrutores = new JButton("Gerenciar Instrutores");
        JButton btnAtividades = new JButton("Gerenciar Atividades");
        JButton btnMensalidades = new JButton("Gerenciar Mensalidades");

        // Ações dos botões - Corrigido a ordem e chamadas
        btnAlunos.addActionListener(e -> cl.show(cards, "TelaAlunos"));
        btnInstrutores.addActionListener(e -> cl.show(cards, "TelaInstrutores"));
        btnAtividades.addActionListener(e -> cl.show(cards, "TelaAtividades"));
        btnMensalidades.addActionListener(e -> cl.show(cards, "TelaMensalidades"));

        centro.add(btnAlunos); 
        centro.add(btnInstrutores); 
        centro.add(btnAtividades); 
        centro.add(btnMensalidades);
        
        painel.add(titulo, BorderLayout.NORTH);
        painel.add(centro, BorderLayout.CENTER);

        // Botão Sair isolado no sul para ser menor
        JPanel painelSair = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSair = new JButton("Sair do Sistema");
        btnSair.setPreferredSize(new Dimension(150, 35)); 
        btnSair.setBackground(new Color(255, 150, 150)); // Destaque para o botão sair
        btnSair.addActionListener(e -> System.exit(0));
        
        painelSair.add(btnSair);
        painel.add(painelSair, BorderLayout.SOUTH);

        return painel;
    }

    // --- 2. TELA: ALUNOS ---
    private JPanel criarTelaAlunos() {
        JPanel painel = new JPanel(new BorderLayout());
        JTextArea areaBusca = new JTextArea("Lista de Alunos vazia...");
        
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastro de Aluno"));
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtMat = new JTextField();

        form.add(new JLabel("Nome:")); form.add(txtNome);
        form.add(new JLabel("CPF:")); form.add(txtCpf);
        form.add(new JLabel("Matrícula:")); form.add(txtMat);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                int m = Integer.parseInt(txtMat.getText());
                membros.add(new Aluno(membros.size()+1, txtNome.getText(), txtCpf.getText(), "01/01/2000", "Endereço", m, "Padrão", 80, 1.75f, "Geral"));
                JOptionPane.showMessageDialog(this, "Aluno cadastrado!");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Erro: Verifique a Matrícula!"); }
        });
        form.add(btnSalvar);

        JButton btnListar = new JButton("Listar Alunos (Login)");
        btnListar.addActionListener(e -> {
            if(realizarLoginGUI()) {
                StringBuilder sb = new StringBuilder("--- ALUNOS ---\n");
                for(Pessoa p : membros) if(p instanceof Aluno a) sb.append(a.getNome()).append(" (Mat: ").append(a.getMatricula()).append(")\n");
                areaBusca.setText(sb.toString());
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cl.show(cards, "Menu"));

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(areaBusca), BorderLayout.CENTER);
        
        JPanel sul = new JPanel();
        sul.add(btnListar); sul.add(btnVoltar);
        painel.add(sul, BorderLayout.SOUTH);

        return painel;
    }

    // --- 3. TELA: INSTRUTORES ---
    private JPanel criarTelaInstrutores() {
        JPanel painel = new JPanel(new BorderLayout());
        JTextArea areaBusca = new JTextArea("Lista de Instrutores vazia...");
        
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastro de Instrutor"));
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtCref = new JTextField();

        form.add(new JLabel("Nome:")); form.add(txtNome);
        form.add(new JLabel("CPF:")); form.add(txtCpf);
        form.add(new JLabel("CREF:")); form.add(txtCref);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                membros.add(new Instrutor(membros.size()+1, txtNome.getText(), txtCpf.getText(), "01/01/2000", "Endereço", "Geral", txtCref.getText()));
                JOptionPane.showMessageDialog(this, "Instrutor cadastrado!");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Erro nos dados!"); }
        });
        form.add(btnSalvar);

        JButton btnListar = new JButton("Listar Instrutores (Login)");
        btnListar.addActionListener(e -> {
            if(realizarLoginGUI()) {
                StringBuilder sb = new StringBuilder("--- INSTRUTORES ---\n");
                for(Pessoa p : membros) if(p instanceof Instrutor i) sb.append(i.getNome()).append(" (CREF: ").append(i.getCref()).append(")\n");
                areaBusca.setText(sb.toString());
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cl.show(cards, "Menu"));

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(areaBusca), BorderLayout.CENTER);
        
        JPanel sul = new JPanel();
        sul.add(btnListar); sul.add(btnVoltar);
        painel.add(sul, BorderLayout.SOUTH);

        return painel;
    }

    // --- 4. TELA: ATIVIDADES ---
    private JPanel criarTelaAtividades() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Novo Exercício"));

        JTextField tNome = new JTextField();
        JTextField tSeries = new JTextField();
        JTextField tReps = new JTextField();
        JTextField tCarga = new JTextField();

        form.add(new JLabel("Exercício:")); form.add(tNome);
        form.add(new JLabel("Séries:")); form.add(tSeries);
        form.add(new JLabel("Reps:")); form.add(tReps);
        form.add(new JLabel("Carga (kg):")); form.add(tCarga);

        JButton btnAdd = new JButton("Adicionar");
        btnAdd.addActionListener(e -> {
            try {
                Atividade a = new Atividade(tNome.getText(), "Treino", Integer.parseInt(tSeries.getText()), 
                                           Integer.parseInt(tReps.getText()), 45, "Equipamento", 
                                           Integer.parseInt(tCarga.getText()), "Unissex");
                atividades.add(a);
                JOptionPane.showMessageDialog(this, "Atividade Adicionada!");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Erro: Use números em Séries/Reps/Carga"); }
        });
        form.add(btnAdd);

        JTextArea listaAtv = new JTextArea();
        JButton btnVer = new JButton("Ver Catálogo");
        btnVer.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("CATÁLOGO DE EXERCÍCIOS:\n");
            for(Atividade at : atividades) sb.append(at.getNome()).append(" - ").append(at.getSeries()).append("x").append(at.getRepeticoes()).append(" (").append(at.getCarga()).append("kg)\n");
            listaAtv.setText(sb.toString());
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cl.show(cards, "Menu"));

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(listaAtv), BorderLayout.CENTER);
        
        JPanel sul = new JPanel();
        sul.add(btnVer); sul.add(btnVoltar);
        painel.add(sul, BorderLayout.SOUTH);

        return painel;
    }

    // --- 5. TELA: MENSALIDADES ---
    private JPanel criarTelaMensalidades() {
        JPanel painel = new JPanel(new BorderLayout());
        JTextArea log = new JTextArea("Clique em 'Atualizar' para ver o histórico financeiro...");
        
        JPanel acoes = new JPanel(new FlowLayout());
        JButton btnDiaria = new JButton("Pagar Diária (R$20)");
        btnDiaria.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Digite a Matrícula do Aluno:");
            if(input != null && !input.isEmpty()) {
                try {
                    int m = Integer.parseInt(input);
                    for(Pessoa p : membros) {
                        if(p instanceof Aluno a && a.getMatricula() == m) {
                            Mensalidade ms = new Mensalidade(a, "07/01/2026", 1);
                            ms.pagarDiaria();
                            mensalidades.add(ms);
                            JOptionPane.showMessageDialog(this, "Pagamento realizado para: " + a.getNome());
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
                } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Matrícula Inválida!"); }
            }
        });

        JButton btnHist = new JButton("Atualizar Histórico");
        btnHist.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("PAGAMENTOS RECEBIDOS:\n");
            for(Mensalidade ms : mensalidades) sb.append(ms.getAluno().getNome()).append(" | R$").append(ms.getValor()).append("\n");
            log.setText(sb.toString());
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cl.show(cards, "Menu"));

        acoes.add(btnDiaria); acoes.add(btnHist); acoes.add(btnVoltar);
        
        JLabel sub = new JLabel("GESTÃO FINANCEIRA", SwingConstants.CENTER);
        sub.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(sub, BorderLayout.NORTH);
        painel.add(new JScrollPane(log), BorderLayout.CENTER);
        painel.add(acoes, BorderLayout.SOUTH);

        return painel;
    }

    // --- LOGIN ---
    private boolean realizarLoginGUI() {
        JTextField u = new JTextField();
        JPasswordField p = new JPasswordField();
        Object[] msg = {"Nome de Usuário:", u, "Senha (Matrícula ou CREF):", p};
        int res = JOptionPane.showConfirmDialog(this, msg, "Autenticação Necessária", JOptionPane.OK_CANCEL_OPTION);
        if(res == JOptionPane.OK_OPTION) {
            try {
                String user = u.getText();
                int pass = Integer.parseInt(new String(p.getPassword()));
                for(Pessoa pess : membros) if(pess instanceof Autenticavel aut && aut.login(user, pass)) return true;
            } catch(Exception e) {}
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenixAcademiaGUI().setVisible(true));
    }
}