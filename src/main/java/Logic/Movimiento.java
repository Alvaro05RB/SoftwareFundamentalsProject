package Logic;

import es.upm.etsisi.fis.model.IMovimiento;

public class Movimiento implements IMovimiento {
    private int fila;
    private int columna;
    private long time;
    private long partidaId;

    public Movimiento(){
        this.fila = 0;
        this.columna = 0;
        this.time = 0;
        this.partidaId = 0;
    }

    @Override
    public IMovimiento cloneMovimiento() {
        Movimiento movimiento = new Movimiento();
        movimiento.setFila(this.fila);
        movimiento.setColumna(this.columna);
        movimiento.setTime(this.time);
        movimiento.setPartidaId(this.partidaId);
        return movimiento;
    }

    @Override
    public void setPartidaId(Long aLong) {
     this.partidaId = aLong;
    }

    @Override
    public void setFila(int i) {
        this.fila = i;
    }

    @Override
    public void setColumna(int i) {
        this.columna = i;
    }

    @Override
    public void setTime(long l) {
        this.time = l;
    }

    //Los getters se usan para las pruebas unitarias
    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public long getPartidaId() {
        return partidaId;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "["+this.fila+","+this.columna+","+this.time+"]";
    }
}
