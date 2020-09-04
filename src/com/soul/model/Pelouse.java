package com.soul.model;

/*
 * Pelouse définit par sa longueur N et sa largeur M
 * une pelouse est découpée en plusieurs Case
 */
public class Pelouse {

  private final int longueur;
  private final int largeur;
  private final Case[][] grille;

  public Pelouse(int longueur, int largeur) {
    this.longueur = longueur;
    this.largeur = largeur;
    this.grille = new Case[largeur][longueur];
    for (int i = 0; i < largeur; i++) {
      for (int j = 0; j < longueur; j++) {
        this.grille[i][j] = new Case(i, j);
      }
    }
  }

  /*
   * Vérifie la validité des coordonnées du prochain déplacement de la tondeuse
   */
  public synchronized boolean checkMoveValidity(int nextx, int nexty) {
    return nextx >= 0 && nextx < this.longueur && nexty >= 0 && nexty < this.largeur;
  }

  /*
   * Tentative de déplacement d'une tondeuse sur la pelouse
   */
  public synchronized boolean makeMove(int x, int y, int nextx, int nexty) {
    if (this.grille[nexty][nextx].isAvailable()) {
      this.grille[nexty][nextx].makeUnAvailable();
      this.grille[y][x].makeAvailable();
      return true;
    }
    return false;
  }

  /*
   * Initialisation de la position de la tondeuse sur la pelouse.
   */
  public synchronized void initPositionTondeuse(int x, int y) {
    this.grille[y][x].makeUnAvailable();
  }
}
