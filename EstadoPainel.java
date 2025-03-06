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
    private JTextField searchField;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnVoltar;

    public EstadoPainel() {
        estadoDAO = new EstadoDAO();
        setLayout(new BorderLayout());

        initComponents();
        loadEstados();
    }

    private void initComponents() {
        // 🔹 Configuração da tabela
        tableModel = new DefaultTableModel(new Object[]{
            "Código UF", "Nome", "Sigla"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 🔹 Criando barra de pesquisa
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        btnSearch = new JButton("Pesquisar");
        btnSearch.addActionListener(e -> searchEstado());

        searchPanel.add(new JLabel("Pesquisar:"));
        searchPanel.add(searchField);
        searchPanel.add(btnSearch);

        // 🔹 Criando painel de botões principais
        JPanel panelButtons = new JPanel(new FlowLayout());

        // Botão para abrir a tela de cadastro
        btnAdd = new JButton("Adicionar Estado");
        btnAdd.addActionListener(e -> abrirCadastroEstado());
        panelButtons.add(btnAdd);

        // Botão de atualizar
        btnUpdate = new JButton("Atualizar");
        btnUpdate.addActionListener(e -> loadEstados());
        panelButtons.add(btnUpdate);

        // Botão de deletar
        btnDelete = new JButton("Deletar");
        panelButtons.add(btnDelete);

        // Botão para voltar ao menu
        btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> voltarAoMenu());
        panelButtons.add(btnVoltar);

        // 🔹 Adicionando componentes na tela
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void loadEstados() {
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

    private void abrirCadastroEstado() {
        CadastroEstadoDialog dialog = new CadastroEstadoDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);

        // 🔹 Atualiza a lista após cadastrar um estado
        if (dialog.isConfirmado()) {
            loadEstados();
        }
    }

    private void voltarAoMenu() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame instanceof CrudApp) {
            ((CrudApp) frame).restaurarMenu(); // 🔹 Chama o método correto para restaurar o menu
        }
    }
}
