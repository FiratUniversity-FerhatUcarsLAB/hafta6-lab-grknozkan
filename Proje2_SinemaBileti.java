//Öğrenci Adı Soyadı:[Gürkan Özkan]
//Öğrenci No:[250542023]
//Ödev No:[Ödev-2:Sinema Bileti]
//Ödev Açıklaması:[Sinema bileti fıyatını hesaplayan java kodu yazdık saatini,hangi gün,hangi tür(2d,3d vs.),yaşını ve mesleğini sorup ona göre fiyat tarifesini yazıyor]

import java.util.Scanner;

public class SinemaBileti {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7; // Cumartesi(6) - Pazar(7)
    }

    // 2) Matine (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat: gün + saat
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;       // Hafta içi matine
        else if (!weekend) return 65;            // Hafta içi normal
        else if (weekend && matinee) return 55;  // Hafta sonu matine
        else return 85;                           // Hafta sonu normal
    }

    // 4) İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun, double basePrice) {

        // 65 yaş indirimi (%30)
        if (yas >= 65) {
            return basePrice * 0.30;
        }

        // 12 yaş altı (%25)
        if (yas < 12) {
            return basePrice * 0.25;
        }

        // Meslek seçimi
        switch (meslek) {

            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) { // Pzt–Perş (%20)
                    return basePrice * 0.20;
                } else { // Cuma–Pazar (%15)
                    return basePrice * 0.15;
                }

            case 2: // Öğretmen
                if (gun == 3) { // Çarşamba (%35)
                    return basePrice * 0.35;
                }
                return 0;

            default: // Diğer
                return 0;
        }
    }

    // 5) Film formatı ekstra fiyat
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6) Nihai fiyat hesaplama
    public static double calculateFinalPrice(double base, double discount, double extra) {
        return (base - discount) + extra;
    }

    // 7) Bilet bilgisi yazdırma
    public static void generateTicketInfo(
            int gun, int saat, int yas, int meslek, int filmTuru,
            double base, double disc, double extra, double total
    ) {
        System.out.println("\n--- BİLET BİLGİSİ ---");
        System.out.println("Gün: " + gun);
        System.out.println("Saat: " + saat);
        System.out.println("Yaş: " + yas);
        System.out.println("Meslek: " + meslek);
        System.out.println("Film Türü: " + filmTuru);

        System.out.printf("Temel Fiyat: %.2f TL\n", base);
        System.out.printf("İndirim: %.2f TL\n", disc);
        System.out.printf("Format Ekstra: %.2f TL\n", extra);
        System.out.printf("Toplam Fiyat: %.2f TL\n", total);
    }

    // MAIN
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Gün (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.print("Yaş: ");
        int yas = sc.nextInt();

        System.out.print("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = sc.nextInt();

        System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = sc.nextInt();

        // Hesaplamalar
        double base = calculateBasePrice(gun, saat);
        double disc = calculateDiscount(yas, meslek, gun, base);
        double extra = getFormatExtra(filmTuru);
        double total = calculateFinalPrice(base, disc, extra);

        // Bilgi yazdır
        generateTicketInfo(gun, saat, yas, meslek, filmTuru, base, disc, extra, total);

        sc.close();
    }
}
