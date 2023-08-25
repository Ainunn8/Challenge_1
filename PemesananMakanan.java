import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PemesananMakanan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        double totalHarga = 0;
        double jumlahBarang = 0;
        StringBuilder struk = new StringBuilder();


        System.out.println("==========================================");
        System.out.println("        Selamat datang di BinarFund");
        System.out.println("==========================================");

        while (isRunning) {
            System.out.println("\nSilahkan pilih makanan: ");
            System.out.println("1. Nasi Goreng\t\t| Rp 15000");
            System.out.println("2. Mie Ayam\t\t\t| Rp 13000");
            System.out.println("3. Nasi + Ayam\t\t| Rp 18000");
            System.out.println("4. Es Teh Manis\t\t| Rp 3000");
            System.out.println("5. Es Jeruk\t\t\t| Rp 5000");
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih menu : ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    pesanMakanan("Nasi Goreng", 15000, totalHarga, struk, scanner);
                    break;
                case 2:
                    pesanMakanan("Mie Ayam", 13000, totalHarga, struk, scanner);
                    break;
                case 3:
                    pesanMakanan("Nasi + Ayam", 18000, totalHarga, struk, scanner);
                    break;
                case 4:
                    pesanMakanan("Es Teh Manis", 3000, totalHarga, struk, scanner);
                    break;
                case 5:
                    pesanMakanan("Es Jeruk", 5000, totalHarga, struk, scanner);
                    break;
                case 99:
                    pesanDanBayar(totalHarga, jumlahBarang , struk, scanner);
                    isRunning = false;
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

        }

        System.out.println("\nTerima kasih telah menggunakan layanan kami!");
        scanner.close();
    }

    public static void pesanMakanan(String namaMakanan, double hargaMakanan, double totalHarga, StringBuilder struk, Scanner scanner) {


        System.out.println("==========================================");
        System.out.println("\t\tBerapa pesanan anda");
        System.out.println("==========================================");
        System.out.println(namaMakanan +  " | Rp " + hargaMakanan);
        System.out.println("input 0 untuk kembali");
        System.out.print("qty => " );
        int jumlahBarang = scanner.nextInt();
        double subTotal = hargaMakanan * jumlahBarang;

        totalHarga += subTotal;

        struk.append(namaMakanan).append("\t\t").append(jumlahBarang).append(" \t Rp ").append(totalHarga).append("\n");
        System.out.println("Pesanan " + namaMakanan + " sebanyak " + jumlahBarang + " berhasil ditambahkan.");

    }

    public static void pesanDanBayar(double jumlahBarang, double totalHarga,  StringBuilder struk, Scanner scanner) {

        System.out.println("==========================================");
        System.out.println("\t\tKonfirmasi & Pembayaran");
        System.out.println("==========================================");
        System.out.println(struk);
        System.out.println("----------------------------------------  +");
        System.out.println("Total \t\t " + jumlahBarang+ "\t\t" + totalHarga);


        System.out.print("Masukkan jumlah uang Anda: Rp ");
        double jumlahUang = scanner.nextDouble();

        if (jumlahUang < totalHarga) {
            System.out.println("Maaf, uang Anda tidak cukup.");
        } else {
            double kembalian = jumlahUang - totalHarga;
            System.out.println("Kembalian Anda: Rp " + kembalian);


            // Simpan struk ke file
            try {
                FileWriter writer = new FileWriter("struk_pembayaran.txt");
                writer.write("==========================================\n");
                writer.write("\t Binarfud\n");
                writer.write("==========================================\n");

                writer.write("Terima kasih sudah memesan di Binarfud\n");
                writer.write("Dibawah ini adalah pesanan anda\n\n");
                writer.write(struk.toString());
                writer.write("----------------------------------------- +\n");
                writer.write("Total Rp\t\t " + jumlahBarang + "\t"+ totalHarga + "\n\n");

                writer.write("==========================================\n");
                writer.write("Simpan struk ini sebagai bukti pembayaran\n");
                writer.write("==========================================\n");
                writer.close();
                System.out.println("Struk pembayaran telah disimpan dalam file 'struk_pembayaran.txt'.");
            } catch (IOException e) {
                System.out.println("Terjadi kesalahan saat menyimpan struk pembayaran.");
            }

        }
    }
}