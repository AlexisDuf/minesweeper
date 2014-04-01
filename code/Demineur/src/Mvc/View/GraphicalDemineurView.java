/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mvc.View;
import Events.ChangeCaseEvent;
import Events.ChangeTimerEvent;
import Events.Event;
import Mvc.Model.AbstractDemineurModel;
import Mvc.View.Graphical.header.GraphicalDemineurMenu;
import Mvc.View.CustomeFrame.NewGameFrame;
import Mvc.View.Graphical.center.GraphicalGridView;
import Mvc.View.Graphical.footer.FooterBar;
import Mvc.View.Scores.NewBestScoreFrame;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

/**
 *
 * @author Delmaire & Dufour
 */
public class GraphicalDemineurView extends JFrame implements AbstractView{
     /*
     * View
     */

    private GraphicalGridView grid;
    private JMenuBar menu;
    private FooterBar footb;

    /*
     * Controller
     */
    /**
     *
     * @throws HeadlessException
     */
    public GraphicalDemineurView() throws HeadlessException {
        super();
    }

    /**
     *
     * @param _title
     * @param _xSize
     * @param _ySize
     * @param _resizable
     * @param _rowsNumber
     * @param _colsNumber
     * @param _model
     * @throws HeadlessException
     */
    public GraphicalDemineurView(String _title, int _xSize, int _ySize, boolean _resizable, int _rowsNumber, int _colsNumber, AbstractDemineurModel _model) throws HeadlessException {
        setTitle("Demineur");
        getContentPane().setLayout(new BorderLayout());
        setSize(_xSize, _ySize);
        setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        setResizable(_resizable);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
    }

    /*
     * Crée la vue et initialise les controlleurs
     */
    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean createView(Event ev) {
        this.footb = new FooterBar(ev);
        this.grid = new GraphicalGridView(ev);
        this.menu = new GraphicalDemineurMenu(ev);
        this.setJMenuBar(this.menu);
        getContentPane().add(this.grid, BorderLayout.CENTER); //Ajout 
        getContentPane().add(this.footb, BorderLayout.PAGE_END);
        this.setVisible(true);
        return true;
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean removeView(Event ev) {
        return true;
    }

    /**
     *
     * @param cce
     * @return
     */
    @Override
    public boolean refreshCase(ChangeCaseEvent cce) {
        this.grid.refreshCase(cce);
        return true;
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean refreshWinGame(Event ev) {
        this.grid.refreshWinGame(ev);
        if(Tools.Tools.isNewBestScore(ev.getSender().getBestScores(ev.getSender().getCurrentLevel()), ev.getSender().getTimer())){
           NewBestScoreFrame nbsf = new NewBestScoreFrame("Félicitations vous avez remporté la partie en "+ ev.getSender().getTimer().getMinutes() + " minutes et "+ ev.getSender().getTimer().getSecondes() + " secondes Partie remportée"); 
        }
        else{
           showOptionDialog("Felicitations, vous avez remporté la partie\nNéanmoins vous n'avez pas obtenu l'un des 5 meilleurs scores de ce niveau\nEntrainez-vous encore !","Partie gagnée");
        }
        
        return true;
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean refreshLooseGame(Event ev) {
        this.grid.refreshLooseGame(ev);
        showOptionDialog("Vous avez cliqué sur une bombe, la partie est perdue !","Partie perdue");
        return true;
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean refreshGrid(Event ev) {
        this.remove(this.grid);
        this.remove(this.footb);
        return createView(ev);
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean refreshRemainingBomb(Event ev) {
        this.footb.bsbv.refreshRemainingBomb(ev);
        return true;
    }
    
    private void showOptionDialog(String content, String title){
        String[] options = {
            "Rejouer une partie",
            "Quitter"
        };
        
        int result = JOptionPane.showOptionDialog(null,content, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
        if(result==0){
            NewGameFrame.getInstance().setVisible(true);
        }else{
            System.exit(0);
        }      
    }

    @Override
    public boolean refreshTimer(ChangeTimerEvent ev) {
        this.footb.refreshTimer(ev);
        return true;
    }

    @Override
    public boolean refreshBomb(Event ev) {
        return this.grid.refreshBomb(ev);
    }
  
    
}
