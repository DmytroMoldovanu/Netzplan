import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AP {
    private int nummer;
    private String beschreibung;
    private int dauer;
    private List<AP> vorgänger;
    private List<AP> nachfolger;

    // Getter-Methoden
    public int getNummer() {
        return nummer;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getDauer() {
        return dauer;
    }

    public List<AP> getVorgänger() {
        return vorgänger;
    }

    public List<AP> getNachfolger() {
        return nachfolger;
    }

    // Methode zum Hinzufügen eines Nachfolgers
    public void addNachfolger(AP ap) {
        nachfolger.add(ap);
    }

    // Konstruktor
    public AP(int nummer, String beschreibung, int dauer, List<AP> vorgänger) {
        this.nummer = nummer;
        this.beschreibung = beschreibung;
        this.dauer = dauer;
        this.vorgänger = vorgänger;
        this.nachfolger = new ArrayList<>();
        for (AP ap : vorgänger) {
            ap.addNachfolger(this);
        }
    }

    // toString-Methode für die Darstellung des APs
    public String toString() {
        StringBuilder vorgaengerNummern = new StringBuilder();
        for (AP ap : vorgänger) {
            if (vorgaengerNummern.length() > 0) {
                vorgaengerNummern.append(", ");
            }
            vorgaengerNummern.append(ap.getNummer());
        }
        return "AP = Nummer: " + nummer + "; Beschreibung: " + beschreibung + "; Dauer: " + dauer + "; FAZ: " + getFaz() + "; FEZ: " + getFez() + "; SAZ: " + getSaz() + "; SEZ: " + getSez() + "; FP: " + getFp() + "; GP: " + getGp() + "; Vorgänger: " + vorgaengerNummern.toString();
    }

    // Berechnung des frühesten Anfangszeitpunkts (FAZ)
    public int getFaz() {
        if (vorgänger == null || vorgänger.isEmpty()) {
            return 0;
        } else {
            int gefFez = 0;
            for (AP t : vorgänger) {
                int maxFez = t.getFez();
                if (maxFez > gefFez) {
                    gefFez = maxFez;
                }
            }
            return gefFez;
        }
    }

    // Berechnung des frühesten Endzeitpunkts (FEZ)
    public int getFez() {
        return getFaz() + dauer;
    }

    // Berechnung des spätesten Anfangszeitpunkts (SAZ)
    public int getSaz() {
        if (nachfolger == null || nachfolger.isEmpty()) {
            return getFez() - dauer;
        } else {
            int minSaz = Integer.MAX_VALUE;
            for (AP t : nachfolger) {
                int saz = t.getSaz();
                if (saz < minSaz) {
                    minSaz = saz;
                }
            }
            return minSaz - dauer;
        }
    }

    // Berechnung des spätesten Endzeitpunkts (SEZ)
    public int getSez() {
        return getSaz() + dauer;
    }

    // Berechnung des freien Puffers (FP)
    public int getFp() {
        if (nachfolger == null || nachfolger.isEmpty()) {
            return 0;
        } else {
            int minFaz = Integer.MAX_VALUE;
            for (AP t : nachfolger) {
                int einFaz = t.getFaz();
                if (einFaz < minFaz) {
                    minFaz = einFaz;
                }
            }
            return minFaz - getFez();
        }
    }

    // Berechnung des Gesamtpuffers (GP)
    public int getGp() {
        return getSaz() - getFaz();
    }
}