package comedor;

public class Alumno extends Thread {
    
    private int iNumAlumno;
    private char cNumCola;
    private int iMesaComedor;
    private Comedor cmdComedor;
    
    public Alumno( ) { }
    
    public Alumno( int iNumAlu, char cNumCol, Comedor cmdC ) { 
        iNumAlumno = iNumAlu; 
        cNumCola = cNumCol;
        cmdComedor = cmdC;
        
        iMesaComedor = -1;
    }
    
    public void run() {
        
        int iTiempo;
        
        // Cada alumno tarda en comer un tiempo aleatorio entre 1 y 10 segundos
        iTiempo = (int)(Math.random()*10000 + 1000);
        try {
            sleep(iTiempo);
        }
        catch( InterruptedException e ) { 
            VentanaPrincipal.Mensaje("Error de interrupción: método run de la clase Cola");
        }
        cmdComedor.SalirAlumno(this);
        
    }
    
    public int getNumAlumno() { return iNumAlumno; }
    public char getNumCola() { return cNumCola; }
    public int getMesaComedor() { return iMesaComedor; }
    public void setMesaComedor( int iMesa ) { iMesaComedor = iMesa; }
    
}
