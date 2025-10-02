public class hilo extends Thread {
    private int contadorHijos;

    public hilo(String nombre, int contadorActual) {
        super(nombre);
        this.contadorHijos = contadorActual;
    }

    @Override
    public void run() {
        System.out.println("Hola soy el " + currentThread().getName());
        if (this.contadorHijos < 5) {
            this.contadorHijos++;
            hilo hiloHijo = new hilo("Hilo hijo nº" + this.contadorHijos, this.contadorHijos);
            hiloHijo.start();
            try {
                hiloHijo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Chau");
        }


    }


    public static void main(String[] args) {
        hilo hiloPadre = new hilo("Hilo Padre", 0);
        hiloPadre.start();


    }


}
