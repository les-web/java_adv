package lambda_stream_optional;

public class StreamApiWithModel {

    // wypisywanie zawartość listy Users z InMemoryData
public void getAllUsers() {
    InMemoryData.users.stream().forEach(System.out::println);
}



    public static void main(String[] args) {
        StreamApiWithModel sapi = new StreamApiWithModel();
        sapi.getAllUsers();

    }
}