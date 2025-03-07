package vn.hoangdung.dto.response;

public class ResponseError extends ResponseData<String> {
    
    public ResponseError(int status, String message) {
        super(status, message);
    }

}
