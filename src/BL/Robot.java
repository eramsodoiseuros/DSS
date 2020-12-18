package BL;

import DL.Dados;

import java.util.ArrayList;
import java.util.List;

public class Robot implements Dados<Robot>{
    private String codeID;
    private Integer ordensFeitas;

    private Boolean ativo;
    private Integer posX;
    private Integer posY;

    protected Robot(String codeID) {
        this.codeID= codeID;
        this.ativo = false;
        this.ordensFeitas = 0;
        this.posX = 0;
        this.posY = 1;
    }

    protected Robot(Robot r){
        this.codeID = r.getCodeID();
        this.ativo = r.getAtivo();
        this.ordensFeitas = r.getOrdensFeitas();
        this.posX = r.getPosX();
        this.posY = r.getPosY();
    }

    public Robot(){
    }

    public Robot(List<String> l) {
        this.codeID = l.get(0);
        this.ordensFeitas = Integer.parseInt(l.get(1));
        this.ativo = false;
        this.posX = 0;
        this.posY = 1;
    }

    public String getCodeID() {
        return codeID;
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

    protected void setCodeID(String id) {
        this.codeID = id;
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
                "codeId='" + codeID + '\'' +
                ", ativo=" + ativo +
                ", ordensFeitas=" + ordensFeitas +
                '}';
    }

    public boolean equals(Robot robo){
        return this.getCodeID().equals(robo.getCodeID());
    }

    public Robot clone(){
        return new Robot(this);
    }

    public Dados<Robot> fromRow(final List<String> l) {
        return new Robot(l);
    }

    public List<String> toRow() {
        List<String> r = new ArrayList<>();
        r.add(this.codeID);
        r.add(this.ordensFeitas.toString());
        return r;
    }

}
