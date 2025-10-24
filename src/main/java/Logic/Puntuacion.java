package Logic;

import es.upm.etsisi.fis.model.IPuntuacion;

public class Puntuacion implements IPuntuacion {
    private long puntuacion;
    private long id;
    public Puntuacion(){
        this.puntuacion = 0;
        this.id = 0;
    }

    @Override
    public long getPuntos() {
        return this.puntuacion;
    }

    @Override
    public IPuntuacion clonePuntuacion() {
        Puntuacion puntuacion = new Puntuacion();
        puntuacion.setPuntuacion(this.puntuacion);
        puntuacion.setPartidaId(this.id);
        return puntuacion;
    }

    @Override
    public void setPuntuacion(long l) {
        this.puntuacion = l;
    }

    @Override
    public void setPartidaId(Long aLong) {
        this.id = aLong;
    }

    public long getId() {
        return id;
    }
}
