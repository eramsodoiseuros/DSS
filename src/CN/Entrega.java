package CN;

public class Entrega extends Pedido {

    @Override
    boolean estado() {
        this.estado = !this.estado;
        return this.estado;
    }
}
