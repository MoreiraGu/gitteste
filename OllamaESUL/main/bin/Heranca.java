
package FuncionarioHeranca;


public class Heranca {


    public static void main(String[] args) {
        Funcionario f1 = new Funcionario(1500,"12223445-5","Lincoln");
        System.out.printf("O salário do %s é %.2f\n",f1.getNome(),f1.getSalario());
        f1.AumentarSalario(20);
        System.out.printf("O salário do %s é %.2f\n",f1.getNome(),f1.getSalario());
    }
    
}
