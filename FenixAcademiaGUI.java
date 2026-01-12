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
        setTitle("Fênix Academia - Sistema de Cadastro");
        setSize(500, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Carregando dados salvos
        this.membros = GerenciadorArquivos.carregarMembros();
        this.mensalidades = GerenciadorArquivos.carregarMensalidades(this.membros);

        // Criando e adicionando as telas ao CardLayout
        cards.add(criarMenuPrincipal(), "Menu");
        cards.add(criarTelaAlunos(), "TelaAlunos");
        cards.add(criarTelaInstrutores(), "TelaInstrutores");
        cards.add(criarTelaAtividades(), "TelaAtividades");
        cards.add(criarTelaMensalidades(), "TelaMensalidades");

        add(cards);
        cl.show(cards, "Menu");
    }

    private void autoSalvar() {
        GerenciadorArquivos.salvarTudo(membros, atividades, mensalidades);
        System.out.println("Dados salvos nos arquivos .txt!"); 
    }

    // --- 1. TELA: MENU PRINCIPAL ---
    private JPanel criarMenuPrincipal() {
        JPanel painel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("FÊNIX ACADEMIA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        
        JPanel centro = new JPanel(new GridLayout(4, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(10, 80, 30, 80));

        JButton btnAlunos = new JButton("Gerenciar Alunos");
        JButton btnInstrutores = new JButton("Gerenciar Instrutores");
        JButton btnAtividades = new JButton("Gerenciar Atividades");
        JButton btnMensalidades = new JButton("Gerenciar Mensalidades");

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

        JPanel painelSair = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSair = new JButton("Sair do Sistema");
        btnSair.setPreferredSize(new Dimension(150, 35)); 
        btnSair.setBackground(new Color(255, 150, 150));
        btnSair.addActionListener(e -> System.exit(0));
        
        painelSair.add(btnSair);
        painel.add(painelSair, BorderLayout.SOUTH);

        return painel;
    }

    // --- 2. TELA: ALUNOS (Com Data, Endereço e Excluir) ---
    private JPanel criarTelaAlunos() {
        JPanel painel = new JPanel(new BorderLayout());
        JTextArea areaBusca = new JTextArea("Lista de Alunos vazia...");
        areaBusca.setEditable(false);
        
        // Ajustado para 10 linhas para os novos campos
        JPanel form = new JPanel(new GridLayout(10, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Cadastro de Aluno"));

        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtDataNasc = new JTextField();
        JTextField txtEndereco = new JTextField(); 
        JTextField txtMat = new JTextField();
        JTextField txtPlano = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtAltura = new JTextField();
        JTextField txtObj = new JTextField();

        form.add(new JLabel("Nome:")); form.add(txtNome);
        form.add(new JLabel("CPF:")); form.add(txtCpf);
        form.add(new JLabel("Data Nasc.:")); form.add(txtDataNasc);
        form.add(new JLabel("Endereço:")); form.add(txtEndereco); 
        form.add(new JLabel("Matrícula:")); form.add(txtMat);
        form.add(new JLabel("Plano:")); form.add(txtPlano);
        form.add(new JLabel("Peso (kg):")); form.add(txtPeso);
        form.add(new JLabel("Altura (ex 1.75):")); form.add(txtAltura);
        form.add(new JLabel("Objetivo:")); form.add(txtObj);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                int m = Integer.parseInt(txtMat.getText());
                float p = Float.parseFloat(txtPeso.getText().replace(",", "."));
                float a = Float.parseFloat(txtAltura.getText().replace(",", "."));
                
                
                membros.add(new Aluno(
                    membros.size() + 1, 
                    txtNome.getText(), 
                    txtCpf.getText(), 
                    txtDataNasc.getText(),
                    txtEndereco.getText(),
                    m, 
                    txtPlano.getText(), 
                    p, 
                    a, 
                    txtObj.getText()
                ));
                
                autoSalvar();
                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                
                // Limpar campos
                txtNome.setText(""); txtCpf.setText(""); txtDataNasc.setText("");
                txtEndereco.setText(""); txtMat.setText(""); txtPlano.setText("");
                txtPeso.setText(""); txtAltura.setText(""); txtObj.setText("");
                
            } catch(Exception ex) { 
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos numéricos!"); 
            }
        });
        form.add(new JLabel("")); form.add(btnSalvar);

        JButton btnListar = new JButton("Listar Alunos (Login)");
        btnListar.addActionListener(e -> {
            if(realizarLoginGUI()) {
                StringBuilder sb = new StringBuilder("--- ALUNOS ---\n");
                for(Pessoa p : membros) {
                    if(p instanceof Aluno aluno) {
                        sb.append(aluno.getNome()).append(" (Mat: ").append(aluno.getMatricula()).append(")\n");
                    }
                }
                areaBusca.setText(sb.toString());
            }
        });

        JButton btnExcluir = new JButton("Excluir Aluno");
        btnExcluir.addActionListener(e -> {
            String matStr = JOptionPane.showInputDialog(this, "Digite a Matrícula para excluir:");
            if (matStr != null && !matStr.isEmpty()) {
                try {
                    int mat = Integer.parseInt(matStr);
                    boolean removido = membros.removeIf(p -> p instanceof Aluno a && a.getMatricula() == mat);
                    if (removido) {
                        autoSalvar();
                        JOptionPane.showMessageDialog(this, "Removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Não encontrado.");
                    }
                } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Valor inválido."); }
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cl.show(cards, "Menu"));

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(areaBusca), BorderLayout.CENTER);
        
        JPanel sul = new JPanel();
        sul.add(btnListar); 
        sul.add(btnExcluir);
        sul.add(btnVoltar);
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
        JTextField txtDataNasc = new JTextField();
        JTextField txtEndereco = new JTextField();
        JTextField txtEspecializacao = new JTextField();
        JTextField txtCref = new JTextField();

        form.add(new JLabel("Nome:")); form.add(txtNome);
        form.add(new JLabel("CPF:")); form.add(txtCpf);
        form.add(new JLabel("Data de Nascimento:")); form.add(txtDataNasc);
        form.add(new JLabel("Endereço:")); form.add(txtEndereco);
        form.add(new JLabel("Especialização:")); form.add(txtEspecializacao);
        form.add(new JLabel("CREF:")); form.add(txtCref);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                membros.add(new Instrutor(membros.size()+1, txtNome.getText(), txtCpf.getText(), txtDataNasc.getText(), txtEndereco.getText(), txtEspecializacao.getText(), txtCref.getText()));
                autoSalvar();
                JOptionPane.showMessageDialog(this, "Instrutor cadastrado!");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "Erro!"); }
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
        
        // Formulário com 6 linhas para incluir Tipo e Sexo
        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Novo Exercício / Sugestão de Treino"));

        JTextField tNome = new JTextField();
        
        // Usando ComboBox para evitar erros de digitação que quebrariam o método divisaoTreino
        String[] tipos = {"superiores", "inferiores", "cardio"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        
        String[] sexos = {"masculino", "femenino"};
        JComboBox<String> cbSexo = new JComboBox<>(sexos);

        JTextField tSeries = new JTextField();
        JTextField tReps = new JTextField();
        JTextField tCarga = new JTextField();

        form.add(new JLabel("Exercício:")); form.add(tNome);
        form.add(new JLabel("Tipo:")); form.add(cbTipo);
        form.add(new JLabel("Sexo:")); form.add(cbSexo);
        form.add(new JLabel("Séries:")); form.add(tSeries);
        form.add(new JLabel("Reps:")); form.add(tReps);
        form.add(new JLabel("Carga (kg):")); form.add(tCarga);

        JTextArea areaSaida = new JTextArea(10, 30);
        areaSaida.setEditable(false);
        areaSaida.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JButton btnAdd = new JButton("Salvar e Ver Divisão");
        btnAdd.setBackground(new Color(173, 216, 230));
        
        btnAdd.addActionListener(e -> {
            try {
                // Criando o objeto com os dados dos ComboBoxes
                Atividade a = new Atividade(
                    tNome.getText(), 
                    (String)cbTipo.getSelectedItem(), 
                    Integer.parseInt(tSeries.getText()), 
                    Integer.parseInt(tReps.getText()), 
                    45, 
                    "Equipamento", 
                    Integer.parseInt(tCarga.getText()), 
                    (String)cbSexo.getSelectedItem()
                );
                
                atividades.add(a);
                autoSalvar();

                // Lógica para mostrar a Divisão de Treino na Tela
                // Como o seu método original é void e usa System.out, vamos simular a lógica dele aqui:
                String divisao = gerarTextoDivisao(a.getTipo(), a.getSexo());
                areaSaida.setText("ATIVIDADE SALVA!\n\nSUGESTÃO DE CRONOGRAMA:\n" + divisao);

                JOptionPane.showMessageDialog(this, "Atividade Adicionada com sucesso!");
                
            } catch(Exception ex) { 
                JOptionPane.showMessageDialog(this, "Erro: Preencha Séries, Repetições e Carga com números!"); 
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cl.show(cards, "Menu"));

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(areaSaida), BorderLayout.CENTER);
        
        JPanel sul = new JPanel();
        sul.add(btnAdd); 
        sul.add(btnVoltar);
        painel.add(sul, BorderLayout.SOUTH);

        return painel;
    }

    // Método auxiliar para não precisar mudar sua classe Atividade.java
    private String gerarTextoDivisao(String tipo, String sexo) {
        if(sexo.equalsIgnoreCase("masculino")){
            if(tipo.equalsIgnoreCase("superiores")){
                return "- Segunda: Peito/Tríceps\n- Quarta: Costas/Bíceps\n- Sexta: Pernas/Ombros";
            } else if (tipo.equalsIgnoreCase("inferiores")){
                return "- Terça: Quadríceps\n- Quinta: Posterior/Panturrilha";
            }
        } else { // feminino
            if(tipo.equalsIgnoreCase("inferiores")){
                return "- Segunda: Glúteos\n- Quarta: Pernas Completo\n- Sexta: Posteriores";
            } else if (tipo.equalsIgnoreCase("superiores")){
                return "- Terça: Membros Superiores\n- Quinta: Cardio/Abdômen";
            }
        }
        return "- Cronograma de Cardio: Diário (30-40 min)";
    }

    // --- 5. TELA: MENSALIDADES ---
    private JPanel criarTelaMensalidades() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Pagamento"));

        JTextField txtMatricula = new JTextField();
        String[] diasOpcoes = {"1 (Diária)", "3 Dias", "5 Dias", "Padrão"};
        JComboBox<String> comboDias = new JComboBox<>(diasOpcoes);
        JCheckBox chkInstrutor = new JCheckBox("Com Instrutor");

        form.add(new JLabel("Matrícula:")); form.add(txtMatricula);
        form.add(new JLabel("Dias:")); form.add(comboDias);
        form.add(new JLabel("Instrutor:")); form.add(chkInstrutor);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> {
            try {
                int mat = Integer.parseInt(txtMatricula.getText());
                Aluno aluno = null;
                for(Pessoa p : membros) {
                    if(p instanceof Aluno a && a.getMatricula() == mat) {
                        aluno = a;
                        break;
                    }
                }

                if (aluno != null) {
                    // Criando a mensalidade com base no aluno encontrado
                    Mensalidade novaM = new Mensalidade(aluno, "10/01/2026", 3);
                    novaM.setTemInstrutor(chkInstrutor.isSelected());
                    novaM.definirValorPlano();
                    novaM.efetuarPagamento();
                    mensalidades.add(novaM);
                    autoSalvar();
                    JOptionPane.showMessageDialog(this, "Pagamento de R$" + novaM.getValor() + " registrado!");
                    txtMatricula.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
                }
            } catch (Exception ex) { 
                JOptionPane.showMessageDialog(this, "Erro: Digite uma matrícula válida!"); 
            }
        });
        form.add(btnConfirmar);

        JTextArea log = new JTextArea();
        log.setEditable(false);
        
        // --- SEÇÃO DO BOTÃO VOLTAR PEQUENO ---
        JButton btnVoltar = new JButton("Voltar");
        // Define o tamanho exato do botão (80 de largura por 25 de altura)
        btnVoltar.setPreferredSize(new Dimension(80, 25)); 
        btnVoltar.addActionListener(e -> cl.show(cards, "Menu"));

        // Painel auxiliar com FlowLayout para o botão não esticar
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        painelBotao.add(btnVoltar);
        // -------------------------------------

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(log), BorderLayout.CENTER);
        // Adicionamos o painel que contém o botão pequeno no rodapé
        painel.add(painelBotao, BorderLayout.SOUTH); 

        return painel;
    }

    // --- LOGIN ---
    private boolean realizarLoginGUI() {
        JTextField u = new JTextField();
        JPasswordField p = new JPasswordField();
        Object[] msg = {"Usuário:", u, "Senha:", p};
        int res = JOptionPane.showConfirmDialog(this, msg, "Login", JOptionPane.OK_CANCEL_OPTION);
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