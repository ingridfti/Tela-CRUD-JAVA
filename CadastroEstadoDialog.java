package br.com.crudcidades;

import javax.swing.*;
import java.awt.*;

public class CadastroEstadoDialog extends JDialog {
    private JTextField codigoUfField, nomeField, siglaField;
    private boolean confirmado = false;
    private EstadoDAO estadoDAO;

    public CadastroEstadoDialog(JFrame parent) {
        super(parent, "Cadastro de Estado", true);
        estadoDAO = new EstadoDAO();
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Código UF:"));
        codigoUfField = new JTextField();
        add(codigoUfField);

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Sigla:"));
        siglaField = new JTextField();
        add(siglaField);

        // 🔹 Botões de salvar e cancelar
        JPanel buttonPanel = new JPanel();
        JButton salvarButton = new JButton("Salvar");
        JButton cancelarButton = new JButton("Cancelar");

        salvarButton.addActionListener(e -> salvarEstado());
        cancelarButton.addActionListener(e -> setVisible(false));

        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);
        add(buttonPanel);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent); // 🔹 Centraliza a janela
    }

    private void salvarEstado() {
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
                confirmado = true;
                setVisible(false); // 🔹 Fecha a janela após salvar
            } else {
                JOptionPane.showMessageDialog(this, "❌ Erro ao cadastrar estado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠ Código UF deve ser um número!");
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }
}
