package controllers;

import models.Room;
import repositories.RoomRepo;
import service.RoomService;
import ultils.Input;
import views.ViewApartment;

import java.util.List;

public class RoomController {
    private final RoomService roomService = new RoomService();
    private final ViewApartment view = new ViewApartment();


    public void menuRoom(){
        List<Room> roomList;
        int choice;
        while (true){
            System.out.println("========MENU ROOM========\n" +
                    "1. Display room list\n" +
                    "2. Add new room\n" +
                    "3. Update room\n" +
                    "4. Delete room\n " +
                    "5. Return main menu\n" +
                    "Choose activity: ");
            choice = Input.getInt();
            switch (choice){
                case 1:
                    roomList = roomService.getAll();
                    view.displayAllRoom(roomList);
                    break;
                case 2:
                    Room room = view.addRoom();
                    roomService.add(room);
                    break;
                case 3:
                    view.viewUpdateRoom();
                    updateRoom();
                    break;
                case 4:
                    String roomNumber = view.viewDelete();
                    if (view.confirm()){
                        roomService.delete(roomNumber);
                        System.out.println("===+> Delete Successfully");
                    } else {
                        System.out.println("There are no rooms to delete, please add more rooms!!");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("This choice is not available!!");
                    break;
            }
        }
    }

    public void updateRoom(){
        String number = view.findRoom();
        Room room = roomService.findRoomByRoomNumber(number);
        int choice = view.viewUpdateRoom();
        switch (choice){
            case 1:
                int index = roomService.Index(room);
                Room room1 = roomService.updateTypeRoom(room);
                roomService.editIndex(index, room1);
                break;
            case 2:
                int index1 = roomService.Index(room);
                Room room2 = roomService.updatePrice(room);
                roomService.editIndex(index1,room2);
                break;
            case 3:
                menuRoom();
                break;
            default:
                System.out.println("This choice is not available!!");
                break;
        }
    }
}
