package gamePackage;

import java.util.ArrayList; 

public class Grid {

  // Attributes
  private final int width;
  private final int height;
  private int[][] values;
  private final String[] symbols;

  // Constructors
  public Grid(int width, int height, String[] symbols){
    this.width = width;
    this.height = height;
    this.values = initializeValues(width, height);
    this.symbols = symbols;
  }

  // Getters
  public int getWidth(){
    return this.width;
  }

  public int getHeight(){
    return this.height;
  }

  public int[][] getValues(){
    return this.values;
  }

  public String getSymbols(int i){
    return this.symbols[i];
}

  // Others methods

  // Initialize all grid values to zero
  private static int[][] initializeValues(int width, int height){
    int grid[][]  = new int[height][width];
    for (int i = 0; i<height; i++){
      for (int j = 0; j<width; j++){
        grid[i][j] = 0;
      }
    }
    return grid;
  }

  // Reset all grid values to zero
  void resetGrid(){
    int width = this.width;
    int height = this.height;
    for (int i = 0; i<height; i++){
      for (int j = 0; j<width; j++){
        this.values[i][j] = 0;
      }
    }
  }

  // Updating the grid after a player has played
  int updateGrid(int position, int numeroPlayer, gamePackage.interfacePackage.WriteInLog write){
    int width = this.width;
    int height = this.height;

    for(int k = 0; k<height; k++){
      // if the cell is empty then the player can play in it, otherwise we look at the cell above (always in the given column)
      if (this.values[height-1-k][position-1] == 0){

        this.values[height-1-k][position-1] = numeroPlayer;

        System.out.println("Player "+numeroPlayer+" plays in position "+position+"\n"); 
        write.writeBuffer("Player "+numeroPlayer+" plays in position "+position);

        return position;
      }
    }
    write.writeBuffer("Player "+numeroPlayer+" plays in position "+position);
    System.out.println("Error : column "+position+" full. Choose a number between 1 and "+width+" : ");

    position = -3; // invalid move, we have to start over (because column is full)

    return position;
  }

  // Test s'il y a égalité en regardant si la première ligne est pleine
  int equalityCheck(){
    int cpt = 0;

    for (int i = 0; i<width; i++){ // Parcours des colonnes
      if (this.values[0][i] != 0)
        cpt++;
    }
    if (cpt == width)
      return 1;

    return 0;
  }

  // Test s'il y a victoire, tokens = nombre de jeton à aligner pour gagner, lastColumn = position jouée
  int victoryCheck(int tokens, int lastColumn){
    int lastLine = 0; // Recherche de la coordonné i du dernier coup (on connait déjà le j)

    // On remonte du bas pour savoir qu'elle est la dernière ligne remplie (qui correspond alors à la ligne où le jeton du joueur se trouve)
    while(this.values[lastLine][lastColumn] == 0)
      lastLine++;

    int vertical[] = new int[height];  // Initialsation de la liste "vertivale"
    int horizontal[] = new int[width]; // Initialisation de la liste "horizontale"
    int lenDiag = 1;
    int lenAntiDiag = 1;
    // Initialisation des diagonales de manière dynamique car leur taille dépend de la position jouée
    ArrayList<Integer> diagonalTmp = new ArrayList<Integer>();     // Diagonale "/"
    ArrayList<Integer> antidiagonalTmp = new ArrayList<Integer>(); // Diagonale "\"

    // On ajoute dans les deux diagonales la valeur du jeton qui vient d'être joué
    diagonalTmp.add(this.values[lastLine][lastColumn]);
    antidiagonalTmp.add(this.values[lastLine][lastColumn]);

    // Remplissage de la diagonale en parcourant la grille vers le haut droite puis vers le bas gauche
    int tmpLine = lastLine; int tmpColumn = lastColumn;
    // Test si la cellule est possible (ie si elle existe et qu'on ne sort pas de la grille)
    while((tmpLine-- > 0) && (tmpColumn++ < width-1)){
      diagonalTmp.add(this.values[tmpLine][tmpColumn]); // Si la celulle existe on met la valeur du jeton à la fin de notre liste
      lenDiag++; // On augmente la variable taille de notre liste (on en aura besoin plus tard) - Même si une fonction existe surement pour donner la taille d'une ArrayList
    }
    tmpLine = lastLine; tmpColumn = lastColumn; // Reinitialisation
    // Test si la cellule est possible (ie si elle existe et qu'on ne sort pas de la grille)
    while((tmpLine++ < height-1) && (tmpColumn-- > 0)){
      diagonalTmp.add(0, this.values[tmpLine][tmpColumn]); // Si la celulle existe on met la valeur du jeton au début de notre liste
      lenDiag++; // On augmente la variable taille de notre liste (on en aura besoin plus tard)
    }

    // Remplissage de l'antidiagonale suivant le même principe que la diagonale (bas droite puis haut gauche cette fois)
    tmpLine = lastLine; tmpColumn = lastColumn; // Reinitialisation
    while((tmpLine++ < height-1) && (tmpColumn++ < width-1)){
      antidiagonalTmp.add(this.values[tmpLine][tmpColumn]); // Encore une fois on met la valeur à la fin si la case existe
      lenAntiDiag++; // On augmente la variable taille de notre liste (on en aura besoin plus tard)
    }
    tmpLine = lastLine; tmpColumn = lastColumn; // Reinitialisation
    while((tmpLine-- > 0) && (tmpColumn-- > 0)){
      antidiagonalTmp.add(0, this.values[tmpLine][tmpColumn]); // Encore une fois on met la valeur au début si la case existe
      lenAntiDiag++; // On augmente la variable taille de notre liste (on en aura besoin plus tard)
    }

    // Remplissage de l'horizontale + la verticale

    // On parcourt les lignes de bas en haut (par rapport à une vraie grille) sur la colonne jouée
    // pour remplir notre liste avec la valeur des jetons
    for(int i = 0; i<height; i++)
      vertical[i] = this.values[i][lastColumn];

    // On parcourt les colonnes de gauche à droite (par rapport à une vraie grille) sur la ligne jouée
    // pour remplir notre liste avec la valeur des jetons
    for(int i = 0; i<width; i++)
      horizontal[i] = this.values[lastLine][i];
    
    // Diagonale et Antidiagonale sont désormais des listes (plus simple à manipuler pour la suite)
    int diagonal[] = new int[lenDiag];
    int antiDiagonal[] = new int[lenAntiDiag];

    // On remplit nos nouvelles listes diag et anti diag avec les anciennes (de type ArrayList)
    for (int i = 0; i<lenDiag; i++)
      diagonal[i] = diagonalTmp.get(i);
    
    for (int i = 0; i<lenAntiDiag; i++)
      antiDiagonal[i] = antidiagonalTmp.get(i);
    
    // Maintenant que nous avons quatre listes contenant chacune la valeur du jeton joué et la valeur des autres jetons
    // dans la direction de la liste (donc dans toutes les directions au final).
    // On peut rechercher si dans ces listes, un alignement de la valeur de "tokens" est présent ou pas
    if(arrayCheck(tokens, vertical, height) == 1)
      return 1; // victoire
    else if(arrayCheck(tokens, horizontal, width) == 1)
      return 1; // victoire
    else if(arrayCheck(tokens, diagonal, lenDiag) == 1)
      return 1; // victoire
    else if(arrayCheck(tokens, antiDiagonal, lenAntiDiag) == 1)
      return 1; // victoire
    
    return 0; // pas de victoire
  }

  // Affichage d'un tableau (DEBUGING)
  private void printArray(int[] array, int length){
    for (int i=0; i<length; i++)
      System.out.print(array[i]);
    
    System.out.print("\n");
  }

  // Test s'il y a une victoire dans une liste d'un nombre d'élément donné
  // Pour cela, on regarde si une suite de "tokens" même symboles consécutifs est présxente ou non
  private int arrayCheck(int tokens, int[] array, int length){

    // Index max où on peut aller dans notre liste (si l'on va plus loin cela ne sert à rien)
    // Par exemple si on cherche à aligner 4 jetons, regardez les trois derniers jetons ensemble ne sert à rien
    int max = length - tokens;
    int[] vals  = new int[tokens]; // Liste de la taille du nombre de jeton à aligner pour gagner
    int cpt = 1; // Compteur (initialisé à 1 car on ne regarde jamais le premier jeton dans vals)

    for(int i = 0; i <= max; i++){ // Parcours de notre liste en s'arrêtant à max (cf commentaire de la var max)
      for(int j = 0; j<tokens; j++){ // Parcours des "tokens" jetons suivant le premier (le jeton i donc)
        vals[j] = array[i+j]; // Ajout dans notre liste de verif la valeur du jeton qu'on regarde
      }
      if (vals[0] != 0) // Parcours de notre liste de verif si le premier jeton est non nul (car si il est non nul c'est sûr qu'il n'y a pas de victoire)
        for (int k = 1; k<tokens; k++){ // Le parcours ne commence pas au premier jeton car cpt initialisé à 1
          if (vals[0] == vals[k]) // Si la valeur du jeton qu'on regarde est la même que le premier jeton alors
            cpt += 1; // on incrémente noter compteur
        }
        if (cpt == tokens) // Si le nombre de jetons similaires au premier (+ 1 le premier)
          // est égal au nombre de jetons à aligner pour gagner alors c'est une victoire
          return 1;

      // On réinitialise notre compteur et notre liste de verif pour la prochaine itération
      vals = new int[tokens];
      cpt = 1;
    }
    return 0; // Pas de victoire si aucune suite de 4 n'a été trouvé
  }

}
