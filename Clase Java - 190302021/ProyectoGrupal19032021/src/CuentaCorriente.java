
    public class CuentaCorriente {

        public double saldo;


        public CuentaCorriente cuentaCorriente(double saldo){
            this.saldo= saldo;
            return  cuentaCorriente(saldo);

        }

        public void cuentaCorriente(){}

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }

        public double ingreso(double ingreso){
            this.saldo = saldo + ingreso;
            return saldo;
        }
        public double egreso(double egreso){
            this.saldo = saldo- egreso;
            return saldo;

        }
        public double reintegro(double reintegro){
            saldo = saldo + reintegro;
            return saldo;
        }
        public double transferencia(double transferencia){
            saldo = saldo - transferencia;
            return saldo;

        }
    }

