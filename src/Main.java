import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Scanner für Benutzereingaben
    static Scanner scanner = new Scanner(System.in);
    // Liste von AP-Objekten
    static List<AP> packet = new ArrayList<>();
    // Variablen für die Eingabe
    static int zahl;
    static String beschreibung;
    static int dauer;

    public static void main(String[] args) {
        // Methode zum Anzeigen des Musterausdrucks
        MusterAusdrück();
        // Methode für das Menü
        menu();
    }

    public static void MusterAusdrück() {
        // Anzeige des Netzplan-Musters
        System.out.println("   Netzplanersteller   \n" +
                "FAZ                 FEZ\n" +
                "_______________________\n" +
                "|       Vorgang       |\n" +
                "_______________________\n" +
                "|Dauer |  GP   |    FP|\n" +
                "_______________________\n" +
                "SAZ                 SEZ\n" +
                "\nFAZ = Frühester AnfangsZeitpunkt\n" +
                "FEZ = Frühester EndZeitpunkt\n" +
                "SAZ = Spätester AnfangsZeitpunkt\n" +
                "SEZ = Spätester EndZeitpunkt\n" +
                "FP = Freier Puffer\n" +
                "GP = Gesamtpuffer");
    }

    public static void menu() {
        // Endlosschleife für das Menü
        while (true) {
            // Anzeige des Menüs
            System.out.println("\nMenu:\n" +
                    "1.Neues AP erstellen \n" +
                    "2.Alle AP anzeigen\n" +
                    "3.Exiting..");
            int i;

            // Einlesen der Benutzerauswahl
            i = scanner.nextInt();

            if (i == 1) {
                // Neues AP erstellen
                System.out.println("");
                System.out.println("AP Nummer: ");
                zahl = scanner.nextInt();

                scanner.nextLine(); // Puffer leeren
                System.out.println("AP Beschreibung: ");
                beschreibung = scanner.nextLine();

                System.out.println("Dauer: ");
                dauer = scanner.nextInt();

                scanner.nextLine(); // Puffer leeren
                System.out.println("Vorgänger Nummer (mit Komma): ");
                String input = scanner.nextLine();

                List<AP> vorgänger = new ArrayList<>();

                // Verarbeiten der Vorgänger-Nummern
                String[] numbers = input.split(",");
                for (String number : numbers) {
                    try {
                        int num = Integer.parseInt(number.trim());
                        for (AP ap : packet) {
                            if (ap.getNummer() == num) {
                                vorgänger.add(ap);
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Falsch " + number);
                    }
                }
                System.out.println("Gespeichert für AP: " + zahl);
                System.out.println("");
                AP ap = new AP(zahl, beschreibung, dauer, vorgänger);

                packet.add(ap);

                for (AP t : packet) {
                    System.out.println(t);
                }
            }else if (i == 2) {
                for (AP ap : packet) {
                    System.out.println(ap);
                }
            }else if (i == 3){
                // Beenden des Programms
                System.out.println("Exiting..");
                break;
            }
            else{
                System.out.println("Fehler! Bitte wählen Sie aus dem Menü die Optionen 1 bis 3 aus.");
            }
        }
    }
}