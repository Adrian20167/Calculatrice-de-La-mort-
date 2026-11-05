package controler;

import java.util.List;

/**
 * Callbacks appelés par le MODÈLE pour prévenir le contrôleur d'un changement.
 */
public interface CalculatorControlerInterface {

	/** Nouvelle valeur de l'accumulateur. */
	void change(String accu);

	/** Nouveau contenu de la pile (le dernier élément = sommet de pile). */
	void change(List<Double> stackData);
}
