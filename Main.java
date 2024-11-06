package DB;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // MongoDB 연결
        MongoDBConnection appDBConnection = new MongoDBConnection("chatAppDB");
        MongoDBConnection chatRoomConnection = new MongoDBConnection("chatRoomDB");

        MongoDatabase appDatabase = appDBConnection.getDatabase();  // 데이터베이스 가져오기
        MongoDatabase chatDatabase = chatRoomConnection.getDatabase();

        CreateService app = new CreateService(appDatabase);
        LoadDB loadDB = new LoadDB(chatDatabase);

        // 채팅방 이름 입력
        System.out.print("채팅방 이름을 입력하세요: ");
        String roomName = scanner.nextLine();

        // 참여 유저 입력 (나중에 GUI 선택창으로 넣을 예정)
        List<Map<String, String>> participants = new ArrayList<>();
        System.out.print("참여 유저의 수를 입력하세요: ");
        int userCount = scanner.nextInt();
        scanner.nextLine();  // 버퍼 비우기

        for (int i = 0; i < userCount; i++) {
            System.out.print("참여 유저 이름을 입력하세요: ");
            String userName = scanner.nextLine();
            String userId = app.generateUserId();  // 유저 ID 생성

            // ID와 이름을 맵으로 저장
            Map<String, String> user = new HashMap<>();
            user.put("userId", userId);
            user.put("userName", userName);
            participants.add(user);
        }

        // 채팅방 생성
        String roomId = app.createChatRoom(roomName, participants);

        // 메시지 입력 및 저장
        while (true) {
            System.out.print("보낼 유저 ID를 입력하세요 (종료하려면 'exit' 입력): ");
            String senderId = scanner.nextLine();
            if (senderId.equalsIgnoreCase("exit")) {
                break;
            }

            // 해당 ID의 유저 이름 검색 (참여 유저 목록에서 찾음)
            String senderName = "";
            for (Map<String, String> user : participants) {
                if (user.get("userId").equals(senderId)) {
                    senderName = user.get("userName");
                    break;
                }
            }

            if (senderName.isEmpty()) {
                System.out.println("유효하지 않은 유저 ID입니다.");
                continue;
            }

            System.out.print("메시지를 입력하세요: ");
            String message = scanner.nextLine();

            // 메시지 저장
            app.addMessageToRoom(chatDatabase, roomId, senderId, senderName, message);
        }

        loadDB.loadMessage(chatDatabase, roomId);

        scanner.close();
    }
}
