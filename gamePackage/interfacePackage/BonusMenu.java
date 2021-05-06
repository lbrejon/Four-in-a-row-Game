package gamePackage.interfacePackage;

import java.util.Scanner;

public class BonusMenu{

    // Display the menu
    public static void interfaceMenu(){
        System.out.println(Color.setColor("\n----- Menu -----\n", Color.getYellow(), Color.getTextBold()));
        System.out.println("1 - Play");
        System.out.println("2 - Rules");
        System.out.println("3 - Settings");
        System.out.println("4 - Information");
        System.out.println("5 - Statistics\n");
    }

    // Command to go back to menu
    public static void goBackToMenu(){
        Scanner scanner = new Scanner(System.in);
        String buf;
        int validAnswer = 0;

        System.out.print("To go back to menu, type 'menu' : ");
        
        while (validAnswer == 0){
            buf = scanner.nextLine();
            exitMenu(buf);

            if (buf.equals("menu")){
                validAnswer = 1;
            }
            else{
                System.out.print("Input error. To go back to menu, type 'menu' : ");
            }
        }
    }


    // Display of the ending message when the "exit" command is typed
    public static void exitMenu(String buffer){
        if (buffer.equals("exit")){
            gamePackage.interfacePackage.WriteInLog.writeBuffer(buffer);
            System.out.println("Game over after ordering '"+buffer+"'");
            System.exit(0);
        }
    }


    // Display rules
    public static void displayRules(){    
        System.out.println(Color.setColor("\n[Rules]", Color.getNone(),1));
        System.out.println(Color.setColor("Four-in-a-row is a board game where the goal is to align 4 pieces on a grid of 6 rows and 7 columns. A game is played in 3 winning rounds. During each round, each player must choose a column (insert the column number in the terminal) to place his piece (X or O), which will slide to the lowest free position in the grid, leaving the hand to his opponent. The winner of the round will be the one who manages to align at least 4 of his pieces (the alignment must be horizontal, vertical or diagonal). Otherwise, if all the squares of the grid are occupied and no one has managed to align 4 checkers, the round will be declared a draw.", Color.getNone(),Color.getTextItalic()));
        goBackToMenu();
    }


    // Display parameters
    public static String[] displayParameters(int numberPlayers, int width, int height, int rounds, int tokens, String[] symbols){
        Scanner scanner = new Scanner(System.in);
        String buf, buf2;
        int[] buff;
        int validAnswer = 0;
        int validAnswer2 = 0;
        int modification = 0;
        int[] res = {numberPlayers, width, height, rounds, tokens};
        String[] symb = symbols; 

        System.out.println(Color.setColor("\n[Settings]", Color.getNone(),Color.getTextBold()));
        System.out.print("Change parameters ? [Yes/No] : ");
        buf = scanner.nextLine();

        while (validAnswer == 0){
            exitMenu(buf);

            if (buf.equals("Yes")){
                validAnswer = 1;
                
                while (validAnswer2 == 0){
                    System.out.println("\nA - Number of players");
                    System.out.println("B - Size of the grid");
                    System.out.println("C - Number of rounds");
                    System.out.println("D - Number of tokens to align to win");
                    System.out.println("E - Players' symbols");
                    System.out.println("Z - All the above mentioned settings ");
                    System.out.print("\nTo go back to menu, type 'menu'\n>> ");
                    buf2 = scanner.nextLine();
                    exitMenu(buf2);
                    
                    // Choose the number of players
                    if (buf2.equals("A")){
                        res[0] = changeNumberPlayers();
                        modification = 1;
                    }

                    // Choose the size of the grid
                    else if (buf2.equals("B")){
                        buff = changeWidthAndHeight(res[1], res[2],res[4]);
                        res[1] = buff[0];
                        res[2] = buff[1];
                        modification = 1;
                    }

                    // Choose the number of rounds to win
                    else if (buf2.equals("C")){                     
                        res[3] = changeNumberRounds();
                        modification = 1;
                    }

                    // Choose the number of tokens to win
                    else if (buf2.equals("D")){                        
                        res[4] = changeNumberTokens(res[1], res[2]);
                        modification = 1;
                    } 

                    // Choose the symbol of players
                    else if (buf2.equals("E")){                        
                        symb = changeTokensSymbols(symb, res[0]); // symbols
                        modification = 1;
                    } 
                        
                    // Change all these parameters
                    else if (buf2.equals("Z")){                        
                        res[0] = changeNumberPlayers();
                        buff = changeWidthAndHeight(res[1], res[2],res[4]);
                        res[1] = buff[0];
                        res[2] = buff[1];
                        res[3] = changeNumberRounds();
                        res[4] = changeNumberTokens(res[1], res[2]);
                        modification = 1;
                    }
                    // Go back to menu
                    else if (buf2.equals("menu")){
                        validAnswer2 = 1;
                        if (modification == 1)
                            System.out.println(Color.setColor("The game settings have been successfully modified", Color.getGreen(),0));
                    }

                    // Input error
                    else{
                        System.out.print("Input error. Mention the parameters to be modified : ");
                    }
                }
            }
            else if (buf.equals("No"))
                validAnswer = 1;
            else{
                System.out.print("Input error. Change parameters ? [Yes/No] : ");
                buf = scanner.nextLine();
            }
        }
        String[] finalRes = concatString(convertIntToString(res), symb);
        return finalRes;  
    }


    // Display information 
    public static void displayInformations(){
        System.out.println(Color.setColor("\n[Information]", Color.getNone(),1));
        System.out.println(Color.setColor("This academic project was developed in a team (3 members in total in 2020) in order to master the concepts of object-oriented programming.\n\nDedication to Evan COLLY", Color.getNone(),Color.getTextItalic()));
        goBackToMenu();
    }


    // Display statistics
    public static void displayStats(){    
        System.out.println(Color.setColor("\n[Statistics]", Color.getNone(),1));
        System.out.print(Color.setColor("Simulations were performed to determine and compare the different ia implemented (ia:random, ia:monkey and ia:pro)\n\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("          Rounds                             IA/IA                          Score\n", Color.getNone(),Color.getTextBold()));
        System.out.print(Color.setColor("           1000                        ia:random/ia:random                  503/497\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                        ia:random/ia:monkey                  188/812\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                        ia:random/ia:pro                     91/909\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                        ia:monkey/ia:pro                     336/664\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("           1000                    ia:random/ia:monkey/ia:pro             170/276/554\n", Color.getNone(),Color.getTextItalic()));
        System.out.print(Color.setColor("            6                          ia:pro/a friend                      4/2\n", Color.getNone(),Color.getTextItalic()));
        goBackToMenu();
    }


    // Changing the number of players
    public static int changeNumberPlayers(){
        Scanner scanner = new Scanner(System.in);
        int numberPlayers = 2;
        int validAnswer = 0;
        String buf;

        System.out.print("Number of players : ");
        
        while(validAnswer == 0){
            buf = scanner.nextLine();
            exitMenu(buf);

            try{
            numberPlayers = Integer.parseInt(buf);
            if (numberPlayers >= 2 && numberPlayers <= 6)
                return numberPlayers;
            else
                System.out.print("Error : number of players incorrect (2-6 required players). Number of players : ");                
            }

            catch(Exception e){
                System.out.print("Error : wrong input. Number of players : ");
            }
        }

        return numberPlayers;
    }


    // Modification of the width of the grid
    public static int changeWidth(int tokens){
        Scanner scanner = new Scanner(System.in);
        int width = 7;
        int validAnswer = 0;
        String buf;

        System.out.print("Grid width : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            exitMenu(buf);

            try{
                width = Integer.parseInt(buf);
                if (width <= 3)
                    System.out.print("Error : wrong grid width (>= 4 required). Grid witdh : ");
                else if (width < tokens)
                    System.out.print("Error : wrong grid width (grid width < "+tokens+" : number of tokens to align). Grid witdh : ");
                else
                    return width;
            }

            catch(Exception e){
                System.out.print("Error : wrong input. Grid witdh : ");
            }
        }

        return width;
    }

    
    // Modification of the height of the grid
    public static int changeHeight(int tokens){
        Scanner scanner = new Scanner(System.in);
        int height = 6;
        int validAnswer = 0;
        String buf;
        System.out.print("Grid height : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            exitMenu(buf);

            try{
                height = Integer.parseInt(buf);
                if (height <= 1)
                    System.out.print("Error : wrong grid height (>= 2 required). Grid height : ");
                else if (height < tokens)
                    System.out.print("Error : wrong grid height (grid height < "+tokens+" : number of tokens to align). Grid height : ");
                else
                    return height;
            }

            catch(Exception e){
                System.out.print("Error : wrong input. Grid height : ");
            }
        }

        return height;
    }


    // Checking the new values for the width and height of the grid
    public static int[] changeWidthAndHeight(int width, int height, int tokens){
        int validAnswer = 0;
        int[]res = new int[2];

        while (validAnswer == 0){
            width = changeWidth(tokens);
            height = changeHeight(tokens); 

            if ((width*height)%2 == 0 && (width*height) >= 8)
                validAnswer = 1;
            
            else
                System.out.println("Error : length x width is not even");    
        }

        res[0] = width;
        res[1] = height;

        return res;
    }


    // Modification of the number of rounds to win
    public static int changeNumberRounds(){
        Scanner scanner = new Scanner(System.in);
        int rounds = 3;
        int validAnswer = 0;
        String buf;

        System.out.print("Number of rounds to win : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            exitMenu(buf);

            try{
                rounds = Integer.parseInt(buf);
                if (rounds < 3)
                    System.out.print("Error : insufficient number of rounds. (>= 3 required rounds). Number of rounds : ");
                else
                    return rounds;
            }

            catch(Exception e){
            System.out.print("Error : wrong input. Number of rounds : ");
            }
        }

        return rounds;
    }


    // Change the number of tokens to align to win a round
    public static int changeNumberTokens(int width, int height){
        Scanner scanner = new Scanner(System.in);
        int tokens = 3;
        int validAnswer = 0;
        String buf;

        System.out.print("Number of tokens to align to win : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            exitMenu(buf);

            try{
                tokens = Integer.parseInt(buf);
                if (tokens < 4)
                    System.out.print("Error : wrong number of tokens (>= 4 required tokens). Number of tokens : ");
                else if (tokens > width)
                    System.out.print("Error : wrong number of tokens (number of tokens > "+tokens+" : grid width). Number of tokens : ");
                else if (tokens > height)
                    System.out.print("Error : wrong number of tokens (number of tokens > "+tokens+" : grid height). Number of tokens : ");
                else
                    return tokens;
            }

            catch(Exception e){
                System.out.print("Error : wrong input. Number of tokens : ");
            }
        }

        return tokens;
    }


    // Changing player symbols
    public static String[] changeTokensSymbols(String[] symbols, int numberPlayers){
        Scanner scanner = new Scanner(System.in);
        int numeroPlayer;
        int validAnswer = 0, validAnswer2 = 0;
        int newSymbol = 1;
        String buf, buf2;
        System.out.print("Change the player's symbol : ");

        while(validAnswer == 0){
            buf = scanner.nextLine();
            exitMenu(buf);

            try{
                numeroPlayer = Integer.parseInt(buf);
                if (numeroPlayer < 1 || numeroPlayer > numberPlayers)
                    System.out.print("Error : player "+numeroPlayer+" not found. Change the player's symbol : ");
                else{
                    while (validAnswer2 == 0){
                        System.out.print("Selection of the new symbol : ");
                        buf2 = scanner.nextLine();
                        newSymbol = 1;

                        try{
                            if (buf2.length() == 1){
                                if (buf2.equals(".") || buf2.equals(" ")){
                                    System.out.print("Error : prohibited symbol ('.' or ''). ");                               
                                }
                                else{
                                    for (int k = 1; k<numberPlayers+1; k++){
                                        if (buf2.concat(" ").equals(symbols[k]) && k != numeroPlayer){
                                            System.out.print("Error : symbol "+symbols[k]+"already used. ");
                                            newSymbol = 0;
                                        }
                                    }
                                    if (newSymbol == 1){
                                        symbols[numeroPlayer] = buf2.concat(" ");
                                        return symbols;
                                    } 
                                }
                            }
                            else
                                System.out.print("Error : the symbol can only contain one letter. ");
                        }
                        catch(Exception e){
                            System.out.print("Error : wrong input. Selection of the new symbol : ");
                        }
                    }
                }
            }
            catch(Exception e){
                System.out.print("Error : wrong input. Change the player's symbol : ");
            }
        }
        return symbols;
    }


    // Convert an array of integers to an array of strings
    public static String[] convertIntToString(int[] res){
        int len = res.length;
        String[] intToString = new String[len];

        for (int l = 0; l<len; l++){
            intToString[l] = Integer.toString(res[l]);
        }

        return intToString;
    }


    // Convert an array of strings into an array of integers 
    public static int[] convertStringToInt(String[] res){
        int len = res.length;
        int[] stringToInt = new int[len];

        for (int m = 0; m<len; m++){
            stringToInt[m] = Integer.parseInt(res[m]);
        }

        return stringToInt;
    }


    // Concatenation of 2 arrays of strings
    public static String[] concatString(String[] str1, String[] str2){
        int len1 = str1.length;
        int len2 = str2.length;
        String[] res = new String[len1+len2];

        for (int i = 0; i<len1+len2; i++){
            if (i<len1)
                res[i] = str1[i];
            else
                res[i] = str2[i-len1];
        }
        return res;
    }
    

    // Retrieve an array of strings from index start to index end
    public static String[] getStringFromTo(String[] str, int start, int end){
        String[] res = new String[end-start+1];
        for (int i = 0; i<end-start+1; i++)
            res[i] = str[start+i];
        return res;
    }


    // Menu options
    public static String[] parametersMenu(int numberPlayers, int width, int height, int rounds, int tokens, String[] symbols){
        Scanner scanner = new Scanner(System.in);
        int[] res = {numberPlayers, width, height, rounds, tokens};
        String[] buffString;
        String[] symb = symbols;
        String buffer;
        int[] buff;
        

        System.out.println("WELCOME TO THE FOUR-IN-A-ROW GAME !");

        // Menu interface
        interfaceMenu();
        System.out.print("Selection : ");
        buffer = scanner.nextLine();

        while(!buffer.equals("1")){
            exitMenu(buffer);

            // Rules
            if (buffer.equals("2"))
                displayRules();

            // Settings
            else if (buffer.equals("3")){
                buffString = displayParameters(numberPlayers, width, height, rounds, tokens, symbols);
                buff = convertStringToInt(getStringFromTo(buffString,0,4));
                res[0] = buff[0];
                res[1] = buff[1];
                res[2] = buff[2];
                res[3] = buff[3];
                res[4] = buff[4];
                symb = getStringFromTo(buffString,5,buffString.length-1);
            }

            // Information
            else if (buffer.equals("4"))
                displayInformations();

            // Statistics
            else if (buffer.equals("5"))
                displayStats();
            
            // Input error
            else
                System.out.println("Error : wrong input. The number must be between 1 and 5 !");

            // System.out.println("\n");
            interfaceMenu();
            System.out.print("Selection : ");
            buffer = scanner.nextLine();
        }
        System.out.println("\nLet's play !\n");

        String[] finalRes = concatString(convertIntToString(res), symb);
        return finalRes;  
    }

}