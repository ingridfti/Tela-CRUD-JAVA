package br.com.crudcidades;

import java.util.List;
import java.util.Scanner;

public class MenuCadastro {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EstadoDAO estadoDAO = new EstadoDAO();
    private static final CidadeDAO cidadeDAO = new CidadeDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU DE CADASTRO ---");
            System.out.println("1. Cadastrar Estado");
            System.out.println("2. Cadastrar Cidade");
            System.out.println("3. Listar Estados");
            System.out.println("4. Listar Cidades");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarEstado();
                    break;
                case 2:
                    cadastrarCidade();
                    break;
                case 3:
                    listarEstados();
                    break;
                case 4:
                    listarCidades();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void cadastrarEstado() {
        System.out.print("\nCódigo UF: ");
        int codigoUf = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        System.out.print("Nome do Estado: ");
        String nome = scanner.nextLine();

        System.out.print("Sigla (UF): ");
        String uf = scanner.nextLine().toUpperCase();

        Estado estado = new Estado(codigoUf, nome, uf);
        if (estadoDAO.createEstado(estado)) {
            System.out.println("✅ Estado cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro ao cadastrar estado.");
        }
    }

    private static void cadastrarCidade() {
        listarEstados();

        System.out.print("\nCódigo IBGE da Cidade: ");
        int codigoIbge = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        System.out.print("Nome da Cidade: ");
        String nome = scanner.nextLine();

        System.out.print("Latitude: ");
        double latitude = scanner.nextDouble();

        System.out.print("Longitude: ");
        double longitude = scanner.nextDouble();

        System.out.print("É capital? (true/false): ");
        boolean capital = scanner.nextBoolean();
        scanner.nextLine(); // Limpar buffer

        System.out.print("Código UF do Estado: ");
        int codigoUf = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        System.out.print("SIAFI ID: ");
        String siafiId = scanner.nextLine();

        System.out.print("DDD: ");
        int ddd = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        System.out.print("Fuso Horário: ");
        String fusoHorario = scanner.nextLine();

        System.out.print("Nome Normalizado: ");
        String nomeNormalizado = scanner.nextLine();

        Cidade cidade = new Cidade(codigoIbge, nome, latitude, longitude, capital, codigoUf, siafiId, ddd, fusoHorario, nomeNormalizado);
        if (cidadeDAO.createCidade(cidade)) {
            System.out.println("✅ Cidade cadastrada com sucesso!");
        } else {
            System.out.println("❌ Erro ao cadastrar cidade.");
        }
    }

    private static void listarEstados() {
        List<Estado> estados = estadoDAO.getAllEstados();
        if (estados.isEmpty()) {
            System.out.println("⚠ Nenhum estado cadastrado.");
        } else {
            System.out.println("\n--- LISTA DE ESTADOS ---");
            for (Estado e : estados) {
                System.out.println(e.getCodigoUf() + " - " + e.getNome() + " (" + e.getUf() + ")");
            }
        }
    }

    private static void listarCidades() {
        List<Cidade> cidades = cidadeDAO.getAllCidades();
        if (cidades.isEmpty()) {
            System.out.println("⚠ Nenhuma cidade cadastrada.");
        } else {
            System.out.println("\n--- LISTA DE CIDADES ---");
            for (Cidade c : cidades) {
                System.out.println(c.getCodigoIbge() + " - " + c.getNome() + " (" + c.getCodigoUf() + ")");
            }
        }
    }
}
