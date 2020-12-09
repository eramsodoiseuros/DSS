package BL;

public class Robot {
    private String codeId;
    private Boolean ativo;
    private Integer ordensFeitas;

    protected Robot(String codeId) {
        this.codeId = codeId;
        this.ativo = false;
        this.ordensFeitas = 0;
    }

    protected Robot(Robot r){
        this.codeId = r.getCodeId();
        this.ativo = r.getAtivo();
        this.ordensFeitas = r.getOrdensFeitas();
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

    boolean doWork(int[][] mapa){
        boolean did_it = false;
        //check first row
            // can or cannot work
        // check second row
            // same shit
        return did_it;
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
