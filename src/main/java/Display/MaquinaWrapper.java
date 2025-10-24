package Display;

import Logic.IJugadorPlus;
import es.upm.etsisi.fis.model.IMovimiento;
import es.upm.etsisi.fis.model.IPuntuacion;
import es.upm.etsisi.fis.model.Maquina;
import es.upm.etsisi.fis.model.TBarcoAccionComplementaria;

import java.util.List;

public class MaquinaWrapper implements IJugadorPlus {
    private Maquina maquina;
    private String id;
    public MaquinaWrapper(Maquina maquina){
        this.maquina = maquina;
        this.id = "M-"+System.currentTimeMillis();
    }

    @Override
    public String getData() {
        return id+";"+maquina.getNombre()+";"+maquina.getPuntuaciones().get(maquina.getPuntuaciones().size()-1).getPuntos();
    }

    @Override
    public boolean aceptarAccionComplementaria(TBarcoAccionComplementaria tBarcoAccionComplementaria, int i) {
        return maquina.aceptarAccionComplementaria(tBarcoAccionComplementaria,i);
    }

    @Override
    public int[] realizaTurno(char[][] chars) {
        return maquina.realizaTurno(chars);
    }

    @Override
    public void addMovimiento(IMovimiento iMovimiento) {
        maquina.addMovimiento(iMovimiento);
    }

    @Override
    public String getNombre() {
        return maquina.getNombre();
    }

    @Override
    public void addPuntuacion(IPuntuacion iPuntuacion) {
        maquina.addPuntuacion(iPuntuacion);
    }

    @Override
    public List<IMovimiento> getMovimientos() {
        return maquina.getMovimientos();
    }

    @Override
    public List<IPuntuacion> getPuntuaciones() {
        return maquina.getPuntuaciones();
    }
}
