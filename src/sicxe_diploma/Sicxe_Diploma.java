 
package sicxe_diploma;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Sicxe_Diploma {
    public static String[] label = new String[100];
    public static String[] instrucation = new String[100];
    public static String[] referance = new String[100];
    public static String[] locationCounter = new String[100];
    public static String[] objectCode = new String[100];
    public static String[][] OPTAB = new String[59][3];
    public static String[][] asciiChar = new String[6][2];
    public static int rowCount = 0;
    public static void main(String[] args) {
        ReadFile();
        formats();
        CalLocationCounter();
        printSymbolTable();
        CalObjectCode();
        HTE_Record();
        ModifiedRecord();
    }
    public static void ReadFile(){
        System.out.println("---------------------------------------------------");
        System.out.println("Pass 1:");
        System.out.println("---------------------------------------------------");
        try {
            FileReader myObj = new FileReader("sicxe.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\\s+"); // split by space or tabs
                if (parts.length >= 3) {
                    label[rowCount] = parts[0];
                    instrucation[rowCount] = parts[1];
                    referance[rowCount] = parts[2];
                    rowCount++;
                }
            }
            myReader.close();
            System.out.println( "label" + "    " + "instrucation"+ "    " + "referance");
            for(int i=0;i<rowCount;i++){
                System.out.println(label[i] + "           " + instrucation[i]+"             "+referance[i]);
            }
                        System.out.println("---------------------------------------------------");

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void formats() {
        OPTAB[0] = new String[]{"FIX", "1", "C4"};
        OPTAB[1] = new String[]{"FLOAT", "1", "C0"};
        OPTAB[2] = new String[]{"HIO", "1", "F4"};
        OPTAB[3] = new String[]{"NORM", "1", "C8"};
        OPTAB[4] = new String[]{"SIO", "1", "F0"};
        OPTAB[5] = new String[]{"TIO", "1", "F8"};
        OPTAB[6] = new String[]{"ADDR", "2", "90"};
        OPTAB[7] = new String[]{"CLEAR", "2", "B4"};
        OPTAB[8] = new String[]{"COMPR", "2", "A0"};
        OPTAB[9] = new String[]{"DIVR", "2", "9C"};
        OPTAB[10] = new String[]{"MULR", "2", "98"};
        OPTAB[11] = new String[]{"RMO", "2", "AC"};
        OPTAB[12] = new String[]{"SHIFTL", "2", "A4"};
        OPTAB[13] = new String[]{"SHIFTR", "2", "A8"};
        OPTAB[14] = new String[]{"SUBR", "2", "94"};
        OPTAB[15] = new String[]{"SVC", "2", "B0"};
        OPTAB[16] = new String[]{"TIXR", "2", "B8"};
        OPTAB[17] = new String[]{"ADD", "3", "18"};
        OPTAB[18] = new String[]{"ADDF", "3", "58"};
        OPTAB[19] = new String[]{"AND", "3", "40"};
        OPTAB[20] = new String[]{"COMP", "3", "28"};
        OPTAB[21] = new String[]{"COMPF", "3", "88"};
        OPTAB[22] = new String[]{"DIV", "3", "24"};
        OPTAB[23] = new String[]{"DIVF", "3", "64"};
        OPTAB[24] = new String[]{"J", "3", "3C"};
        OPTAB[25] = new String[]{"JEQ", "3", "30"};
        OPTAB[26] = new String[]{"JGT", "3", "34"};
        OPTAB[27] = new String[]{"JLT", "3", "38"};
        OPTAB[28] = new String[]{"JSUB", "3", "48"};
        OPTAB[29] = new String[]{"LDA", "3", "00"};
        OPTAB[30] = new String[]{"LDB", "3", "68"};
        OPTAB[31] = new String[]{"LDCH", "3", "50"};
        OPTAB[32] = new String[]{"LDF", "3", "70"};
        OPTAB[33] = new String[]{"LDL", "3", "08"};
        OPTAB[34] = new String[]{"LDS", "3", "6C"};
        OPTAB[35] = new String[]{"LDT", "3", "74"};
        OPTAB[36] = new String[]{"LDX", "3", "04"};
        OPTAB[37] = new String[]{"LPS", "3", "D0"};
        OPTAB[38] = new String[]{"MUL", "3", "20"};
        OPTAB[39] = new String[]{"MULF", "3", "60"};
        OPTAB[40] = new String[]{"OR", "3", "44"};
        OPTAB[41] = new String[]{"RD", "3", "D8"};
        OPTAB[42] = new String[]{"RSUB", "3", "4C"};
        OPTAB[43] = new String[]{"SSK", "3", "EC"};
        OPTAB[44] = new String[]{"STA", "3", "0C"};
        OPTAB[45] = new String[]{"STB", "3", "78"};
        OPTAB[46] = new String[]{"STCH", "3", "54"};
        OPTAB[47] = new String[]{"STF", "3", "80"};
        OPTAB[48] = new String[]{"STI", "3", "D4"};
        OPTAB[49] = new String[]{"STL", "3", "14"};
        OPTAB[50] = new String[]{"STS", "3", "7C"};
        OPTAB[51] = new String[]{"STSW", "3", "E8"};
        OPTAB[52] = new String[]{"STT", "3", "84"};
        OPTAB[53] = new String[]{"STX", "3", "10"};
        OPTAB[54] = new String[]{"SUB", "3", "1C"};
        OPTAB[55] = new String[]{"SUBF", "3", "5C"};
        OPTAB[56] = new String[]{"TD", "3", "E0"};
        OPTAB[57] = new String[]{"TIX", "3", "2C"};
        OPTAB[58] = new String[]{"WD", "3", "DC"};
        asciiChar[0] = new String[]{"A", "0"};
        asciiChar[1] = new String[]{"X", "1"};
        asciiChar[2] = new String[]{"B", "4"};
        asciiChar[3] = new String[]{"S", "5"};
        asciiChar[4] = new String[]{"T", "6"};
        asciiChar[5] = new String[]{"F", "7"};
    }
    public static void CalLocationCounter(){
        locationCounter[0] = "####";
       locationCounter[1] = referance[0];
        int decimalValue=0;
    for (int i = 2; i < rowCount; i++) {
        if("RESW".equals(instrucation[i-1])){
            //Convert Sting to int
           decimalValue =Integer.parseInt(referance[i - 1])*3;
           //Convert locationCounter[i-1] to decimal and add decimalValue
           // and after all convert to hex
           locationCounter[i]=Integer.toHexString(Integer.parseInt(locationCounter[i - 1], 16)+decimalValue).toUpperCase();
        }
        else if("BYTE".equals(instrucation[i-1])){
           //Get First char if C ot X
           char firstchar=referance[i-1].charAt(0);
           if(firstchar=='X'){
               // Remove 'X' and single quotes
               String newValue=referance[i-1].replace("X", "").replace("'", "");
               locationCounter[i]=Integer.toHexString(Integer.parseInt(locationCounter[i - 1], 16)+(newValue.length()/2)).toUpperCase();
           }
           else{
               // Remove 'C' and single quotes
            String newValue=referance[i-1].replace("C", "").replace("'", "");
               locationCounter[i]=Integer.toHexString(Integer.parseInt(locationCounter[i - 1], 16)+(newValue.length())).toUpperCase();
           }
        }
        else if("RESB".equals(instrucation[i-1])){
            
            decimalValue=Integer.parseInt(referance[i - 1]);
           locationCounter[i]=Integer.toHexString(Integer.parseInt(locationCounter[i - 1], 16)+(decimalValue)).toUpperCase();
        }
        else if("BASE".equals(instrucation[i-1])){
           locationCounter[i]=locationCounter[i-1];
        }
        else if(instrucation[i-1].contains("+")){ 
               //String to decimal
           decimalValue = Integer.parseInt(locationCounter[i - 1], 16) + 4;
            //decimal to Hex String
             locationCounter[i] = Integer.toHexString(decimalValue).toUpperCase();
        }
        else{
            for(int j=0;j<OPTAB.length;j++){
                if(instrucation[i-1].equals(OPTAB[j][0])){ 
                   if("1".equals(OPTAB[j][1])){
             decimalValue = Integer.parseInt(locationCounter[i - 1], 16) + 1;
            //decimal to Hex String
             locationCounter[i] = Integer.toHexString(decimalValue).toUpperCase();
             break;
                  }  
               else if("2".equals(OPTAB[j][1])){   
                    decimalValue = Integer.parseInt(locationCounter[i - 1], 16) + 2;
                   //decimal to Hex String
                    locationCounter[i] = Integer.toHexString(decimalValue).toUpperCase();
                    break;
                  }     
                 
                } 
                 else{ 
                      decimalValue = Integer.parseInt(locationCounter[i - 1], 16) + 3;
            //decimal to Hex String
             locationCounter[i] = Integer.toHexString(decimalValue).toUpperCase();
                } 
                
            }
          
        }
        
    }
            System.out.println("locationCounter"+ "        " + "label" + "      " + "instrucation"+ "         " + "referance");
    for (int i = 0; i < rowCount; i++) {
        while(locationCounter[i].length()!=4){
           locationCounter[i]="0"+locationCounter[i];
        }
        System.out.println(locationCounter[i] + "                   " + label[i] + "             " + instrucation[i] + "             " + referance[i]);
    }
            System.out.println("---------------------------------------------------");
    }
    public static void printSymbolTable(){
       System.out.println("Symbols"+ "        " + "locationCounter");
         for (int i = 1; i < rowCount; i++) {
            if("###".equals(label[i])){
                
            }
            else{
                System.out.println(label[i] + "                   " + locationCounter[i]);
            }
         }
           System.out.println("---------------------------------------------------");
    }
    public static void CalObjectCode(){
         System.out.println("Pass 2:");
        System.out.println("---------------------------------------------------");
        objectCode[0]="No Obeject Code";
        String test_instrucation="";
        for (int i = 1; i < rowCount; i++) {
            if(instrucation[i].contains("+")){
                String formatinstr = instrucation[i].replaceAll("\\+", ""); 
                for(int j=0;j<OPTAB.length;j++){ 
                    if(formatinstr.equals(OPTAB[j][0])){ 
                        if(OPTAB[j][1].equals("3")){
                       Format4(i,j);
                      // System.out.println(instrucation[i]+"=="+OPTAB[j][0]+"=="+formatinstr);
                   }
                    }
                      
                }
            }
            for(int j=0;j<OPTAB.length;j++){              
                if(instrucation[i].equals(OPTAB[j][0])){ 
                   if(OPTAB[j][1].equals("1")||OPTAB[j][1].equals("2")){
                       Format1and2(i,j);
                   }
                  else if(OPTAB[j][1].equals("3")){
                       Format3(i,j);
                   }   
                  }
                
                else{
                     if("RESW".equals(instrucation[i])||"RESB".equals(instrucation[i])||"BASE".equals(instrucation[i]) || "END".equals(instrucation[i])){ // Also added END
                    objectCode[i]="No Object Code";
                      }
                    else if("BYTE".equals(instrucation[i])){
                        if (referance[i].startsWith("X")) {
                                 objectCode[i] = referance[i].replace("X", "").replace("'", "");
                         } else if (referance[i].startsWith("C")) { // Added handling for C type BYTE
                             StringBuilder asciiHex = new StringBuilder();
                             String charData = referance[i].replace("C", "").replace("'", "");
                             for (char c : charData.toCharArray()) {
                                 asciiHex.append(String.format("%02X", (int) c));
                             }
                             objectCode[i] = asciiHex.toString();
                         } else {
                             objectCode[i]="Invalid BYTE format"; // Handle invalid BYTE
                         }
                    }
                     
                           }
                     }
        }
           System.out.println("Location_table            label         instarction              Refrence                Object_code");
        for (int i = 0; i < rowCount; i++) {
            System.out.println(locationCounter[i] + "                         " + label[i] + "                  " + instrucation[i] + "                    " + referance[i] + "                         " + objectCode[i]);
        }
    }
    public static void Format1and2(int i,int j){
        String opcode=OPTAB[j][2];
        String reg1="",reg2="";
        if(referance[i].length()>1){
            String format=referance[i].replace(",","");
            String char1=String.valueOf(format.charAt(0));
            String char2=String.valueOf(format.charAt(1));
            for(int k=0;k<asciiChar.length;k++){
               if(char1.equals(asciiChar[k][0])){
                    reg1=asciiChar[k][1];
                }
               if(char2.equals(asciiChar[k][0])){
                    reg2=asciiChar[k][1];
                }
            }
            objectCode[i]=opcode+reg1+reg2;
        }
        else{
            reg2="0";
            for(int k=0;k<asciiChar.length;k++){
                if(referance[i].equals(asciiChar[k][0])){
                    reg1=asciiChar[k][1];
                }
            }
            objectCode[i]=opcode+reg1+reg2;
        }
    }
    public static void Format3(int i,int j){
        String n="",ii="",x="",b="",p="",e="0",Second="",Third="",disp="";
        String opcode=hexToBinaryFirst6(OPTAB[j][2]);
        String opcode1=opcode.substring(0, 4);
        opcode1=binaryToHex(opcode1);
        String opcode2=opcode.substring(4);
        if(referance[i].contains("@")){
            n="1";
            ii="0";
        }
        else if(referance[i].contains("#")){
            n="0";
            ii="1";
        }
        else{
            n="1";
            ii="1";
        }
        Second=opcode2+n+ii;
        Second=binaryToHex(Second);
        if(referance[i].contains(",X")){
            x="1";
        }
        else{
            x="0";
        }
        String Ref=referance[i].replaceAll("@","").replaceAll("#","").replaceAll(",X","");
        if(isNumber(Ref)){
            p="0";
            b="0";
            disp=decimalToHex3Digit(Ref);
        }
        else{
            String TA="",pc="";
            for(int k=0;k<rowCount;k++){
                if(Ref.equals(label[k])){
                    TA=locationCounter[k];
                }
            }
            pc=locationCounter[i+1];
            int dispnum=0;
               dispnum=hexToDecimal(TA)-hexToDecimal(pc);   
              
            if(dispnum>=-2048&&dispnum<=2047){
                b="0";
                p="1";
                disp=decimalToHex3Digit(String.valueOf(dispnum));
            }
            else{
                dispnum=hexToDecimal(TA)-hexToDecimal(getbase());
                b="1";
                p="0";
                disp=decimalToHex3Digit(String.valueOf(dispnum));
            }
        }
        Third=binaryToHex(x+b+p+e);
        objectCode[i]=opcode1+Second+Third+disp;
    }
    public static void Format4(int i,int j) {
        String n="",ii="",x="",b="0",p="0",e="1",Second="",Third="",disp="";
        String opcode=hexToBinaryFirst6(OPTAB[j][2]);
        String opcode1=opcode.substring(0, 4);
        opcode1=binaryToHex(opcode1);
        String opcode2=opcode.substring(4);
        if(referance[i].contains("@")){
            n="1";
            ii="0";
        }
        else if(referance[i].contains("#")){
            n="0";
            ii="1";
        }
        else{
            n="1";
            ii="1";
        }
        Second=opcode2+n+ii;
        Second=binaryToHex(Second);
        if(referance[i].contains(",X")){
            x="1";
        }
        else{
            x="0";
        }
        String Ref=referance[i].replaceAll("@","").replaceAll("#","").replaceAll(",X","");
        if(isNumber(Ref)){         
            disp=decimalToHex5Digit(Ref);
        }
        else{
            for(int k=0;k<rowCount;k++){
                if(Ref.equals(label[k])){
                   disp="0"+locationCounter[k]; 
                }
            }
        }
        Third=binaryToHex(x+b+p+e);
        objectCode[i]=opcode1+Second+Third+disp;
    }
    public static String getbase(){
            for(int i=0;i<rowCount;i++){
                 if("BASE".equals(instrucation[i])){
                     for(int j=0;j<rowCount;j++){
                         if(label[j].contains(referance[i])){
                             return locationCounter[j];
                         }
                     }
                }
            }
        return "";
    } 
    public static String binaryToHex(String binaryStr) {
        try {
            // Convert binary string to decimal
            int decimal = Integer.parseInt(binaryStr, 2);
            
            // Convert decimal to hex string
            return Integer.toHexString(decimal).toUpperCase();
        } catch (NumberFormatException e) {
            return "Invalid binary input";
        }
    }
    public static String hexToBinaryFirst6(String hexStr) {
        try {
            // Convert hex string to integer
            int decimal = Integer.parseInt(hexStr, 16);

            // Convert to binary string and pad with leading zeros if needed
            String binaryStr = String.format("%" + (hexStr.length() * 4) + "s", Integer.toBinaryString(decimal)).replace(' ', '0');

            // Return the first 6 bits (or pad more if input is short)
            if (binaryStr.length() < 6) {
                binaryStr = String.format("%6s", binaryStr).replace(' ', '0');
            }

            return binaryStr.substring(0, 6);
        } catch (NumberFormatException e) {
            return "Invalid hex input";
        }
    }
    public static String decimalToHex3Digit(String decimalStr) {
            try {
                int number = Integer.parseInt(decimalStr);         // Convert to int
                String hex = Integer.toHexString(number).toUpperCase(); // Convert to hex

                // Return last 3 characters (pad with 0s if needed)
                if (hex.length() < 3) {
                    hex = String.format("%3s", hex).replace(' ', '0'); // Pad left with zeros
                }
                return hex.substring(hex.length() - 3);
            } catch (NumberFormatException e) {
                return "Invalid decimal input";
            }
    }
    public static String decimalToHex5Digit(String decimalStr) {
    try {
        int number = Integer.parseInt(decimalStr);         // Convert to int
        String hex = Integer.toHexString(number).toUpperCase(); // Convert to hex

        // Pad with leading zeros to ensure 5 digits
        return String.format("%05X", number);
    } catch (NumberFormatException e) {
        return "Invalid decimal input";
    }
}
    public static int hexToDecimal(String hexStr) {
        try {
            return Integer.parseInt(hexStr, 16);  // base 16
        } catch (NumberFormatException e) {
            return -1; // or throw exception or return 0
        }
    }
    public static boolean isNumber(String str) {
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
    public static void HTE_Record(){
        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");
        System.out.println("HTE Record:");
        HeadRecord();
        TextRecord();
        EndRecord();
     }
    public static void HeadRecord() {
    int start = Integer.parseInt(locationCounter[1], 16);
    int end = Integer.parseInt(locationCounter[rowCount - 1], 16);
    int length = end - start;

    // Format referance[0] and result to 6-digit uppercase hex
    String refFormatted = String.format("%06X", start);
    String resultFormatted = String.format("%06X", length);

    // Pad label to 6 characters if necessary
    System.out.println("H^" + "X"+label[0] + "^" + refFormatted + "^" + resultFormatted);
}
    public static void TextRecord(){
        int currentTextRecordStart = 0;
        ArrayList<String> currentObjectCodes = new ArrayList<>();
        int currentLength = 0;

        for (int i = 1; i < rowCount; i++) {
            // Skip "No Object Code" entries (RESW, RESB, END)
            if ("No Object Code".equals(objectCode[i])) {
                if (!currentObjectCodes.isEmpty()) {
                    // Print the current T-record if it has accumulated object codes
                    printSingleTextRecord(currentTextRecordStart, currentObjectCodes);
                }
                // Reset for the next T-record
                currentTextRecordStart = i + 1; // Start from the next instruction
                currentObjectCodes.clear();
                currentLength = 0;
                continue; // Skip to the next iteration
            }

            // Calculate length of current object code
            int instructionLength = objectCode[i].length() / 2; // Each two hex chars is 1 byte
            
            // If adding the current instruction exceeds 30 bytes (60 hex characters),
            // or if it's the first instruction of a new record
            // evry 2 digit 1 
            if (currentLength + instructionLength > 30 || currentObjectCodes.isEmpty()) {
                if (!currentObjectCodes.isEmpty()) {
                    // Print the accumulated T-record
                    printSingleTextRecord(currentTextRecordStart, currentObjectCodes);
                }
                // Start a new T-record
                currentTextRecordStart = i;
                currentObjectCodes.clear();
                currentLength = 0;
            }
            currentObjectCodes.add(objectCode[i]);
            currentLength += instructionLength;
        }
        // Print any remaining T-record at the end
        if (!currentObjectCodes.isEmpty()) {
            printSingleTextRecord(currentTextRecordStart, currentObjectCodes);
        }
    }
    public static void printSingleTextRecord(int startIndex, ArrayList<String> codes) {
        if (codes.isEmpty()) {
            return;
        }
        StringBuilder recordBuilder = new StringBuilder();
        int totalLength = 0;
        for (String code : codes) {
            recordBuilder.append(code);
            totalLength += code.length() / 2; // Each two hex chars is 1 byte
        }
        String startAddress = locationCounter[startIndex];
        String lengthHex = String.format("%02X", totalLength); // Length in bytes, 2 hex digits

        System.out.print("T^" + startAddress + "^" + lengthHex);
        for (String code : codes) {
            System.out.print("^" + code);
        }
        System.out.println();
    }
    public static void EndRecord() {
        // The operand of the END record is the starting address of the program
        // It should be referance[0] if it's the START address.
        String firstExecutableAddress = referance[0];
        System.out.println("E^" + String.format("%06X", hexToDecimal(firstExecutableAddress)));
    }
    public static void ModifiedRecord() {
     System.out.println("---------------------------------------------------");
     System.out.println("---------------------------------------------------");
    System.out.println("Modified Record:");
    for (int i = 0; i < rowCount; i++) {
        if (instrucation[i].contains("+")) {
            try {
                int address = Integer.parseInt(locationCounter[i], 16) + 1; // Add 1 to address
                String formattedAddress = String.format("%06X", address);  // 6-digit hex
                System.out.println("M^" +"05^"+formattedAddress ); // 5 half-bytes = 20-bit address
            } catch (NumberFormatException e) {
                System.out.println("Invalid hex in locationCounter at index " + i);
            }
        }
    }
}
}
