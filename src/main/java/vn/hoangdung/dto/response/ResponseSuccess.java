package vn.hoangdung.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseSuccess extends ResponseEntity<ResponseSuccess.Payload> {


    //Mô tả dữ liệu trả về cho các API: PUT / PATHCH / DELETE
    public ResponseSuccess(HttpStatus status, String message) {
        super(new Payload(status.value(), message), HttpStatus.OK);
        //HttpStatus.OK ở đây là in ra data dạng json
        //còn thay bằng status thì chỉ in ra trạng thái
    }

    //Mô tả dữ liệu trả về cho các API: GET / POST
    public ResponseSuccess(HttpStatus status, String message, Object data) {
        super(new Payload(status.value(), message, data), HttpStatus.OK);
    }

    
    public static class Payload {

        private final int status;
        private final String message;
        private Object data;

        public Payload(int status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public Payload(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }        

    }

}
