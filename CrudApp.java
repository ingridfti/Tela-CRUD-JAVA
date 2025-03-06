package br.com.crudcidades;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CrudApp extends JFrame {
	private CidadeDAO cidadeDAO;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
	private JTextField searchField;

	public CrudApp() {
		cidadeDAO = new CidadeDAO();
		setTitle("Cadastro de Cidades");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		criarMenu();

		mainPanel = new JPanel(new BorderLayout());
		add(mainPanel);
	}

	private JPanel mainPanel;

	private void criarMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuCadastro = new JMenu("Cadastro");

		JMenuItem itemCidade = new JMenuItem("Cadastrar Cidade");
		itemCidade.addActionListener(e -> mostrarTelaCidades());

		JMenuItem itemEstado = new JMenuItem("Cadastrar Estado");
		itemEstado.addActionListener(e -> addEstado());

		menuCadastro.add(itemCidade);
		menuCadastro.add(itemEstado);
		menuBar.add(menuCadastro);

		setJMenuBar(menuBar);
	}

	private void mostrarTelaCidades() {
		mainPanel.removeAll(); // Remove o conteúdo anterior
		mainPanel.add(new CidadePainel(), BorderLayout.CENTER);
		mainPanel.revalidate(); // Revalida o painel
		mainPanel.repaint(); // Redesenha o painel
	}

	private void initComponents() {
		tableModel = new DefaultTableModel(new Object[] { "Código IBGE", "Nome", "Latitude", "Longitude", "Capital",
				"Código UF", "Siafi ID", "DDD", "Fuso Horário", "Nome Normalizado" }, 0);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		searchField = new JTextField(20);
		btnSearch = new JButton("Pesquisar");
		btnSearch.addActionListener(e -> searchCidade());

		btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(e -> addCidade());

		btnUpdate = new JButton("Atualizar");
		btnUpdate.addActionListener(e -> updateCidade());

		btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(e -> deleteCidade());

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(new JLabel("Pesquisar:"));
		panelButtons.add(searchField);
		panelButtons.add(btnSearch);
		panelButtons.add(btnAdd);
		panelButtons.add(btnUpdate);
		panelButtons.add(btnDelete);

		add(scrollPane, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);
	}

	private void addEstado() {
	    CadastroEstadoDialog dialog = new CadastroEstadoDialog(this);
	    dialog.setVisible(true);

	    // 🔹 Se o usuário cadastrou um novo estado, recarregar a lista sem remover a tabela
	    if (dialog.isConfirmado() && mainPanel.getComponentCount() > 0 && mainPanel.getComponent(0) instanceof EstadoPainel) {
	        ((EstadoPainel) mainPanel.getComponent(0)).loadEstados();
	    }
	}


	private void addCidade() {
		CadastroCidadeDialog dialog = new CadastroCidadeDialog(this);
		dialog.setVisible(true);
		loadCidades();
	}

	private void loadCidades() {
		List<Cidade> cidades = cidadeDAO.getAllCidades();
		atualizarTabela(cidades);
	}

	private void searchCidade() {
		String nomePesquisa = searchField.getText().trim();
		if (nomePesquisa.isEmpty()) {
			loadCidades();
			return;
		}
		List<Cidade> cidadesFiltradas = cidadeDAO.getCidadesPorNome(nomePesquisa);
		atualizarTabela(cidadesFiltradas);
	}

	private void atualizarTabela(List<Cidade> cidades) {
		tableModel.setRowCount(0);
		for (Cidade c : cidades) {
			String nomeEstado = cidadeDAO.getNomeEstado(c.getCodigoUf());
			tableModel.addRow(
					new Object[] { c.getCodigoIbge(), c.getNome(), c.getLatitude(), c.getLongitude(), c.isCapital(),
							nomeEstado, c.getSiafiId(), c.getDdd(), c.getFusoHorario(), c.getNomeNormalizado() });
		}
	}

	public JPanel getMainPanel() {
	    return mainPanel;
	}

	public void restaurarMenu() {
	    getContentPane().removeAll(); // Remove o conteúdo atual
	    criarMenu(); // Recria o menu
	    mainPanel = new JPanel(new BorderLayout()); // Recria o painel principal
	    add(mainPanel, BorderLayout.CENTER);
	    revalidate();
	    repaint();
	}

	private void updateCidade() {
		JOptionPane.showMessageDialog(this, "Função de atualização ainda não implementada.");
	}

	private void deleteCidade() {
		JOptionPane.showMessageDialog(this, "Função de exclusão ainda não implementada.");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new CrudApp().setVisible(true));
	}
}
