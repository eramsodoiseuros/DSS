package CN;

public class Robot {
    private String codeId;
    private Boolean ativo;
    private Integer ordensFeitas;

    public Robot(String codeId) {
        this.codeId = codeId;
        this.ativo = false;
        this.ordensFeitas = 0;
    }

    public Robot(Robot r){
        this.codeId = r.getCodeId();
        this.ativo = r.getAtivo();
        this.ordensFeitas = r.getOrdensFeitas();
    }

    private boolean notificaRecolha(String codeIdRequesicao, String codeIdPalete){
        return true;//por fazer
    }

    private boolean notificaEntrega(String codeIdEntrega, String codeIdPalete){
        return true;//por fazer
    }

    private void ordem(){}
    private void estado(){}



    public String getCodeId() {
        return codeId;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getOrdensFeitas() {
        return ordensFeitas;
    }

    public void setId(String id) {
        this.codeId = id;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setOrdensFeitas(Integer ordensFeitas) {
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
