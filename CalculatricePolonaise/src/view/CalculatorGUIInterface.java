package view;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Interface de la vue : c'est le contrôleur qui l'utilise pour mettre à jour l'affichage.
 */
public interface CalculatorGUIInterface {

    /** Rafraîchit l'affichage avec les dernières données reçues. */
    void affiche();

    /** Nouvelle valeur de l'accumulateur. */
    void change(String accu);

    /** Nouveau contenu de la pile (le dernier élément de la liste = sommet de pile). */
    void change(List<Double> stackData);

    /**
     * Le contrôleur s'abonne aux événements de la vue (clic sur un bouton).
     * La commande du bouton est récupérable via ((Button) e.getSource()).getUserData().
     */
    void setActionHandler(EventHandler<ActionEvent> handler);
}
