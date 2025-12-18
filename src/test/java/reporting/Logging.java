package reporting;



public class Logging {

    public static void writeConsoleLog(String item){
        System.out.println(java.time.LocalDateTime.now()+ " [LOG] " + item.toUpperCase());
    }

    public static void writeConsoleLog(String item,Boolean lower){
        if (lower){
            System.out.println(java.time.LocalDateTime.now()+ " [LOG] " + item);
        } else {
            writeConsoleLog(item);
        }
    }

}
