package br.com.crudcidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CidadePainel extends JPanel {
    private static final long serialVersionUID = 1L; // ✅ Adicionado para evitar warnings

    private CidadeDAO cidadeDAO;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
    private JTextField searchField;

    // 🔹 CONSTRUTOR
    public CidadePainel() {
        cidadeDAO = new CidadeDAO();
        setLayout(new BorderLayout());

        initComponents();  // ⚡ Configura os componentes visuais
        loadCidades();     // ⚡ Carrega os dados da cidade
    }

    private void initComponents() {
        tableModel = new DefaultTableModel(new Object[]{
            "Código IBGE", "Nome", "Latitude", "Longitude", "Capital",
            "Código UF", "Siafi ID", "DDD", "Fuso Horário", "Nome Normalizado"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        searchField = new JTextField(20);
        btnSearch = new JButton("Pesquisar");
        btnSearch.addActionListener(e -> searchCidade());

        btnAdd = new JButton("Adicionar Cidade");
        btnAdd.addActionListener(e -> addCidade());

        btnUpdate = new JButton("Atualizar");
        btnUpdate.addActionListener(e -> updateCidade());

        btnDelete = new JButton("Deletar");
        btnDelete.addActionListener(e -> deleteCidade());

        // 🔹 Adicionando botão "Voltar ao Menu"
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> voltarAoMenu());

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(new JLabel("Pesquisar:"));
        panelButtons.add(searchField);
        panelButtons.add(btnSearch);
        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnVoltar); // Adicionando botão no painel

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    // 🔹 Criando o método para voltar ao menu principal
    private void voltarAoMenu() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame instanceof CrudApp) {
            ((CrudApp) frame).restaurarMenu(); // 🔹 Chama o método correto para restaurar o menu
        }
    }




    // 🔹 CARREGA OS DADOS DAS CIDADES
    private void loadCidades() {
        List<Cidade> cidades = cidadeDAO.getAllCidades();
        atualizarTabela(cidades);
    }

    // 🔹 ATUALIZA A TABELA
    private void atualizarTabela(List<Cidade> cidades) {
        tableModel.setRowCount(0);
        for (Cidade c : cidades) {
            tableModel.addRow(new Object[]{
                c.getCodigoIbge(), c.getNome(), c.getLatitude(), c.getLongitude(), c.isCapital(),
                c.getCodigoUf(), c.getSiafiId(), c.getDdd(), c.getFusoHorario(), c.getNomeNormalizado()
            });
        }
    }

    // 🔹 PESQUISA CIDADE
    private void searchCidade() {
        String nomePesquisa = searchField.getText().trim();
        if (nomePesquisa.isEmpty()) {
            loadCidades();
            return;
        }
        List<Cidade> cidadesFiltradas = cidadeDAO.getCidadesPorNome(nomePesquisa);
        atualizarTabela(cidadesFiltradas);
    }

    // 🔹 ADICIONA UMA NOVA CIDADE
    private void addCidade() {
        CadastroCidadeDialog dialog = new CadastroCidadeDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        loadCidades();
    }

    // 🔹 ATUALIZAÇÃO (AINDA NÃO IMPLEMENTADO)
    private void updateCidade() {
        JOptionPane.showMessageDialog(this, "Função de atualização ainda não implementada.");
    }

    // 🔹 EXCLUSÃO (AINDA NÃO IMPLEMENTADO)
    private void deleteCidade() {
        JOptionPane.showMessageDialog(this, "Função de exclusão ainda não implementada.");
    }
}
