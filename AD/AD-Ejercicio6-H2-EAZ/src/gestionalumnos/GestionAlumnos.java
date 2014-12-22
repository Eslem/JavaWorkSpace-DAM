/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionalumnos;

import gestionalumnos.controller.MainController;
import gestionalumnos.model.bll.AlumnoBLL;
import gestionalumnos.view.MainView;

/**
 *
 * @author eslem
 */
public class GestionAlumnos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainController controller = new MainController();
        AlumnoBLL alumnoBLL = new AlumnoBLL();
        MainView mainView  = new MainView();
        controller.setAlumnoBLL(alumnoBLL);
        alumnoBLL.setController(controller);
        
        mainView.setController(controller);
        controller.setMainView(mainView);
        
        controller.start();
    }
    
}
