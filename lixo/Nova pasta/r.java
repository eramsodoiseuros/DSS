    //momento em que o robo se encontra ligado até pegar/recolher na palete na pos X Y
    public boolean andaParaPalete(Integer[][] mapa, int x, int y){

        boolean did_it  = false;

        if ( ((x == 0 && getPosX() == 1) || (x == 5 && getPosX() == 4)) && getPosY() == y) did_it = true;
        else rotate(mapa);

        return did_it;
    }

    //do momento em que o robo tem uma palete e chega a area de carregamento
    public boolean entregaPalete(Integer[][] mapa){

        boolean did_it  = false;
        Integer lastX   = getPosX();
        Integer lastY   = getPosY();

        if (lastX == 2 && lastY == 6) did_it = true;
        else rotate(mapa);

        return did_it;
    }

    //retorna o robo para a posição defaul e desliga o moço
    public boolean takeBreak(Integer[][] mapa){

        boolean did_it  = false;

        if (getPosX() == 1 && getPosY() == 1){
            moveState(mapa, 0, 1);
            setAtivo(false);
            this.ordensFeitas++; //assumir que robot acaba ordem sempre que retorna ao estado default
            did_it = true;
        } else rotate(mapa);

        return did_it;
    }

    //robo da um passo clockwise
    private void rotate(Integer[][] mapa){

        Integer lastX   = getPosX();
        Integer lastY   = getPosY();

        if (lastX == 1 && lastY < 6) moveState(mapa, lastX, lastY+1);

        else if (lastY == 6 && lastX < 4) moveState(mapa, lastX+1, lastY);

        else if (lastX == 4 && lastY > 1) moveState(mapa, lastX, lastY-1);

        else if (lastY == 1 && lastX > 1) moveState(mapa, lastX-1, lastY);
    }

    //move o robot para a proxima posição, se nao estiver ocupada
    private void moveState(Integer[][] mapa, Integer newPosX, Integer newPosY){
        if (mapa[newPosX][newPosY] != 1){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mapa[getPosX()][getPosY()]  = 0;
            mapa[newPosX][newPosY]      = 1;
            setPos(newPosX,newPosY);
            System.out.println(this.getCodeID()+": esta na pos x->"+this.posX+" y->"+this.posY);

        }
    }

    //liga o robo para começar a trabalhar, se a posição inicial nao estiver ocupada
    protected boolean startWork(Integer[][] mapa) {
        System.out.println("o senhor "+this.getCodeID()+" esta a tentar começar a trabalhar");
        if (mapa[1][1]!= 1){
            setAtivo(true);
            moveState(mapa, 1,1);
            return true;
        }
        return false;
    }
