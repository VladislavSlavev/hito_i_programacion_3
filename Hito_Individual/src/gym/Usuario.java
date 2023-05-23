package gym;

public class Usuario {
    private String nombre;
    private String planTrabajo;
    private double peso;
    private int eventosMes;
    private boolean horasExtra;

    public Usuario(String nombre, String planTrabajo, double peso, int eventosMes, boolean horasExtra) {
        this.nombre = nombre;
        this.planTrabajo = planTrabajo;
        this.peso = peso;
        this.eventosMes = eventosMes;
        this.horasExtra = horasExtra;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlanTrabajo() {
        return planTrabajo;
    }

    public double getPeso() {
        return peso;
    }

    public int getEventosMes() {
        return eventosMes;
    }

    public boolean isHorasExtra() {
        return horasExtra;
    }
}

