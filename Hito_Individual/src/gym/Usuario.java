package gym;

public class Usuario {
    private String nombre;
    private String planTrabajo;
    private double peso;
    private int eventosMes;
    private int horasExtra;

    public Usuario(String nombre, String planTrabajo, double peso, int eventosMes, int horasExtra2) {
        this.nombre = nombre;
        this.planTrabajo = planTrabajo;
        this.peso = peso;
        this.eventosMes = eventosMes;
        this.horasExtra = horasExtra2;
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

    public int isHorasExtra() {
        return horasExtra;
    }
}

