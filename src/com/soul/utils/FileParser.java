package com.soul.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileParser {

  private final String filename;
  private boolean fileValid;
  private final List<String> news = new ArrayList<>();
  private final List<Character> possibleDeplacement = new ArrayList<>();
  private List<String> lines = new ArrayList<>();
  /*
   * Véirifie si la case est disponible
   * @param filename le nom du fichier à parser
   */
  public FileParser(String filename) {
    this.filename = filename;
    this.fileValid = false;
    this.news.add("N");
    this.news.add("E");
    this.news.add("W");
    this.news.add("S");
    this.possibleDeplacement.add('A');
    this.possibleDeplacement.add('G');
    this.possibleDeplacement.add('D');
  }

  /*
   * Permet de parser un fichier txt
   */
  public void parseFile() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));
    Stream<String> lines = reader.lines();
    this.lines = lines.collect(Collectors.toList());
    reader.close();
    this.checkFileValidity();
  }

  /*
   * Véirifie la validité du fichier par
   */
  private void checkFileValidity() {
    if (this.lines.size() > 2 && (lines.size() - 1) % 2 != 0) {
      this.fileValid = false;
      return;
    }
    this.fileValid = this.lines.get(0).split(" ").length == 2;
    for (int i = 1; i < this.lines.size(); i++) {
      String line = this.lines.get(i);
      if (i % 2 == 0) {
        if (!this.checkDeplacementValidity(line)) {
          this.fileValid = false;
          break;
        }
      } else {
        if (!this.checkPositionValidity(line)) {
          this.fileValid = false;
          break;
        }
      }
    }
  }

  /*
   * Véirifie qu'une ligne de postion de la tondeuse est valide
   * @param la ligne à vérifier
   */
  private boolean checkPositionValidity(String line) {
    String[] splitedLine = line.split(" ");
    return splitedLine.length == 3 && this.news.contains(splitedLine[2]);
  }

  /*
   * Véirifie qu'une ligne suite de déplacement de la tondeuse est valide
   * @param la ligne à vérifier
   */
  private boolean checkDeplacementValidity(String line) {
    return Stream.of(line.toCharArray()).anyMatch(c ->
        !this.possibleDeplacement.contains(c));
  }

  /*
   * Recupére la validité du fichier
   */
  public boolean isFileValid() {
    return this.fileValid;
  }

  /*
   * Recupére le nombre de tondeuse à lancer
   */
  public int nombreTondeuseALancer() {
    return (this.lines.size() - 1) / 2;
  }

  /*
   * Véirifie qu'une ligne de postion de la tondeuse est valide
   * @param index de la ligne à recupérer
   */
  public String getLine(int index) {
    return this.lines.get(index);
  }
}
