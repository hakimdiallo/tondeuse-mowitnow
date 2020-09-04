package com.soul.model;

/*
 * Case de la pelouse définit par ses coordonées x et y (position)
 */
class Case {
  private final int x;
  private final int y;
  private boolean available;

  public Case(int x, int y) {
    this.x = x;
    this.y = y;
    this.available = true;
  }

  /*
   * Véirifie si la case est disponible
   */
  public boolean isAvailable() {
    return available;
  }

  /*
   * Rend disponible la case
   */
  public void makeAvailable() {
    this.available = true;
  }

  /*
   * Rend indisponible la case
   */
  public void makeUnAvailable() {
    this.available = false;
  }
}
