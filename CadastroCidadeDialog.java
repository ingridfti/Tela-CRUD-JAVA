package br.com.crudcidades;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CadastroCidadeDialog extends JDialog {
	private JTextField codigoIbgeField, nomeField, latitudeField, longitudeField, siafiIdField, dddField,
			fusoHorarioField, nomeNormalizadoField;
	private JCheckBox capitalCheckBox;
	private JComboBox<String> estadoComboBox;
	private boolean confirmado = false;
	private CidadeDAO cidadeDAO;

	public CadastroCidadeDialog(JFrame parent) {
		super(parent, "Cadastro de Cidade", true);
		cidadeDAO = new CidadeDAO(); // Criar conexão com banco
		setLayout(new GridLayout(11, 2, 5, 5));

		add(new JLabel("Código IBGE:"));
		codigoIbgeField = new JTextField();
		add(codigoIbgeField);

		add(new JLabel("Nome:"));
		nomeField = new JTextField();
		add(nomeField);

		add(new JLabel("Latitude:"));
		latitudeField = new JTextField();
		add(latitudeField);

		add(new JLabel("Longitude:"));
		longitudeField = new JTextField();
		add(longitudeField);

		add(new JLabel("Capital:"));
		capitalCheckBox = new JCheckBox();
		add(capitalCheckBox);

		// 🔽 Adicionando ComboBox para selecionar Estado 🔽
		add(new JLabel("Estado:"));
		estadoComboBox = new JComboBox<>();
		carregarEstados(); // Método para preencher os estados
		add(estadoComboBox);

		add(new JLabel("Siafi ID:"));
		siafiIdField = new JTextField();
		add(siafiIdField);

		add(new JLabel("DDD:"));
		dddField = new JTextField();
		add(dddField);

		add(new JLabel("Fuso Horário:"));
		fusoHorarioField = new JTextField();
		add(fusoHorarioField);

		add(new JLabel("Nome Normalizado:"));
		nomeNormalizadoField = new JTextField();
		add(nomeNormalizadoField);

		// Painel de botões
		JPanel buttonPanel = new JPanel();
		JButton confirmarButton = new JButton("Salvar");
		JButton cancelarButton = new JButton("Cancelar");

		confirmarButton.addActionListener(e -> {
			confirmado = true;
			setVisible(false);
		});

		cancelarButton.addActionListener(e -> setVisible(false));

		buttonPanel.add(confirmarButton);
		buttonPanel.add(cancelarButton);
		add(buttonPanel);

		pack();
		setLocationRelativeTo(parent);
	}

	private void carregarEstados() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> estados = estadoDAO.getAllEstados();
		for (Estado estado : estados) {
			estadoComboBox.addItem(estado.getCodigoUf() + " - " + estado.getNome());
		}
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public Cidade getCidade() {
		Cidade cidade = new Cidade();
		cidade.setCodigoIbge(Integer.parseInt(codigoIbgeField.getText()));
		cidade.setNome(nomeField.getText());
		cidade.setLatitude(Double.parseDouble(latitudeField.getText()));
		cidade.setLongitude(Double.parseDouble(longitudeField.getText()));
		cidade.setCapital(capitalCheckBox.isSelected());

		// Extrai o código do estado selecionado no ComboBox
		String estadoSelecionado = (String) estadoComboBox.getSelectedItem();
		int codigoUf = Integer.parseInt(estadoSelecionado.split(" - ")[0]); // Pega apenas o código numérico
		cidade.setCodigoUf(codigoUf);

		cidade.setSiafiId(siafiIdField.getText());
		cidade.setDdd(Integer.parseInt(dddField.getText()));
		cidade.setFusoHorario(fusoHorarioField.getText());
		cidade.setNomeNormalizado(nomeNormalizadoField.getText());

		return cidade;
	}
}
