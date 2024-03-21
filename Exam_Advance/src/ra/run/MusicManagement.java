package ra.run;

import ra.model.Singer;
import ra.model.Song;

import java.util.Scanner;

public class MusicManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        Singer singer = new Singer();
        Song song = new Song(singer);
        while (true) {
            System.out.println("1. Quản lý ca sĩ");
            System.out.println("2. Quản lý bài hát");
            System.out.println("3. Tìm kiếm bài hát(search by nameSong or songDescriptions)");
            System.out.println("4. Thoát");
            System.out.println("Mời bạn chọn chức năng:");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:

                    boolean continueSingerManagement = true;
                    while (continueSingerManagement) {
                        System.out.println("**********Singer Management**********");
                        System.out.println("1. Thêm ca sĩ");
                        System.out.println("2. Hiển thị danh sách ca sĩ");
                        System.out.println("3. Sửa thông tin ca sĩ");
                        System.out.println("4. Xóa ca sĩ");
                        System.out.println("5. Thoát");
                        System.out.println("Mời bạn chọn chức năng:");
                        int choiceSinger = Integer.parseInt(scanner.nextLine());
                        switch (choiceSinger) {
                            case 1:
                                singer.addData(scanner);
                                break;
                            case 2:
                                singer.showData();
                                break;
                            case 3:
                                singer.updateSinger(scanner);
                                break;
                            case 4:
                                singer.deleteSinger(scanner);
                                break;
                            case 5:
                                continueSingerManagement = false;
                                break;
                            default:
                                System.out.println("Chức năng không tồn tại");
                        }
                    }
                    break;
                case 2:

                    boolean continueSongManagement = true;
                    while (continueSongManagement) {
                        System.out.println("**********Song Management**********");
                        System.out.println("1. Thêm bài hát");
                        System.out.println("2. Hiển thị bài hát đang lưu trữ");
                        System.out.println("3. Sửa bài hát");
                        System.out.println("4. Xóa bài hát");
                        System.out.println("5. Thoát");
                        System.out.println("Mời bạn chọn chức năng:");
                        int choiceSong = Integer.parseInt(scanner.nextLine());
                        switch (choiceSong) {
                            case 1:
                                song.addSong(scanner);
                                break;
                            case 2:
                                song.showSong();
                                break;
                            case 3:
                                song.changeSongById();
                                break;
                            case 4:
                                song.deleteSongById(scanner);
                                break;
                            case 5:
                                continueSongManagement = false;
                                break;
                            default:
                                System.out.println("Chức năng không tồn tại");
                        }
                    }
                    break;
                case 3:
                    song.searchByKeyword(scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chức năng không tồn tại");
            }
        }
    }

}