package DB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateService {

    private MongoCollection<Document> chatRoomsCollection;

    public CreateService(MongoDatabase appDatabase) {
        this.chatRoomsCollection = appDatabase.getCollection("chatRooms");  // 채팅방을 저장할 컬렉션
    }

    // 채팅방 생성
    public String createChatRoom(String roomName, List<Map<String, String>> participants) {
        String roomId = generateRoomId();
        Document chatRoom = new Document("roomId", roomId)
                .append("roomName", roomName)
                .append("participants", participants);

        chatRoomsCollection.insertOne(chatRoom);
        System.out.println("채팅방이 생성되었습니다: " + roomName);
        return roomId;
    }

    // 메시지 추가 (방마다 컬렉션 생성 후 메시지 저장)
    public void addMessageToRoom(MongoDatabase database, String roomId, String senderId, String senderName, String message) {
        // 채팅방 ID를 이용해 메시지 저장 컬렉션 생성
        MongoCollection<Document> messagesCollection = getMessagesCollection(database, roomId);

        Document newMessage = new Document("senderId", senderId)  // 보낸 사람의 ID
                .append("senderName", senderName)  // 보낸 사람의 이름
                .append("message", message)
                .append("timestamp", new java.util.Date());  // 메시지 전송 시간

        messagesCollection.insertOne(newMessage);  // 해당 방의 메시지 컬렉션에 저장
        System.out.println("메시지가 저장되었습니다: " + message);
    }

    // 특정 방의 메시지를 저장할 컬렉션 가져오기
    static public MongoCollection<Document> getMessagesCollection(MongoDatabase database, String roomId) {
        return database.getCollection(roomId); // 방 ID로 컬렉션 접근
    }

    // UUID를 이용한 roomId 생성
    static public String generateRoomId() {
        return java.util.UUID.randomUUID().toString();
    }

    // UUID를 이용한 userId 생성
    static public String generateUserId() {
        return java.util.UUID.randomUUID().toString();
    }

}
