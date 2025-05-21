/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio3;

/**
 *
 * @author User
 */
public class Gerente extends Funcionario{
    private int senha;

    public Gerente(int senha, String nome, int cpf, double salario) {
        super(nome, cpf, salario);
        this.senha = senha;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    @Override
    public double getSalario() {
        return salario;
    }
    
    @Override
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean autentica(int senha){
        if (this.senha == senha){
            System.out.println("Acesso permitido.");
            return true;
        }else{
            System.out.println("Acesso negado!");
            return false;
        }
    }
    
    
    
}
