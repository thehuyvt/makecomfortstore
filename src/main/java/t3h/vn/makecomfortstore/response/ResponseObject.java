package t3h.vn.makecomfortstore.response;

import lombok.Data;

@Data
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseObject() {

    }
}
