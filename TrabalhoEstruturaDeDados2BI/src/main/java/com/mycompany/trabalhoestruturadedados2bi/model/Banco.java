package com.mycompany.trabalhoestruturadedados2bi.model;

import com.mycompany.trabalhoestruturadedados2bi.model.ContaBancaria;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Banco {
    private ContaBancaria[] contas;
    private int numContas;
    private static final int TAMANHO_MAXIMO = 100;

    public Banco() {
        contas = new ContaBancaria[TAMANHO_MAXIMO];
        numContas = 0;
    }

    public void executar() {
        int opcao;

        do {
            opcao = exibirMenu();

            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    ordenarContas();
                    exibirContas();
                    break;
                case 3:
                    fazerDeposito();
                    break;
                case 4:
                    realizarSaque();
                    break;
                case 5:
                    double saldoTotal = calcularSaldoTotal();
                    JOptionPane.showMessageDialog(null,
                        "Saldo total das contas: " + saldoTotal);
                    break;
                case 6:
                    verificarSaldoNegativo();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null,
                            "Saindo do programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private int exibirMenu() {
        String menu = "Banco Do Brasil\n"
                + "1 - Cadastrar uma nova conta\n"
                + "2 - Exibir contas bancárias\n"
                + "3 - Depositar\n"
                + "4 - Realizar saque\n"
                + "5 - Calcular saldo total\n"
                + "6 - Verificar saldo negativo\n"
                + "0 - Sair\n";

        return Integer.parseInt(JOptionPane.showInputDialog
                               (null, menu));
    }

    private void cadastrarConta() {
        if (numContas >= TAMANHO_MAXIMO) {
            JOptionPane.showMessageDialog(null,
                    "Limite máximo de contas atingido.");
            return;
        }

        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog
        ("Digite o número da conta: "));
        String nomeTitular = JOptionPane.showInputDialog
        ("Digite o nome do titular: ");
        double saldoInicial = Double.parseDouble(JOptionPane.showInputDialog
        ("Digite o saldo inicial: "));

        ContaBancaria novaConta = new ContaBancaria(numeroConta,
                                nomeTitular, saldoInicial);
        contas[numContas] = novaConta;
        numContas++;

        JOptionPane.showMessageDialog(null,
                "Conta cadastrada com sucesso!");
    }

    private void ordenarContas() {
        Arrays.sort(contas, 0, numContas);
        JOptionPane.showMessageDialog(null,
                "Contas ordenadas com sucesso!");
    }

    private void exibirContas() {
      
        if (numContas == 0) {
            JOptionPane.showMessageDialog(null,
                    "Nenhuma conta cadastrada.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < numContas; i++) {
            sb.append(contas[i].toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void fazerDeposito() {
        if (numContas == 0) {
            JOptionPane.showMessageDialog(null,
                    "Nenhuma conta cadastrada.");
            return;
        }

        String busca = JOptionPane.showInputDialog("Digite o número"
                + " da conta ou o nome do titular para realizar o depósito: ");

        ContaBancaria contaEncontrada = null;
        for (int i = 0; i < numContas; i++) {
            ContaBancaria conta = contas[i];
            if (String.valueOf(conta.getNumeroConta()).equals(busca)
                    || conta.getNomeTitular().equals(busca)) {
                contaEncontrada = conta;
                break;
            }
        }

        if (contaEncontrada != null) {
            double valorDeposito = Double.parseDouble
        (JOptionPane.showInputDialog("Digite o valor do depósito: "));
            
            contaEncontrada.depositar(valorDeposito);
            
            JOptionPane.showMessageDialog(null,
                    "Depósito realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Conta não encontrada.");
        }
    }
    private void realizarSaque() {
        ordenarContas();

        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog
        ("Informe o número da conta: "));
        ContaBancaria conta = buscarConta(Integer.toString(numeroConta));

        if (conta != null) {
            double valor = Double.parseDouble(JOptionPane.showInputDialog
            ("Valor do saque: "));
            conta.realizarSaque(valor);
            JOptionPane.showMessageDialog(null,
                    "Saque realizado com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Conta não encontrada.");
        }
    }

    private double calcularSaldoTotal() {
        return calcularSaldoTotalRecursivo(0, 0);
    }

    private double calcularSaldoTotalRecursivo(int indice, double saldoTotal) {
        if (indice >= numContas) {
            return saldoTotal;
        }

        saldoTotal += contas[indice].getSaldo();

        return calcularSaldoTotalRecursivo(indice + 1, saldoTotal);
    }

    private void verificarSaldoNegativo() {
        verificarSaldoNegativoRecursivo(0);
    }

    private void verificarSaldoNegativoRecursivo(int indice) {
        if (indice >= numContas) {
            return;
        }

        if (contas[indice].getSaldo() < 0) {
            JOptionPane.showMessageDialog(null, "Conta " + 
                contas[indice].getNumeroConta() + " possui saldo negativo: "
                    + contas[indice].getSaldo());
        }

        verificarSaldoNegativoRecursivo(indice + 1);
    }

    private ContaBancaria buscarConta(String busca) {
        for (int i = 0; i < numContas; i++) {
            if (String.valueOf(contas[i].getNumeroConta()).equals
            (busca)
                    || contas[i].getNomeTitular().equalsIgnoreCase
            (busca)) {
                return contas[i];
            }
        }
        return null;
    }
}