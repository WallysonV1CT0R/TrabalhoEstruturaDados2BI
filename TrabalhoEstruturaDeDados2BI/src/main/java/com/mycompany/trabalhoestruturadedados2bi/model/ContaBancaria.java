package com.mycompany.trabalhoestruturadedados2bi.model;

import javax.swing.JOptionPane;

public class ContaBancaria {

    private int numeroConta;
    private String nomeTitular;
    private double saldo;

    public ContaBancaria(int numeroConta, String nomeTitular, double saldo) {
        this.numeroConta = numeroConta;
        this.nomeTitular = nomeTitular;
        this.saldo = saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso.\n"
                + "Novo saldo: " + saldo);
    }
     public void realizarSaque(double valor) {
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null, "Valor inválido para saque.");
            return;
        }

        if (saldo >= valor) {
            saldo -= valor;
            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso.\n"
                    + "Novo saldo: " + saldo);
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar o saque.");
        }
    }

    public int compareTo(ContaBancaria outraConta) {
        return Integer.compare(this.numeroConta, outraConta.numeroConta);
    }

    @Override
    public String toString() {
        String mensagem = "Conta: " + numeroConta + "\nTitular: " + nomeTitular + "\nSaldo: " + saldo;
        JOptionPane.showMessageDialog(null, mensagem);
        return "Voltando ao menu principal";
    }
}