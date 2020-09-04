package com.soul.model;

/*
 * Tondeuse
 */
public class Tondeuse implements Runnable{

  private int positionx;
  private int positiony;
  private char orientation;
  private final Pelouse pelouse;
  private final String suiteDeplacement;
  private final int nombreDeTentative;

  public Tondeuse(int positionx, int positiony, char orientation, Pelouse pelouse,
      String suiteDeplacement, int nombreDeTentative) {
    this.positionx = positionx;
    this.positiony = positiony;
    this.orientation = orientation;
    this.pelouse = pelouse;
    this.suiteDeplacement = suiteDeplacement;
    this.nombreDeTentative = nombreDeTentative;
    this.pelouse.initPositionTondeuse(positionx, positiony);
  }

  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread,
   * starting the thread causes the object's
   * <code>run</code> method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method <code>run</code> is that it may take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {
    int indexDeplacement = 0;
    char [] deplacements = suiteDeplacement.toCharArray();
    int attempt = 0;
    while (indexDeplacement < deplacements.length) {
      if (deplacements[indexDeplacement] != 'A'){ // changement d'orientation de la tondeuse
        this.changeOrientation(deplacements[indexDeplacement]);
        indexDeplacement++;
      } else {
        int nextx = this.findNextXOrY('X');
        int nexty = this.findNextXOrY('Y');
        if (!this.pelouse.checkMoveValidity(nextx, nexty)) { // recherche la validité du prohain mouvement
          System.out.println("Tondeuse c'ant move anymore. Turning Off...");
          break;
        }
        if (this.pelouse.makeMove(this.positionx, this.positiony, nextx, nexty)) { // tentative de déplacement
          this.positionx = nextx;
          this.positiony = nexty;
          indexDeplacement++;
          attempt = 0;
        } else { // prochaine case déja occupée par une autre tondeuse
          System.out.println("Tondeuse c'ant move. Waiting...");
          attempt++;
          if (attempt == this.nombreDeTentative) {
            break;
          }
        }
      }
    }
    System.out.println(positionx + " " + positiony + " " + orientation);
  }

  /*
   * Permet de changer l'orientation de la tondeuse
   * @param orientation mouvement de la tondeuse
   */
  private void changeOrientation(char orientation) {
    switch (this.orientation) {
      case 'E':
        this.moveFromEast(orientation);
        break;
      case 'N':
        this.moveFromNorth(orientation);
        break;
      case 'W':
        this.moveFromWest(orientation);
        break;
      case 'S':
        this.moveFromSouth(orientation);
        break;
    }
  }

  /*
   * Permet de changer l'orientation de la tondeuse depuis l'Est
   * @param orientation mouvement de la tondeuse
   */
  private void moveFromEast(char orientation) {
    switch (orientation) {
      case 'D':
        this.orientation = 'S';
        break;
      case 'G':
        this.orientation = 'N';
        break;
    }
  }

  /*
   * Permet de changer l'orientation de la tondeuse depuis le Nord
   * @param orientation mouvement de la tondeuse
   */
  private void moveFromNorth(char orientation) {
    switch (orientation) {
      case 'D':
        this.orientation = 'E';
        break;
      case 'G':
        this.orientation = 'W';
        break;
    }
  }

  /*
   * Permet de changer l'orientation de la tondeuse depuis l'Ouest
   * @param orientation mouvement de la tondeuse
   */
  private void moveFromWest(char orientation) {
    switch (orientation) {
      case 'D':
        this.orientation = 'N';
        break;
      case 'G':
        this.orientation = 'S';
        break;
    }
  }

  /*
   * Permet de changer l'orientation de la tondeuse depuis le Sud
   * @param orientation mouvement de la tondeuse
   */
  private void moveFromSouth(char orientation) {
    switch (orientation) {
      case 'D':
        this.orientation = 'W';
        break;
      case 'G':
        this.orientation = 'E';
        break;
    }
  }

  /*
   * Permet de prochain X ou Y du déplacement de la tondeuse
   * @param axe X ou Y pour lequel on cherche le prochain coordonné
   */
  private int findNextXOrY(char axe) {
    switch (this.orientation) {
      case 'E':
        return axe == 'X' ? this.positionx + 1 : this.positiony;
      case 'N':
        return axe == 'X' ? this.positionx : this.positiony + 1;
      case 'W':
        return axe == 'X' ? this.positionx - 1 : this.positiony;
      case 'S':
        return axe == 'X' ? this.positionx : this.positiony - 1;
      default:
        return -1;
    }
  }
}
