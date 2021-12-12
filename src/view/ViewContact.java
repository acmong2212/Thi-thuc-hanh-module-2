package view;

import controller.Controller;
import model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewContact {
    public static void main(String[] args) {
        menuManagerContact();
    }

    public static void menuManagerContact() {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        List<Contact> contactList = new ArrayList<>();

        while (true) {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----" + "\n"
                    + "Chọn chức năng theo số (để tiếp tục)" + "\n"
                    + "1. Xem danh sách" + "\n"
                    + "2. Thêm mới" + "\n"
                    + "3. Cập nhập" + "\n"
                    + "4. Xoá" + "\n"
                    + "5. Tìm kiếm" + "\n"
                    + "6. Đọc từ file" + "\n"
                    + "7. Ghi vào file" + "\n"
                    + "8. Thoát" + "\n"
                    + "Chọn chức năng:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    controller.displayContact();
                    break;
                case 2:
                    controller.addContact();
                    break;
                case 3:
                    controller.updateContact();
                    break;
                case 4:
                    controller.deleteContact();
                    break;
                case 5:
                    controller.searchContact();
                    break;
                case 6:
                    controller.readFile("contact.csv");
                    break;
                case 7:
                    controller.writerFile("contact.csv", contactList);
                    break;
                case 8:
                    System.exit(8);
                    break;
            }
        }
    }
}
