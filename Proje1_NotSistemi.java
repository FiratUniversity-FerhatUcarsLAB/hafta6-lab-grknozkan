//Öğrenci Adı Soyadı:[Gürkan Özkan]
//Öğrenci No:[250542023]
//Tarih:[19.11.2025]
//Ödev No:[Ödev-1:Not Sistemi]
//Ödev Açıklaması:[vize,final ve ödev notlarını alarak harf metodu,büte girebilme hakkı ve durumu yazar.]

import java.util.Scanner;

public class NotSistemi {

    // Ortalama Hesaplama Metodu
    public static double calculateAverage(double vize, double fin, double odev) {
        return (vize * 0.30) + (fin * 0.40) + (odev * 0.30);
    }

    // Geçti mi? (≥50)
    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    // Harf Notu Metodu
    public static String getLetterGrade(double ort) {
        if (ort >= 90) return "A";
        else if (ort >= 80) 
          return "B";
        else if (ort >= 70) 
          return "C";
        else if (ort >= 60) 
          return "D";
        else return "F";
    }

    // Onur Listesi (Ort ≥ 85 ve hiçbir not < 70 olmamalı)
    public static boolean isHonorList(double ort, double vize, double fin, double odev) {
        return (ort >= 85) && (vize >= 70) && (fin >= 70) && (odev >= 70);
    }

    // Bütünleme hakkı (40 ≤ ort < 50)
    public static boolean hasRetakeRight(double ort) {
        return (ort >= 40 && ort < 50);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Girdiler
        System.out.print("Vize notu: ");
        double vize = input.nextDouble();

        System.out.print("Final notu: ");
        double fin = input.nextDouble();

        System.out.print("Ödev notu: ");
        double odev = input.nextDouble();

        // Ortalama
        double ortalama = calculateAverage(vize, fin, odev);

        // Sonuçlar
        System.out.printf("\n--- NOT RAPORU ---\n");
        System.out.printf("Ortalama: %.2f\n", ortalama);
        System.out.println("Harf Notu: " + getLetterGrade(ortalama));

        // Geçti mi?
        if (isPassingGrade(ortalama)) {
            System.out.println("Durum: GEÇTİ");
        } else {
            System.out.println("Durum: KALDI");
        }

        // Onur Listesi kontrolü
        if (isHonorList(ortalama, vize, fin, odev)) {
            System.out.println("Onur Listesi: EVET");
        } else {
            System.out.println("Onur Listesi: HAYIR");
        }

        // Bütünleme hakkı
        if (!isPassingGrade(ortalama) && hasRetakeRight(ortalama)) {
            System.out.println("Bütünleme Hakkı: VAR");
        } else {
            System.out.println("Bütünleme Hakkı: YOK");
        }

        input.close();
    }
}

