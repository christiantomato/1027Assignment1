public class PeriodicTable {
    //instance variables
    private Element[][] mainTable;
    private Element[] lanthanides;
    private Element[] actinides;

    //constructor
    public PeriodicTable(String filename) {
        //initialize all 3 arrays with capacities
        mainTable = new Element[7][18];
        lanthanides = new Element[15];
        actinides = new Element[15];

        //call to loadData()
        loadData(filename);
    }

    //method to get data from file and construct each element
    public void loadData(String filename) {
        //where all the elements will be stored (118 total elements)
        Element[] allElements = new Element[118];

        //utilize TextFileReader class
        TextFileReader reader = new TextFileReader(filename);

        //read data until end of file
        boolean skipHeader = true;
        while(!reader.endOfFile()) {
            //read in line
            String line = reader.readString();
            //split by the delimiter (there are 28 properties)
            String[] splitLine = new String[28];
            splitLine = line.split(",");

            //indexes needed: atNo.0,Elem.1,Sym.2,atMa.3,per.8,gr.9,ph.10,met.13,nonmet.14,metoid.15
            //determine type
            int typeIndex = 0;

            for(int i = 13; i < 16; i++) {
                if(splitLine[i].equals("yes")) {
                    //record index
                    typeIndex = i;
                }
            }

            //create the element (but skip header)
            if(skipHeader) {
                skipHeader = false;
                continue;
            }
            Element currentElement = new Element(Integer.parseInt(splitLine[0]), Float.parseFloat(splitLine[3]), splitLine[2], splitLine[1], splitLine[10], splitLine[typeIndex]);

            //add it to our array
            allElements[currentElement.getAtomicNo()-1] = currentElement;
        }

        //add to initialized arrays, start with easy parts

        //lanthanides
        for(int i = 56, j = 0; i < 71; i++, j++) {
            //put into lanthanides
            this.lanthanides[j] = allElements[i];
        }

        //actinides
        for(int i = 88, j = 0; i < 103; i++, j++) {
            //put into actinides
            this.actinides[j] = allElements[i];
        }

        //now onto the hard 2D array

        //loop through main table (i = row, j = column)
        int eleIndex = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 18; j++) {
                //do it by period
                if(i == 0) {
                    if(j == 0) {
                        //hydrogen case
                        this.mainTable[i][j] = allElements[eleIndex];
                        eleIndex++;
                    }
                    else if(j == 17) {
                        //helium case
                        this.mainTable[i][j] = allElements[eleIndex];
                        eleIndex++;
                    }
                    else {
                        //add null for empty spots
                        this.mainTable[i][j] = null;
                    }
                }
                //period 2 and 3 (same logic)
                else if(i == 1 || i == 2) {
                    if(j < 2) {
                        //Li,Be OR Na,Mg
                        this.mainTable[i][j] = allElements[eleIndex];
                        eleIndex++;
                    }
                    else if(j > 11) {
                        //B,C,N,,F,Ne OR Al,Si,P,S,Cl,Ar
                        this.mainTable[i][j] = allElements[eleIndex];
                        eleIndex++;
                    }
                    else {
                        this.mainTable[i][j] = null;
                    }
                }
                //period 4 and 5 (same logic)
                else if(i == 3 || i == 4) {
                    //easy case
                    this.mainTable[i][j] = allElements[eleIndex];
                    eleIndex++;
                }
                //period 6 (lanthanide gap)
                else if(i == 5) {
                    if(j == 2) {
                        //only gap
                        this.mainTable[i][j] = null;
                        //go to new index (Hafnium)
                        eleIndex = 71;
                    }
                    else{
                        //normal
                        this.mainTable[i][j] = allElements[eleIndex];
                        eleIndex++;
                    }
                }
                //period 7 (actinide gap)
                else if(i == 6) {
                    if(j == 2) {
                        //only gap
                        this.mainTable[i][j] = null;
                        //go to new index (Rutherfordium)
                        eleIndex = 103;
                    }
                    else{
                        //normal
                        this.mainTable[i][j] = allElements[eleIndex];
                        eleIndex++;
                    }
                }
            }
        }
    }

    //toString
    public String toString() {
        String str = "";

        //loop through main table
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 18; j++) {
                //print out symbol only, for null make space
                if(this.mainTable[i][j] == null) {
                    //4 spaces
                    str += "    ";
                }
                else {
                    //determine if symbol is 2 or 3 letters
                    if(this.mainTable[i][j].getSymbol().length() == 1) {
                        //3 spaces
                        str += "   " + this.mainTable[i][j].getSymbol();

                    }
                    else {
                        //2 spaces
                        str += "  " + this.mainTable[i][j].getSymbol();
                    }
                }
            }
            //create new line each period
            str += "\n";
        }

        //lanthanides and actinides
        str += "\n";
        //add space in front
        str += "            ";
        for(int i = 0; i < this.lanthanides.length; i++) {
            //determine if symbol is 2 or 3 letters
            if(this.lanthanides[i].getSymbol().length() == 1) {
                //3 spaces
                str += "   " + this.lanthanides[i].getSymbol();
            }
            else {
                //2 spaces
                str += "  " + this.lanthanides[i].getSymbol();
            }
        }
        str += "\n";
        //add space in front
        str += "            ";
        for(int i = 0; i < this.actinides.length; i++) {
            //determine if symbol is 2 or 3 letters
            if(this.actinides[i].getSymbol().length() == 1) {
                //3 spaces
                str += "   " + this.actinides[i].getSymbol();
            }
            else {
                //2 spaces
                str += "  " + this.actinides[i].getSymbol();
            }
        }
        return str;
    }

    public static void main(String[] args) {
        PeriodicTable mine = new PeriodicTable("elements.txt");
        System.out.println(mine);

    }
}   
