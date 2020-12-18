public void run() throws ExecutionException, InterruptedException {
    while (true) {
        while(listar_requisicoes().size() + listar_entregas().size() > 0){
            UI.notifica("bro im working i swear " + "||R:" + listar_requisicoes().size() + "||E" + listar_entregas().size());
            //ver se algum dos robôs terminou o que está em progresso
            robosEmProgresso.removeIf(Future::isDone);

            while (inventario.size() > 0 && robotsDisponiveis().size() > 0){
                for (Palete p : inventario.values()){
                    if (!p.isArmazenado() && mapa[1][0] == 0) {
                        Future<Robot> futureTask = threadpool.submit(() -> recolherPalete(p, getAvailable(robots) ));
                        inventario.remove(p.getCodID());
                        robosEmProgresso.add(futureTask);
                        break;
                    }
                }
            }
        }
        //maybe fazer um sleepzito aqui so we don't check robot constantly
    }
}

//so pode ser chamado se ouver espaço
public Robot recolherPalete(Palete p, Robot robot) {

    Point destino = getEspacoLivre();

    boolean iniciado = false;
    boolean entregou = false;

    while (!iniciado) iniciado = robot.startWork(this.mapa);
    while (!entregou) entregou = robot.andaParaPalete(mapa, destino.x, destino.y);

    UI.notifica("O robot: " + robot.getCodeID() + " acabou de movimentar a Palete " + p.getCodID() + " e vai cessar atividade.");

    p.setArmazenado(true);
    p.setLocalizacao(destino);
    inventario.add(p);
    mapa[destino.x][destino.y]++;
    if (mapa[destino.x][destino.y] == 10) mapa[destino.x][destino.y] = 3;
    recolherRobo(robot);

    return robot;
}

public Robot entregarPalete (Palete p, Robot r){
    Point destino = p.getLocalizacao();
    boolean temPalete = false;
    boolean iniciado = false;
    boolean entregouPalete = false;

    while (!iniciado) iniciado = r.startWork(this.mapa);
    while(!temPalete) temPalete = r.andaParaPalete(mapa, destino.x, destino.y);

    inventario.remove(p.getCodID());

    while(!entregouPalete) entregouPalete = r.entregaPalete(mapa);
    recolherRobo(r);

    return r;
}

//robo retorna a posição defaul e é desligado
public void recolherRobo(Robot robot){
    boolean voltou = false;
    while (!voltou) voltou = robot.takeBreak(mapa);
}

public Point getEspacoLivre(){
    int i = 2;
    Point pointReturn = new Point();

    for(; i<7; i++){
        if ((mapa[0][i] > 3 && mapa[0][i] <10) || mapa[0][i] == 2) {
            pointReturn.setLocation(0,i);
            return pointReturn;
        }
    }

    for(i = 2; i<7; i++){
        if ((mapa[0][i] > 3 && mapa[0][i] <10) || mapa[0][i] == 2) {
            pointReturn.setLocation(5,i);
            return pointReturn;
        }
    }

    return pointReturn;
}

public void shutdown(){
    threadpool.shutdown();
}
