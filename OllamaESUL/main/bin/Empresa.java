
package EmpresaHeranca;


public class Empresa {
     public static void main(String[] args) {
         Gerente g1 = new Gerente(123456,"Lincoln","12345678912",4500);
         System.out.printf("Senha: %d \n Nome: %s \n CPF: %s \n Salario: %.2f",g1.getSenha(),g1.getNome(),g1.getCPF(),g1.getSalario());
         g1.autentica(1234);
     }
}
