import java.util.Date;
import java.util.Scanner;

public class Task {


    public static void main(String[] args) {

//        ArrayList<String> input = new ArrayList<>();
//
//        input.add("78782622160310041630c8031491680845e1c01b148101940a027b0030ae010200008e152e029ca5270d0a");
//        input.add("78782622160310041724cb031484140845e2f01b14d701940a027b00487d010000008e164d02a29be60d0a");
//        input.add("78782622160310041811cd03147afc0845dad003140001940a020f007e47010400008e170502a67a610d0a");
//
//        List<String> acc = Arrays.asList("78780a13460603000202a56a2d0d0a", "78780a134606040002029de73a0d0a");

//        input.stream().forEach(Task::splitStr);
//        acc.stream().forEach(Task::Acc);
        Scanner sc = new Scanner(System.in);
        String packet;
        do {
            System.out.print("Enter packet : ");
             packet = sc.nextLine();

            getData(packet);
        } while (!packet.equalsIgnoreCase("exit"));


    }

    static void getData(String packet) {
        if (packet.equalsIgnoreCase("exit")) return;
        switch (packet.substring(6, 8)) {
            case "26":
                System.out.println("\n\nAlarm Packet");
                System.out.println("latti : " + hexToDecimalDou(packet.substring(22, 30)) / 1800000);
                System.out.println("longi : " + hexToDecimalDou(packet.substring(30, 38)) / 1800000);
                if (Integer.toBinaryString(hexToDecimal(packet.substring(62, 64))).startsWith("100", 1)) {
                    System.out.println("SOS Present");
                } else System.out.println("SOS Not Present");
                break;
            case "13":
                String st = "00000000";
                System.out.println("\n\nHB packet");
                String binaryString = Integer.toBinaryString(hexToDecimal(packet.substring(8, 10)));
                System.out.println("Acc : " + binaryString.substring(binaryString.length() - 2, binaryString.length() - 1));

                System.out.print("Battery Status : ");
                switch (packet.substring(10, 12)) {
                    case "00":
                        System.out.println("No Power (shutdown)");
                        break;
                    case "01":
                        System.out.println("Extremely Low Battery");
                        break;
                    case "02":
                        System.out.println("Very Low Battery (Low Battery Alarm)");
                        break;
                    case "03":
                        System.out.println("Low Battery (can be used normally)");
                        break;
                    case "04":
                        System.out.println("Medium");
                        break;
                    case "05":
                        System.out.println("High");
                        break;
                    case "06":
                        System.out.println("Full");
                        break;
                }

                break;
            case "22":
                System.out.println("\n\nGPS Carrier Location packet");
                System.out.println("latti : " + hexToDecimalDou(packet.substring(22, 30)) / 1800000);
                System.out.println("longi : " + hexToDecimalDou(packet.substring(30, 38)) / 1800000);
                System.out.println("speed : " + hexToDecimal(packet.substring(38, 40)));
                Date d = new Date();
                d.setYear(hexToDecimal(packet.substring(8, 10)));
                d.setMonth(hexToDecimal(packet.substring(10, 12)));
                d.setDate(hexToDecimal(packet.substring(12, 14)));
                d.setHours(hexToDecimal(packet.substring(14, 16)));
                d.setMinutes(hexToDecimal(packet.substring(16, 18)));
                d.setSeconds(hexToDecimal(packet.substring(18, 20)));
                System.out.println(d);
                break;
            case "01":
                System.out.println("\n\nLogin Packet");
                System.out.println("IMEI : " + packet.substring(8, 24));
                break;
            default:
                System.out.println("Wrong packet");
        }
    }


//    public static void splitStr(String st) {
//        System.out.println("\n\nData ");
//        System.out.println("date :  year " + hexToDecimal(st.substring(8, 10)) + " Month : " + hexToDecimal(st.substring(10, 12)) + " day : " + hexToDecimal(st.substring(12, 14)) + " Hour : " + hexToDecimal(st.substring(14, 16)) + " mint : " + hexToDecimal(st.substring(16, 18)) + " sec : " + hexToDecimal(st.substring(18, 20)));
//        System.out.println("latti : " + hexToDecimalDou(st.substring(22, 30)) / 1800000);
//        System.out.println("longi : " + hexToDecimalDou(st.substring(30, 38)) / 1800000);
//        System.out.println("speed : " + hexToDecimal(st.substring(38, 40)));
//
//    }
//
//    public static void Acc(String st) {
//        System.out.println("\n\nData ");
//        System.out.println("Acc : " + Integer.toBinaryString(hexToDecimal(st.substring(8, 10))).substring(5, 6));
//        System.out.println("Info : " + hexToDecimal(st.substring(8, 10)));
//    }

    public static int hexToDecimal(String hexnum) {
        String hstring = "0123456789ABCDEF";
        hexnum = hexnum.toUpperCase();
        int num = 0;
        for (int i = 0; i < hexnum.length(); i++) {
            char ch = hexnum.charAt(i);
            int n = hstring.indexOf(ch);
            num = 16 * num + n;
        }
        return num;
    }

    public static double hexToDecimalDou(String hexnum) {
        String hstring = "0123456789ABCDEF";
        hexnum = hexnum.toUpperCase();
        double num = 0;
        for (int i = 0; i < hexnum.length(); i++) {
            char ch = hexnum.charAt(i);
            int n = hstring.indexOf(ch);
            num = 16 * num + n;
        }
        return num;
    }


}

