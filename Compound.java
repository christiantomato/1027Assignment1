public class Compound {
    //instance variables
    Element[] elements;
    int[] elementCount;

    //constructor
    public Compound(PeriodicTable table, String[][] compoundArray) {
        //init the size
        this.elements = new Element[compoundArray.length];
        this.elementCount = new int[compoundArray.length];

        //populate our instance variables based on the definitino given for compoundArray
        for(int i = 0; i < compoundArray.length; i++) {
            //iterate over length of nested arrays (cause they might be 1 or 2 we don't know)
            for(int j = 0; j < compoundArray[i].length; j++) {
                //on first iteration figure out element
                if(j == 0) {
                    //figure out element
                    String symbol = compoundArray[i][j];
                    this.elements[i] = table.getElement(symbol);
                }
                //make sure to set value for default case
                if(compoundArray[i].length == 1) {
                    //do default value of 1
                    elementCount[i] = 1;
                }
                //if on 2nd iteration
                if(j == 1) {
                    //parse to int and add to array
                    int atoms = Integer.parseInt(compoundArray[i][j]);
                    this.elementCount[i] = atoms;
                }
            }
        }
    }

    //get bond type
    public String getBondType() {
        //are there 2 elements in the compound
        if(this.elements.length == 2) {
            //determine bond
            String type1 = this.elements[0].getType();
            String type2 = this.elements[1].getType();

            if((type1.equals("Metal") && type2.equals("Nonmetal")) || (type1.equals("Nonmetal") && type2.equals("Metal"))) {
                //ionic
                return "ionic";
            }
            else if((type1.equals("Metal") && type2.equals("Metalloid")) || (type1.equals("Metalloid") && type2.equals("Metal"))) {
                //covalent
                return "covalent";
            }
            else if(type1.equals("Nonmetal") && type2.equals("Nonmetal")) {
                //covalent
                return "covalent";
            }
            else{
                //null
                return null;
            }
        }
        else{
            return null;
        }
    }

    //toString
    public String toString() {
        String s = "";
        for(int i = 0; i < this.elements.length; i++) {
            s += this.elements[i].getName() + ": " + this.elementCount[i] + "\n";
        }

        return s;
    }

    
     public static void main(String[] args) {
        PeriodicTable table = new PeriodicTable("elements.txt");
        String[][] compounds = {{"Mg", "1"}, {"Cl", "5"}};
        Compound mine = new Compound(table, compounds);
        System.out.println(mine);
        System.out.println(mine.getBondType());
    }
     
    
}
