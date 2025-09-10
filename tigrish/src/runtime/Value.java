package runtime;

/**
 * wau to represent a value
 */
public class Value implements ValueI{
    private int num;
    public Value(){
        num = 0;
    }

    /**
     * Value constructor
     * @param num the number of the value
     */
    public Value(int num){
        this.num = num;
    }
    /**
     * Get the value of this node
     *
     * @return the integer value of this node
     */
    public int getValue() {
        return num;
    }
}
