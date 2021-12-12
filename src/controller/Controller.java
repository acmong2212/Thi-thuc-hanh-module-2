package controller;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    static List<Contact> contactList = new ArrayList<>();

    public void displayContact() {
        for (int i = 0; i < contactList.size(); i++) {
            if (i % 5 == 0 && i != 0) {
                scanner.nextLine();
            }
            System.out.println(contactList.get(i));
        }
    }

    public void addContact() {
        System.out.println("Nhập số điện thoại:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Nhập nhóm của danh bạ:");
        String group = scanner.nextLine();
        System.out.println("Nhập họ và tên:");
        String name = scanner.nextLine();
        System.out.println("Nhập giới tính:");
        String gender = scanner.nextLine();
        System.out.println("Nhập địa chỉ:");
        String address = scanner.nextLine();
        System.out.println("Nhập ngày sinh:");
        int birthday = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập email:");
        String email = scanner.nextLine();

        System.err.println("Đã thêm người này");
        contactList.add(new Contact(phoneNumber, group, name, gender, address, birthday, email));
        writerFile(FILE_PATH, contactList);
    }

    public void updateContact() {
        System.out.println("Nhập số điện thoại gần sửa");
        int id = findIndex();
        if (id >= 0) {
            System.out.println("Nhập số điện thoại mới:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Nhập nhóm của danh bạ:");
            String group = scanner.nextLine();
            System.out.println("Nhập họ và tên:");
            String name = scanner.nextLine();
            System.out.println("Nhập giới tính:");
            String gender = scanner.nextLine();
            System.out.println("Nhập địa chỉ:");
            String address = scanner.nextLine();
            System.out.println("Nhập ngày sinh:");
            int birthday = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập email:");
            String email = scanner.nextLine();


            System.out.println("ĐÃ CẬP NHẬP!!!");

            contactList.get(id).setPhoneNumber(phoneNumber);
            contactList.get(id).setGroup(group);
            contactList.get(id).setName(name);
            contactList.get(id).setGender(gender);
            contactList.get(id).setAddress(address);
            contactList.get(id).setBirthday(birthday);
            contactList.get(id).setEmail(email);
            writerFile(FILE_PATH, contactList);
        } else {
            System.out.println("Không tìm danh bạ với số diện thoại trên, vui lòng nhập lại!!");
        }

    }

    public int findIndex() {
        String phoneNumber = scanner.nextLine();
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteContact() {
        System.out.println("Nhập số điện thoại cần xoá:");
        int id = findIndex();
        if (id >= 0) {
            System.out.println("Nhập 'Y' để xác nhận xoá khỏi bộ nhớ");
            String confirm = scanner.nextLine();
            if (confirm.equals("Y")) {
                contactList.remove(id);
                writerFile(FILE_PATH, contactList);
                System.out.println("ĐÃ XOÁ!!!");
            } else {
                System.out.println("Nhập Y cơ mà, nhập lại đê");
            }
        } else {
            System.out.println("Không tìm thấy !!");
        }
    }

    public void searchContact() {
        System.out.println("Nhập số điện thoại hoặc họ tên");
        String phoneNumberOrName = scanner.nextLine();
        int count = 0;
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Số điện thoại", "Nhóm", "Họ tên", "Giới tính", "Địa chỉ");
        for (Contact contact : contactList) {
            if (contact.getPhoneNumber().contains(phoneNumberOrName) || contact.getName().contains(phoneNumberOrName)) {
                count++;
                System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", contact.getPhoneNumber(), contact.getGroup(), contact.getName(), contact.getGender(), contact.getAddress());
            }
        }
        if (count == 0) {
            System.err.println("Không tìm thấy thông tin ");
        }
    }

//Đọc ghi file csv
    final String FILE_PATH = "contact.csv";
    public Controller() {
        contactList = readFile(FILE_PATH);
    }

    public void writerFile(String filePath, List<Contact> list) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                fileWriter.write(list.get(i) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> readFile(String FILE_PATH) {
        List<Contact> studentsList = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                String phoneNumber = str[0];
                String group = str[1];
                String name = str[2];
                String gender = str[3];
                String address = str[4];
                int birthday = Integer.parseInt(str[5]);
                String email = str[6];
                Contact contact = new Contact(phoneNumber, group, name, gender, address, birthday, email);
                studentsList.add(contact);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentsList;
    }
}
