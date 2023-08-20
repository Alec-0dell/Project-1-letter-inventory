public class LetterInventory {  


    private int size;
    private int[] characters;
    private static final int ALPHABET_LENGTH = 26;

    
    /** LetterInventory(String data)
     * Constructs an inventory of the alphabetic letters in the given string data, while also updating size and isEmpty
     * @param data
     */
    public LetterInventory(String data){
        characters = new int[ALPHABET_LENGTH];
        for (int index = 0; index < data.length(); index++) {
            char letter = data.toLowerCase().charAt(index);
            if(Character.isLetter(letter)){
                characters[letter - 'a']++;
                size++;
            }
        }
    }


    /** int get(char letter) 
     * Returns a count of how many of this letter are in the inventory.
     * @param letter
     * @return a count of number of this letter that is in the inventory
     */
    public int get(char letter){
        char lowerLetter = Character.toLowerCase(letter);
        if(!Character.isLetter(lowerLetter)){
            throw new IllegalArgumentException();
        } else {
            return characters[lowerLetter - 'a'];
        }
    }


    /** void set(char letter, int value)
     * Sets the count for the given letter to the given value.
     * @param letter
     * @param value
     */
    public void set(char letter, int value){
        char lowerLetter = Character.toLowerCase(letter);
        if(!Character.isLetter(lowerLetter) || value < 0){
            throw new IllegalArgumentException();
        } else {
            characters[lowerLetter - 'a'] = value;
        }
    }


    /** int size()
     * Returns the sum of all of the counts in this inventory.
     * @return the sum of all the counts in this inventory 
     */
    public int size(){
        return size;
    }


    /** boolean isEmpty()
     * Returns true if this inventory is empty (all counts are 0)
     * @return state of vairable isEmpty
     */
    public boolean isEmpty(){
        return(size == 0);
    }


    /** toString for LetterInventory
     * @return a string form of this letter inventory
     */
    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        String output = "[";
        for (int index = 0; index < characters.length; index++) {
            for(int numLetters = 0; numLetters < characters[index]; numLetters++){
                char letter = (char)(index + 'a');
                output += letter;
            }
        }
        return output + "]";
    }


    /** LetterInventory add(LetterInventory other)
     * Constructs and returns a new `LetterInventory` object that represents the sum of this letter inventory and the other given `LetterInventory`
     * @param other
     * @return a new LetterInvetory that was the result of the addition
     */
    public LetterInventory add(LetterInventory other){
        return calculate(other,1);
    }


    /** LetterInventory subtract(LetterInventory other)
     * Constructs and returns a new `LetterInventory` object that represents the result of subtracting the other inventory from this inventory
     * @param other
     * @return a new LetterInventory that was the result of the addition
     */
    public LetterInventory subtract(LetterInventory other){
        return calculate(other,-1);
    }


    /** LetterInventory calculate( LetterInventory other, int type)
     * @brief Calcuate is used to add or subtract Letter inventories, based on the value of type it will either subtract or add. 
     *     ...If type is -1 it will subtract or if type is 1 it will add.
     * @param other
     * @param type
     * @return the resultant added or subtracted Letterinvetory
     */
    private LetterInventory calculate(LetterInventory other,int type){
        String output = "";
        for (int index = 0; index < characters.length; index++) {
            if(this.characters[index] + (other.characters[index])*(type) < 0){
                return null;
            } else {
                for(int numLetters = 0; numLetters < this.characters[index] + (other.characters[index])*(type); numLetters++ ){
                    char letter = (char)(index + 'a');
                    output += letter;
                }
            }
        }
        return new LetterInventory(output);
    }
    
}
