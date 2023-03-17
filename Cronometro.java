import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {
    
    private Timer timer;
    private int minutosAlarma;
    private int segundosAlarma;
    private int segundosTranscurridos;
    
    public Cronometro() {
        timer = new Timer();
    }
    
    public void iniciar() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                segundosTranscurridos++;
                System.out.println(obtenerHora());
                if (segundosTranscurridos == segundosAlarma && minutosAlarma == 0) {
                    sonarAlarma();
                } else if (segundosTranscurridos == 60) {
                    segundosTranscurridos = 0;
                    minutosAlarma--;
                }
            }
        }, 0, 1000);
    }
    
    public void configurarAlarma(int minutos, int segundos) {
        minutosAlarma = minutos;
        segundosAlarma = segundos;
    }
    
    private void sonarAlarma() {
        TimerTask tareaAlarma = new TimerTask() {
            public void run() {
                System.out.println("¡ALARMA!");
            }
        };
        timer.scheduleAtFixedRate(tareaAlarma, 0, 10000);
    }
    
    private String obtenerHora() {
        Date fecha = new Date(segundosTranscurridos * 1000);
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(fecha);
    }
    
    public static void main(String[] args) {
        Cronometro cronometro = new Cronometro();
        cronometro.configurarAlarma(2, 0);
        cronometro.iniciar();
    }
}