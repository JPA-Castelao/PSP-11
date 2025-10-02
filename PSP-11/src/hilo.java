import org.w3c.dom.xpath.XPathResult;

public class hilo extends Thread {
    private int contadorHijos;
    private Thread hiloPadre;

    public hilo(String nombre, int contadorActual) {
        super(nombre);
        this.contadorHijos = contadorActual;
    }

    public void ciclo() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Soy el " + currentThread().getName() + " iteracion nº" + i);
            try {
                sleep(((int) Math.random() * (1000 - 600 + 1)) * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void run() {
        this.contadorHijos++;
        if (this.contadorHijos < 6) {
            hilo hiloHijo = new hilo("Hilo hijo nº" + this.contadorHijos, this.contadorHijos);
            hiloHijo.start();
            ciclo();
            try {
                hiloHijo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            ciclo();
            System.out.println("Acabo el[" + currentThread().getName() + "]");
            System.out.println("Chau");
        }
    }


    public static void main(String[] args) {
        hilo hiloPadre = new hilo("Hilo Padre", 1);
        hiloPadre.start();


    }


}
