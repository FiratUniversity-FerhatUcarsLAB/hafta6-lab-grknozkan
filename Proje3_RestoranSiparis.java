//Öğrenci Adı Soyadı:[Gürkan Özkan]
//Öğrenci No:[250542023]
//Ödev No:[Ödev-3:Restoran Şiparişi]
//Ödev Açıklaması:[sipariş almak için düzlenlenen sistemde ana yemekler,içecek fiyatı ve tatlı fiyatlarını numaralandırıp öğrenci indirimi var mı yok mu sorusunu soran java kodu]

import java.util.Scanner;

public class RestoranSiparis {

    // 1) Ana Yemek Fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç Fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;
        }
    }

    // 3) İçecek Fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {

            case 1: return 15;  // Kola
            case 2: return 12;  // Ayran
            case 3: return 35;  // Taze Meyve Suyu
            case 4: return 25;  // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı Fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;   // Künefe
            case 2: return 55;   // Baklava
            case 3: return 35;   // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo Menüsü mü?
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6) Happy Hour mü? (14-17)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) İndirim Hesabı
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, boolean happyHour, double icecekFiyati) {
        double indirim = 0;

        // COMBO %15
        if (combo) {
            indirim += tutar * 0.15;
        }

        // Happy hour → içeceklerde %20
        if (happyHour) {
            indirim += icecekFiyati * 0.20;
        }

        // 200 TL üzeri %10
        if (tutar >= 200) {
            indirim += tutar * 0.10;
        }

        // Öğrenci hafta içi %10
        if (ogrenci) {
            indirim += tutar * 0.10;
        }

        return indirim;
    }

    // 8) Bahşiş (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // MENÜ SEÇİMLERİ
        System.out.print("Ana Yemek (0=Yok, 1-4): ");
        int ana = sc.nextInt();

        System.out.print("Başlangıç (0=Yok, 1-3): ");
        int bas = sc.nextInt();

        System.out.print("İçecek (0=Yok, 1-4): ");
        int icecek = sc.nextInt();

        System.out.print("Tatlı (0=Yok, 1-3): ");
        int tatli = sc.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.print("Öğrenci misiniz? (1=Evet, 0=Hayır): ");
        boolean ogrenci = sc.nextInt() == 1;

        // FİYATLAR
        double fAna = getMainDishPrice(ana);
        double fBas = getAppetizerPrice(bas);
        double fIcecek = getDrinkPrice(icecek);
        double fTatli = getDessertPrice(tatli);

        // TOPLAM TEMEL TUTAR
        double toplam = fAna + fBas + fIcecek + fTatli;

        // DURUMLAR
        boolean combo = isComboOrder(ana != 0, icecek != 0, tatli != 0);
        boolean happyHour = isHappyHour(saat);

        // İNDİRİM
        double indirim = calculateDiscount(toplam, combo, ogrenci, happyHour, fIcecek);
        double odenen = toplam - indirim;

        // Garson bahşiş önerisi
        double bahsis = calculateServiceTip(odenen);

        // FİŞ
        System.out.println("\n--- SİPARİŞ ÖZETİ ---");
        System.out.printf("Ana Yemek: %.2f TL\n", fAna);
        System.out.printf("Başlangıç: %.2f TL\n", fBas);
        System.out.printf("İçecek: %.2f TL\n", fIcecek);
        System.out.printf("Tatlı: %.2f TL\n", fTatli);

        System.out.println("---------------------------");
        System.out.printf("Toplam Tutar: %.2f TL\n", toplam);
        System.out.printf("İndirimler: %.2f TL\n", indirim);
        System.out.printf("Ödenecek Tutar: %.2f TL\n", odenen);
        System.out.printf("Bahşiş Önerisi (%%10): %.2f TL\n", bahsis);

        System.out.println("---------------------------");
        System.out.println("Combo Menü: " + (combo ? "Evet" : "Hayır"));
        System.out.println("Happy Hour: " + (happyHour ? "Evet" : "Hayır"));
        System.out.println("Öğrenci: " + (ogrenci ? "Evet" : "Hayır"));

        sc.close();
    }
}
