public class Inss {

    public Double calcularInss(Double salario){
        Double salarioComTaxa = salario;

        if (salario <= 1500){
            salarioComTaxa += salario * 0.10;
        } else if(salario <= 2000){
            salarioComTaxa += salario * 0.20;
        } else if(salario <=3000){
            salarioComTaxa += salario * 0.30;
        }

        return salarioComTaxa;
    }
}
