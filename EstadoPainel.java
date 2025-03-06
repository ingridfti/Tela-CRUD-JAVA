package br.com.crudcidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstadoPainel extends JPanel {
    private static final long serialVersionUID = 1L;

    private EstadoDAO estadoDAO;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField codigoUfField, nomeField, siglaField;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnVoltar, btnShowForm;
    private JTextField searchField;
    private JPanel formPanel;

    public EstadoPainel() {
        estadoDAO = new EstadoDAO();
        setLayout(new BorderLayout());

        initComponents();
        loadEstados();
    }

    private void initComponents() {
        // Configuração da tabela
        tableModel = new DefaultTableModel(new Object[]{
            "Código UF", "Nome", "Sigla"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 🔹 Criando o formulário mas deixando invisível
        formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Novo Estado"));
        formPanel.setVisible(false); // 🔹 O formulário começa escondido

        formPanel.add(new JLabel("Código UF:"));
        codigoUfField = new JTextField();
        formPanel.add(codigoUfField);

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Sigla:"));
        siglaField = new JTextField();
        formPanel.add(siglaField);

        // Botão de salvar estado dentro do formulário
        JButton btnSalvar = new JButton("Salvar Estado");
        btnSalvar.addActionListener(e -> addEstado());
        formPanel.add(btnSalvar);

        // 🔹 Criando barra de pesquisa
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(15);
        btnSearch = new JButton("Pesquisar");
        btnSearch.addActionListener(e -> searchEstado());

        searchPanel.add(new JLabel("Pesquisar:"));
        searchPanel.add(searchField);
        searchPanel.add(btnSearch);

        // 🔹 Criando painel de botões principais
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());

        // Botão para exibir o formulário
        btnShowForm = new JButton("Adicionar Estado");
        btnShowForm.addActionListener(e -> toggleFormVisibility());
        panelButtons.add(btnShowForm);

        // Botão de atualizar
        btnUpdate = new JButton("Atualizar");
        panelButtons.add(btnUpdate);

        // Botão de deletar
        btnDelete = new JButton("Deletar");
        panelButtons.add(btnDelete);

        // Botão para voltar ao menu
        btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> voltarAoMenu());
        panelButtons.add(btnVoltar);

        // 🔹 Adicionando componentes na tela
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.SOUTH);
        add(formPanel, BorderLayout.EAST);
    }

    void loadEstados() {
        List<Estado> estados = estadoDAO.getAllEstados();
        atualizarTabela(estados);
    }

    private void atualizarTabela(List<Estado> estados) {
        tableModel.setRowCount(0);
        for (Estado e : estados) {
            tableModel.addRow(new Object[]{e.getCodigoUf(), e.getNome(), e.getUf()});
        }
    }

    private void searchEstado() {
        String nomePesquisa = searchField.getText().trim();
        if (nomePesquisa.isEmpty()) {
            loadEstados();
            return;
        }
        JOptionPane.showMessageDialog(this, "🔍 Pesquisa ainda não implementada!");
    }

    private void addEstado() {
        try {
            int codigoUf = Integer.parseInt(codigoUfField.getText());
            String nome = nomeField.getText();
            String sigla = siglaField.getText().toUpperCase();

            if (nome.isEmpty() || sigla.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ Nome e Sigla não podem ser vazios!");
                return;
            }

            Estado estado = new Estado(codigoUf, nome, sigla);
            if (estadoDAO.createEstado(estado)) {
                JOptionPane.showMessageDialog(this, "✅ Estado cadastrado com sucesso!");
                loadEstados();
                formPanel.setVisible(false); // 🔹 Esconde o formulário após cadastrar
            } else {
                JOptionPane.showMessageDialog(this, "❌ Erro ao cadastrar estado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠ Código UF deve ser um número!");
        }
    }

    // 🔹 Alternar a visibilidade do formulário
    private void toggleFormVisibility() {
        formPanel.setVisible(!formPanel.isVisible());
    }

    private void voltarAoMenu() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame instanceof CrudApp) {
            ((CrudApp) frame).restaurarMenu(); // 🔹 Chama o método correto para restaurar o menu
        }
    }
}
