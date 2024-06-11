package service;

import models.Room;
import repositories.RoomRepo;
import ultils.Input;
import views.ViewApartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomService {
    RoomRepo roomRepo = new RoomRepo();
    ViewApartment viewApartment = new ViewApartment();

    public Room findRoomByRoomNumber(String roomNumber){
        List<Room> rooms = roomRepo.getAllRoom();
        for (Room room : rooms){
            if (Objects.equals(room.getRoomNumber(),roomNumber)){
                return room;
            }
        }
        return null;
    }

    public List<Room> getAll(){
        return roomRepo.getAllRoom();
    }

    public void delete(String roomNumber){
        roomRepo.deleteRoom(roomNumber);
    }

    public void add(Room room){
        roomRepo.addRoom(room);
    }

    public int Index(Room room){
        List<Room> roomList = getAll();
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomNumber().equals(room.getRoomNumber())){
                return i;
            }
        }
        return -1;
    }

    public void editIndex(int index, Room room){
        List<Room> roomList = getAll();
        roomList.set(index, room);
        roomRepo.writeFileToRoomCSV(roomList, false);
    }
    public Room updateTypeRoom(Room room){
        System.out.println("Enter new Type Room: ");
        String newTypeRoom = Input.getString();
        room.setTypeRoom(newTypeRoom);
        System.out.println("===+> Update Successfully");
        System.out.println(room);
        return room;
    }

    public Room updatePrice(Room room){
        System.out.println("Enter new Price: ");
        double newPrice = Input.getDou();
        room.setPrice(newPrice);
        System.out.println("===+> Update Successfully");
        System.out.println(room);
        return room;
    }


}
