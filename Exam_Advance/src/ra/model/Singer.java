package ra.model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Singer {

    private static int nextId = 1;
    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender;
    private String genre;

    public Singer() {
    }

    public Singer(String singerName, int age, String nationality, boolean gender, String genre) {
        this.singerId = nextId++;
        this.singerName = singerName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.genre = genre;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("singerId: %s, \nsingerName: %s, \nage: %d, \nnationality: %s, \ngender: %s, \ngenre: %s \n", singerId, singerName, age, nationality, gender ? "Nam" : "Nu", genre);
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
    private Singer[] singerArr = new Singer[100];

    Singer inputData(Scanner scanner) {

        System.out.println("Mời bạn nhập tên ca sĩ: ");
        String singerName = getInputFromUser(scanner, ".+");
        System.out.println("Mời bạn nhập tuổi ca sĩ: ");
        int singerAge = Integer.parseInt(getInputFromUser(scanner, "\\d{0,100}"));
        System.out.println("Mời bạn nhập quốc tịch của ca sĩ: ");
        String singerNationality = getInputFromUser(scanner, ".+");
        System.out.println("Mời bạn nhập giới tính ca sĩ (true:Nam/false:Nu): ");
        boolean singerGender = Boolean.parseBoolean(getInputFromUser(scanner, "^(true|false)$"));
        System.out.println("Mời bạn nhập thể loại nhạc: ");
        String singerGenre = getInputFromUser(scanner, ".+");
        Singer newSinger = new Singer(singerName, singerAge, singerNationality, singerGender, singerGenre);
        return newSinger;
    }

    public void addData(Scanner scanner) {
        System.out.println("Mời bạn nhập số lượng ca sĩ cần thêm:");
        int number = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < singerArr.length; i++) {
            if (singerArr[i] == null) {
                singerArr[i] = inputData(scanner);
                number--;
            }
            if (number == 0) {
                break;
            }
        }
    }

    public void showData() {
        boolean isEmpty = true;
        for (Singer singer : singerArr) {
            if (singer != null) {
                System.out.println(singer.toString());
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("Mảng ca sĩ rỗng.");
        }
    }


    public void updateSinger(Scanner scanner) {
        boolean hasSingers = false;
        for (Singer singer : singerArr) {
            if (singer != null) {
                hasSingers = true;
                break;
            }
        }
        if (!hasSingers) {
            System.out.println("Mảng ca sĩ rỗng.");
            return;
        }

        System.out.println("Mời bạn nhập id ca sĩ cần sửa:");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < singerArr.length; i++) {
            if (singerArr[i] != null && singerArr[i].getSingerId() == id) {
                Singer updatedSinger = inputData(scanner);
                updatedSinger.setSingerId(id);
                singerArr[i] = updatedSinger;
                break;
            }
        }
    }

    public void deleteSinger(Scanner scanner) {
        boolean hasSingers = false;
        for (Singer singer : singerArr) {
            if (singer != null) {
                hasSingers = true;
                break;
            }
        }
        if (!hasSingers) {
            System.out.println("Mảng ca sĩ rỗng.");
            return;
        }

        System.out.println("Mời bạn nhập id ca sĩ cần xóa:");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < singerArr.length; i++) {
            if (singerArr[i] != null && singerArr[i].getSingerId() == id) {
                for (int j = i; j < singerArr.length - 1; j++) {
                    singerArr[j] = singerArr[j + 1];
                }
                singerArr[singerArr.length - 1] = null;
                break;
            }
        }
    }


    public Singer getSingerById(int id) {
        for (Singer singer : singerArr) {
            if (singer != null && singer.getSingerId() == id) {
                return singer;
            }
        }
        return null;
    }

}