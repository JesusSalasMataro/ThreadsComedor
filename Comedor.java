package comedor;

import javax.swing.JTextArea;

public class Comedor {

    public static final int MAX_ALUMNOS = 60;
    
    // Alumnos que se encuentran comiendo actualmente en el comedor
    private Alumno[] aluAlumnos;
    
    // Número de alumnos comiendo actualmente en el comedor
    private int iNumAlumnos;

    // Colas del comedor
    public Cola colC1, colC2, colC3, colC4;
    
    public Comedor( ) {
        
        int i;
        
        // Inicializamos el arrays de alumnos
        iNumAlumnos = 0;
        aluAlumnos = new Alumno[MAX_ALUMNOS];
        for( i=0; i<MAX_ALUMNOS; i++ ) {
            aluAlumnos[i] = new Alumno();
            aluAlumnos[i] = null;
        }

        colC1 = new Cola('A', this);
        colC2 = new Cola('B', this);
        colC3 = new Cola('C', this);
        colC4 = new Cola('D', this);

        colC1.setPriority(Thread.MIN_PRIORITY);
        colC2.setPriority(Thread.MIN_PRIORITY);
        colC3.setPriority(Thread.MAX_PRIORITY);
        colC4.setPriority(Thread.MIN_PRIORITY);
        
        LlenarColas();
        colC1.start();
        colC2.start();
        colC3.start();
        colC4.start();

    }
    
    public synchronized boolean EntrarAlumno( Alumno aluAlumno, char cNumCola ) {
        
        boolean bEntraOk = true;
        char cCola = ' ';
        String sCodAlumno;
        int i;
        
        if( iNumAlumnos >= MAX_ALUMNOS ) {
            try {
                VentanaPrincipal.Mensaje("Cola " + cNumCola + " en espera...\n");
                wait(); 
                VentanaPrincipal.Mensaje("++ Cola " + cNumCola + " finaliza espera\n");
            }
            catch(InterruptedException e) { 
                VentanaPrincipal.Mensaje("Error de interrupción: método run de la clase Cola");
                bEntraOk = false; 
            }
        }
        
        sCodAlumno = CodigoAlumno(aluAlumno);
         
        // Buscamos una mesa libre en el comedor y colocamos al alumno
        if( ColocarAlumno(aluAlumno, sCodAlumno) ) {       
            iNumAlumnos++;
            aluAlumno.start();
            VentanaPrincipal.Mensaje(
                    "          --> Entra el alumno " + sCodAlumno + "\n");
            VisualizarComedor();
        }
        else bEntraOk = false;
            
        return( bEntraOk );
                    
    }
    
    public synchronized void SalirAlumno( Alumno aluAlumno ) {
        
        String sCodAlumno;
                
        sCodAlumno = CodigoAlumno(aluAlumno);
        aluAlumnos[aluAlumno.getMesaComedor()] = null;
        iNumAlumnos--;
        VentanaPrincipal.Mensaje(
                "     <-- Sale el alumno " + sCodAlumno + "\n");
        VisualizarComedor();
        
        notifyAll();
        
    }
    
    public synchronized boolean ColocarAlumno( Alumno aluAlumno, String sCodAlumno ) {
        
        int i = 0;
        boolean bColocarAlumnoOk = true;
        
        while( (i<(MAX_ALUMNOS)) && (aluAlumnos[i] != null) ) i++;
        if( i<(MAX_ALUMNOS) ) {
            aluAlumnos[i] = aluAlumno;
            aluAlumnos[i].setMesaComedor(i);
                       
            VentanaPrincipal.txaComedor.append(sCodAlumno + " ");
            if( (iNumAlumnos % 10 == 0) && (iNumAlumnos > 0) ) 
                VentanaPrincipal.txaComedor.append("\n"); 
        }
        else bColocarAlumnoOk = false;
        
        return bColocarAlumnoOk;
            
    }
    
    public String CodigoAlumno( Alumno aluAlumno ) {
        
        String sCodAlumno = "";
        char cCola;
        
        cCola = aluAlumno.getNumCola();                   
        if( aluAlumno.getNumAlumno() < 10 ) 
            sCodAlumno = cCola + "0" + Integer.toString(aluAlumno.getNumAlumno());
        else 
            sCodAlumno = cCola + Integer.toString(aluAlumno.getNumAlumno());
        
        return sCodAlumno;
        
    }
    
    public synchronized void VisualizarComedor() {
        
        int i;
        char cCola;
        String sCodAlumno;
        
        cCola = ' ';
        sCodAlumno = "";
        VentanaPrincipal.txaComedor.setText("\n     ");
        for( i=0; i<MAX_ALUMNOS; i++ ) {
           if( aluAlumnos[i] == null ) {
               VentanaPrincipal.txaComedor.append(" ___   ");
           } 
           else {               
                cCola = aluAlumnos[i].getNumCola();          
                if( aluAlumnos[i].getNumAlumno() < 10 ) 
                    sCodAlumno = cCola + "0" + Integer.toString(aluAlumnos[i].getNumAlumno());
                else 
                    sCodAlumno = cCola + Integer.toString(aluAlumnos[i].getNumAlumno());
               
               VentanaPrincipal.txaComedor.append(sCodAlumno + "    ");
           }
           if( (i+1) % 10 == 0 ) VentanaPrincipal.txaComedor.append("\n     ");
        }
        
    }
    
    private void LlenarColas() {
        
        int i;
        Alumno aluAlumno;
        
        for( i=0; i<Cola.MAX_COLA; i++ ) { 
            aluAlumno = new Alumno(i, colC1.getNumCola(), this);
            colC1.AnadirAlumno(aluAlumno);
        }
        
        for( i=0; i<Cola.MAX_COLA; i++ ) { 
            aluAlumno = new Alumno(i, colC2.getNumCola(), this);
            colC2.AnadirAlumno(aluAlumno);
        }
        
        for( i=0; i<Cola.MAX_COLA; i++ ) { 
            aluAlumno = new Alumno(i, colC3.getNumCola(), this);
            colC3.AnadirAlumno(aluAlumno);
        }
        
        for( i=0; i<Cola.MAX_COLA; i++ ) { 
            aluAlumno = new Alumno(i, colC4.getNumCola(), this);
            colC4.AnadirAlumno(aluAlumno);
        }

    }

    public int getNumAlumnos() { return iNumAlumnos; }
        
}
