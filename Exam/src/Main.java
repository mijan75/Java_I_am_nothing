public class Main {
    public Main() {
        System.out.println("Ok");
        Conection connection = Singleton.getConnection();
    }

    public static void main(String [] args){
        new Main();
    }
}
