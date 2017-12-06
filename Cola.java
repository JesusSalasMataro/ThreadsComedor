package comedor;

public class Cola extends Thread {
    
    public static final int MAX_COLA = 50;
    
    private Alumno[] aluAlumnos;
    private int iNumAlumnos;
    private char cNumCola;
    private Comedor cmdComedor;
    
    public Cola( char cNumC, Comedor cmdCom ) {
        
        super();

        iNumAlumnos = 0;
        aluAlumnos = new Alumno[MAX_COLA];
        cNumCola = cNumC;
        cmdComedor = cmdCom;
        
    }
    
    public void AnadirAlumno( Alumno aluAlumno ) {
        
        aluAlumnos[iNumAlumnos] = new Alumno();
        aluAlumnos[iNumAlumnos] = aluAlumno;
        iNumAlumnos++;
        
    }
    
    public void run() {
       
        int iAlumnoActual;
        
        iAlumnoActual = 0;
        while( iAlumnoActual < MAX_COLA ) {
            if( cmdComedor.EntrarAlumno(aluAlumnos[iAlumnoActual], cNumCola) )
                iAlumnoActual++;
            try { sleep(200); }
            catch( InterruptedException e ) {
                VentanaPrincipal.Mensaje("Error de interrupción: método run de la clase Cola");
            }                       
        }
        
    }
    
    public char getNumCola() { return cNumCola; }
    
}
