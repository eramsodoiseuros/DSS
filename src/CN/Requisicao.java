package CN;

public class Requisicao extends Pedido {

    @Override
    boolean estado() {
        this.estado = !this.estado;
        return this.estado;
    }
}
