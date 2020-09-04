package com.soul.utils;

import com.soul.model.Pelouse;
import com.soul.model.Tondeuse;
import java.io.IOException;

/*
 * TondeuseHandler utilitaire d'initialisation
 */
public class TondeuseHandler {

  private final FileParser fileParser;
  public TondeuseHandler(String filename) {
    this.fileParser = new FileParser(filename);
  }

  /*
   * 1 - Lecture du fichier
   * 2 - Initialisation de la pelouse et des tondeuses
   * 3 - Lancement des tondeuses
   */
  public void initAndStart(int nombreDeTentative) throws IOException {
    this.fileParser.parseFile();
    if (this.fileParser.isFileValid()) {
      int nbTondeuse = this.fileParser.nombreTondeuseALancer();
      String[] splitedline = this.fileParser.getLine(0).split(" ");
      Pelouse pelouse = new Pelouse(Integer.parseInt(splitedline[0]) + 1,
          Integer.parseInt(splitedline[1]) + 1);
      int indexLine = 1;
      for (int i = 0; i < nbTondeuse; i++) {
        String line = this.fileParser.getLine(indexLine);
        String[] splitedLine = line.split(" ");
        Tondeuse tondeuse = new Tondeuse(Integer.parseInt(splitedLine[0]),
            Integer.parseInt(splitedLine[1]), splitedLine[2].charAt(0), pelouse,
            this.fileParser.getLine(indexLine + 1), nombreDeTentative);
        indexLine += 2;
        Thread t = new Thread(tondeuse);
        t.start();
      }
    }
  }
}
