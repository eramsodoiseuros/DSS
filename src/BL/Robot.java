package BL;

public class Robot {
    private String codeId;
    private Boolean ativo;
    private Integer ordensFeitas;
    private Integer posX;
    private Integer posY;

    protected Robot(String codeId) {
        this.codeId = codeId;
        this.ativo = false;
        this.ordensFeitas = 0;
        this.posX = 0;
        this.posY = 1;
    }

    protected Robot(Robot r){
        this.codeId = r.getCodeId();
        this.ativo = r.getAtivo();
        this.ordensFeitas = r.getOrdensFeitas();
        this.posX = r.getPosX();
        this.posY = r.getPosY();
    }

    protected boolean notificaRecolha(String codeIdRequesicao, String codeIdPalete){
        return true;//por fazer
    }

    protected boolean notificaEntrega(String codeIdEntrega, String codeIdPalete){
        return true;//por fazer
    }

    protected void ordem(){

    }
    protected void estado(){

    }

    //momento em que o robo se encontra ligado até pegar/recolher na palete na pos X Y
    public boolean andaParaPalete(int[][] mapa, int x, int y){

        boolean did_it  = false;

        if ( ((x == 0 && getPosX() == 1) || (x == 5 && getPosX() == 4)) && getPosY() == y) did_it = true;
        else rotate(mapa);

        return did_it;
    }

    //do momento em que o robo tem uma palete e chega a area de carregamento
    public boolean entregaPalete(int[][] mapa){

        boolean did_it  = false;
        Integer lastX   = getPosX();
        Integer lastY   = getPosY();

        if (lastX == 2 && lastY == 6) did_it = true;
        else rotate(mapa);

        return did_it;
    }

    //retorna o robo para a posição defaul e desliga o moço
    public boolean takeBreak(int[][] mapa){

        boolean did_it  = false;

        if (getPosX() == 1 && getPosY() == 1){
            moveState(mapa, 0, 1);
            setAtivo(false);
            did_it = true;
        } else rotate(mapa);

        return did_it;
    }

    //robo da um passo clockwise
    private void rotate(int[][] mapa){

        Integer lastX   = getPosX();
        Integer lastY   = getPosY();

        if (lastX == 1 && lastY < 6) moveState(mapa, lastX, lastY+1);

        else if (lastY == 6 && lastX < 4) moveState(mapa, lastX+1, lastY);

        else if (lastX == 4 && lastY > 1) moveState(mapa, lastX, lastY-1);

        else if (lastY == 1 && lastX > 1) moveState(mapa, lastX-1, lastY);
    }

    //move o robot para a proxima posição, se nao estiver ocupada
    private void moveState(int[][] mapa, Integer newPosX, Integer newPosY){
        if (mapa[newPosX][newPosY] != 1){
            mapa[getPosX()][getPosY()]  = 0;
            mapa[newPosX][newPosY]      = 1;
            setPos(newPosX,newPosY);
        }
    }

    //liga o robo para começar a trabalhar, se a posição inicial nao estiver ocupada
    protected boolean startWork(int[][] mapa){
        if (mapa[1][1]!= 1){
            setAtivo(true);
            moveState(mapa, 1,1);
            return true;
        }
        return false;
    }

    protected String getCodeId() {
        return codeId;
    }

    protected Boolean getAtivo() {
        return ativo;
    }

    protected Integer getOrdensFeitas() {
        return ordensFeitas;
    }

    protected Integer getPosX() { return posX; }

    protected Integer getPosY() { return posY; }

    protected void setPosX(Integer x) { this.posX = x; }

    protected void setPosY(Integer y) { this.posY = y; }

    protected void setPos(Integer x, Integer y) {
        setPosX(x);
        setPosY(y);
    }

    protected void setId(String id) {
        this.codeId = id;
    }

    protected void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    protected void setOrdensFeitas(Integer ordensFeitas) {
        this.ordensFeitas = ordensFeitas;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "codeId='" + codeId + '\'' +
                ", ativo=" + ativo +
                ", ordensFeitas=" + ordensFeitas +
                '}';
    }

    public boolean equals(Robot robo){
        return this.getCodeId().equals(robo.getCodeId());
    }

    public Robot clone(){
        return new Robot(this);
    }
}
