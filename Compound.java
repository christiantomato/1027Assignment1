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

    public static void main(String[] args) {
        PeriodicTable table = new PeriodicTable("elements.txt");
        String[][] compounds = {{"H", "2"}, {"O", "1"}};
        Compound mine = new Compound(table, compounds);
        System.out.println(mine.elements[0]);
        System.out.println(mine.elementCount[0]);
        System.out.println(mine.elements[1]);
        System.out.println(mine.elementCount[1]);
    }
}
