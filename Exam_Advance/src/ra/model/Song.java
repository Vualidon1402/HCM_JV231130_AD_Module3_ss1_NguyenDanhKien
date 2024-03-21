package ra.model;

import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Song {
    private static int nextId = 1;
    private int songId;
    private String songName;
    private String songDescriptions;
    private Singer singer;
    private String songWriter;
    private int year;
    private boolean songStatus;

    public Song() {
    }

    public Song(int songId, String songName, String songDescriptions, String songWriter, Singer singer, int year, boolean songStatus) {
        this.songId = songId;
        this.songName = songName;
        this.songDescriptions = songDescriptions;
        this.singer = singer;
        this.songWriter = songWriter;
        this.year = year;
        this.songStatus = songStatus;
    }

    public Song(Singer singer) {
        this.singer = singer;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongDescriptions() {
        return songDescriptions;
    }

    public void setSongDescriptions(String songDescriptions) {
        this.songDescriptions = songDescriptions;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    @Override
    public String toString() {
        return String.format("songId: %s, \nsongName: %s, \nsongDescriptions: %s, \nsingerName: %s, \nsongWriter: %s, \nyear: %d, \nsongStatus: %b \n",
                songId, songName, songDescriptions, singer.getSingerName(), songWriter, year, songStatus);
    }

    private boolean validate(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private String getInputFromUser(Scanner scanner, String regex) {
        String data = scanner.nextLine();
        boolean isValidate = validate(regex, data);
        if (isValidate) {
            return data;
        } else {
            System.out.println("Du lieu ban nhap khong hop le,vui long nhap lai");
            return getInputFromUser(scanner, regex);
        }
    }

    Scanner scanner = new Scanner(System.in);
    private Song[] songArr = new Song[100];

    private Song inputData(Scanner scanner) {
        System.out.println("Mời bạn nhập tên bài hát:");
        String songName = getInputFromUser(scanner, ".+");
        System.out.println("Mời bạn nhập mô tả bài hát:");
        String songDescriptions = getInputFromUser(scanner, ".+");
        System.out.println("Mời bạn nhập Id tên ca sĩ:");
        int singerId = Integer.parseInt(getInputFromUser(scanner, "\\d+"));
        Singer singer = this.singer.getSingerById(singerId);
        if (singer == null) {
            System.out.println("Ca sĩ không tồn tại. Mời bạn nhập thông tin ca sĩ mới:");
            singer = this.singer.inputData(scanner);
        }
        System.out.println("Mời bạn nhập tên người sáng tác :");
        String songWriter = getInputFromUser(scanner, ".+");
        System.out.println("Mời bạn nhập năm phát hành :");
        int year = Integer.parseInt(getInputFromUser(scanner, "\\d+"));
        boolean songStatus = true;
        return new Song(nextId++, songName, songDescriptions, songWriter, singer, year, songStatus);
    }


    public void addSong(Scanner scanner) {
        System.out.println("Mời bạn nhập số lượng bài hát cần thêm:");
        int number = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < songArr.length; i++) {
            if (songArr[i] == null) {
                songArr[i] = inputData(scanner);
                number--;
            }
            if (number == 0) {
                break;
            }
        }
    }

    public void showSong() {
        boolean isEmpty = true;
        for (int i = 0; i < songArr.length; i++) {
            if (songArr[i] != null) {
                System.out.println(songArr[i].toString());
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("Mảng bài hát rỗng.");
        }
    }

    public void changeSongById() {
        boolean isEmpty = true;
        for (Song song : songArr) {
            if (song != null) {
                isEmpty = false;
                break;
            }
        }

        if (isEmpty) {
            System.out.println("Mảng bài hát rỗng.");
            return;
        }

        System.out.println("Mời bạn nhập id bài hát cần sửa:");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < songArr.length; i++) {
            if (songArr[i] != null && songArr[i].getSongId() == id) {
                songArr[i] = inputData(scanner);
                break;
            }
        }
    }


    public void deleteSongById(Scanner scanner) {
        boolean isEmpty = true;
        for (Song song : songArr) {
            if (song != null) {
                isEmpty = false;
                break;
            }
        }

        if (isEmpty) {
            System.out.println("Mảng bài hát rỗng.");
            return;
        }

        System.out.println("Mời bạn nhập id bài hát cần xóa:");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < songArr.length; i++) {
            if (songArr[i] != null && songArr[i].getSongId() == id) {
                songArr[i] = null;
                break;
            }
        }
    }

    public void searchByKeyword(Scanner scanner) {
        System.out.println("Nhập từ khóa tìm kiếm:");
        String keyword = scanner.nextLine().trim().toLowerCase();
        String normalizedKeyword = removeAccents(keyword);

        boolean found = false;
        System.out.println("Kết quả tìm kiếm:");
        for (Song song : songArr) {
            if (song != null) {
                String normalizedSongName = removeAccents(song.getSongName().toLowerCase());
                String normalizedSongDescriptions = removeAccents(song.getSongDescriptions().toLowerCase());

                if (normalizedSongName.contains(normalizedKeyword) || normalizedSongDescriptions.contains(normalizedKeyword)) {
                    System.out.println(song.toString());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy bài hát nào phù hợp với từ khóa.");
        }
    }

    private String removeAccents(String input) {
        String withNormalizedChars = Normalizer.normalize(input, Normalizer.Form.NFD);
        return withNormalizedChars.replaceAll("\\p{M}", "");
    }


}