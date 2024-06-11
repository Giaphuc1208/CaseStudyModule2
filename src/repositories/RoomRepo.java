package repositories;

import models.Room;
import ultils.Input;
import ultils.Validate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepo {
    private final String PATH_ROOM = "src/data/room.csv";

    public void addRoom(Room room){
        List<Room> roomList = getAllRoom();
        roomList.add(room);
        writeFileToRoomCSV(roomList, false);
    }

    public List<Room> getAllRoom(){
        List<Room> list = new ArrayList<>();
        File file = new File(PATH_ROOM);
        try(FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] data = line.split(",");
                Room room = new Room(data[0],data[1],Double.parseDouble(data[2]));
                list.add(room);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void deleteRoom(String roomNumber){
      List<Room> roomList = getAllRoom();
      for (Room room : roomList){
          if (room.getRoomNumber().equals(roomNumber)){
              roomList.remove(room);
              writeFileToRoomCSV(roomList, false);
              break;
          }
      }
    }



    public void writeFileToRoomCSV(List<Room> rooms, boolean append){
        try {
            FileWriter fileWriter = new FileWriter(PATH_ROOM, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Room room : rooms){
                bufferedWriter.write(room.toCsv());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}
