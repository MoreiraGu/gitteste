
package EmpresaHeranca;


public class Gerente extends Funcionario {
    private int senha;

    public Gerente(int senha, String nome, String CPF, double salario) {
        super(nome, CPF, salario);
        this.senha = senha;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
    public boolean autentica(int s){
        if (senha == s){
            System.out.println("\nSenha correta!");
            return true;
        }
        else{
            System.out.println("\nSenha incorreta!");
            return false;
        }
    }
}
