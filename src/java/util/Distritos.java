/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego Torres
 */
public class Distritos {
    private List<String> lima_norte;
    private List<String> lima_sur;
    private List<String> lima_oeste;
    private List<String> lima_este;

    public Distritos() {
        this.lima_norte = new ArrayList<>();
        this.lima_norte.add("Los Olivos");
        this.lima_norte.add("Independencia");
        this.lima_norte.add("San Martín de Porres");
        this.lima_norte.add("La Perla");
        this.lima_norte.add("Callao");
        
        this.lima_sur = new ArrayList<>();
        this.lima_sur.add("San Isidro");
        this.lima_sur.add("Miraflores");
        this.lima_sur.add("Surquillo");
        this.lima_sur.add("Barranco");
        this.lima_sur.add("San Borja");
        this.lima_sur.add("Santiago de Surco");
        
        this.lima_oeste = new ArrayList<>();
        this.lima_oeste.add("San Miguel");
        this.lima_oeste.add("Pueblo Libre");
        this.lima_oeste.add("Jesús María");
        this.lima_oeste.add("Magdalena del Mar");
        this.lima_oeste.add("Lince");
        
        this.lima_este = new ArrayList<>();
        this.lima_este.add("Lima");
        this.lima_este.add("La Victoria");
        this.lima_este.add("Rimac");
        this.lima_este.add("Ate");
        this.lima_este.add("La Molina");        
    }

    public List<String> getLima_norte() {
        return lima_norte;
    }

    public void setLima_norte(List<String> lima_norte) {
        this.lima_norte = lima_norte;
    }

    public List<String> getLima_sur() {
        return lima_sur;
    }

    public void setLima_sur(List<String> lima_sur) {
        this.lima_sur = lima_sur;
    }

    public List<String> getLima_oeste() {
        return lima_oeste;
    }

    public void setLima_oeste(List<String> lima_oeste) {
        this.lima_oeste = lima_oeste;
    }

    public List<String> getLima_este() {
        return lima_este;
    }

    public void setLima_este(List<String> lima_este) {
        this.lima_este = lima_este;
    }
    
    
    
    
    
}
