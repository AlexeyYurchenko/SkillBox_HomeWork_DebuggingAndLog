public class CustomerException extends Exception {

    private final int DATA;

    public CustomerException(int data) {
        this.DATA = data;
    }

    final static public int OVER_FOUR = 0;

    final static public int LESS_FOUR = 1;

    final static public int THIS_IS_NOT_AN_EMAIL = 2;

    final static public int THIS_IS_NOT_A_NUMBER = 3;

    public int getDATA() {
        return DATA;
    }
}
