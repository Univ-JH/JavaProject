package DB;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadDB {
    Scanner scanner = new Scanner(System.in);

    private MongoCollection<Document> chatRoomsCollection;

    public LoadDB(MongoDatabase database) {
        this.chatRoomsCollection = database.getCollection("chatRooms");  // 채팅방을 저장할 컬렉션
    }

    // 특정 채팅방에서 메시지 불러오기 (페이징 적용)
    public void loadMessage(MongoDatabase database, String roomId){
        // 페이징 처리하여 메시지 불러오기
        System.out.println("몇 개의 메시지를 불러올까요?");
        int limit = scanner.nextInt();
        scanner.nextLine();  // 버퍼 비우기

        System.out.println("몇 번째 메시지부터 불러올까요?");
        int skip = scanner.nextInt();

        List<Document> messages = getMessagesFromRoom(database, roomId, limit, skip);
        for (Document msg : messages) {
            System.out.println(msg.toJson());
        }
    }

    public List<Document> getMessagesFromRoom(MongoDatabase database, String roomId, int limit, int skip) {
        MongoCollection<Document> messagesCollection = CreateService.getMessagesCollection(database, roomId);
        List<Document> messages = messagesCollection.find()
                .sort(new Document("timestamp", -1))  // 최신 메시지 순으로 정렬
                .skip(skip)  // 페이지의 시작점
                .limit(limit)  // 불러올 메시지 수
                .into(new ArrayList<>());

        return messages;
    }
}
