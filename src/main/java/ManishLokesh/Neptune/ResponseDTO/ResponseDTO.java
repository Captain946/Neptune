package ManishLokesh.Neptune.ResponseDTO;

public class ResponseDTO<T> {
    public String status;
    public String message;
    public Object result;

    public ResponseDTO(String status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ResponseDTO() {
    }

}
